package com.cn.web.resolver;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerMapping;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class WrapperArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String prefix = "search";
    private static final String SORT_PREFIX = "sort";
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return QueryWrapper.class.isAssignableFrom(methodParameter.getParameterType())||
                Wrapper.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Map<String, String[]> parameterMap = webRequest.getParameterMap();
        String searchPrefix = getSearchPrefix(parameter);
        Map<String, String[]> wrapperMap = getPrefixParameterMap(searchPrefix, webRequest, true);
        boolean hasCustomSearchFilter = wrapperMap.size() > 0;
        QueryWrapper<?> queryWrapper = new QueryWrapper();
        if (hasCustomSearchFilter) {
            for (String name : wrapperMap.keySet()) {
                String entityFieldName = filterFieldName(name);
                String conditionValue = filterConditionValue(name);
                String[] mapValues = filterSearchValues(wrapperMap.get(name));
                String fieldName = getDbFieldName(parameter, entityFieldName);
                addCondition(queryWrapper, fieldName, conditionValue, mapValues);
            }
        }
        parameterMap.forEach((K,V)->{
            if(K.startsWith(SORT_PREFIX)){
                String column = K.substring(SORT_PREFIX.length() + 1);
                if (V[0].equals("asc")){
                    queryWrapper.orderBy(true,true,column);
                }else {
                    queryWrapper.orderBy(true,false,column);
                }
            }
        });
        return queryWrapper;
    }

    /**
     * 获取数据库字段名
     *
     * @param parameter
     * @param name
     * @return
     */
    private String getDbFieldName(MethodParameter parameter, String name) {
        String fieldName = null;
        Class<?> entityClazz = ReflectUtils.getClassGenricType(parameter.getContainingClass());
        try {
            Field field = entityClazz.getDeclaredField(name);
            TableField tableField = field.getAnnotation(TableField.class);
            if (tableField != null) {
                fieldName = tableField.value();
            }
        } catch (NoSuchFieldException e) {
            log.debug(e.getMessage());
        }
        // 如果没有设置TableField则直接用字段驼峰转换下划线
        if (fieldName == null) {
            fieldName = com.baomidou.mybatisplus.toolkit.StringUtils.camelToUnderline(name);
        }
        return fieldName;
    }


    private void addCondition(QueryWrapper<?> queryWrapper, String fieldName, String condition, String[] values) {
        if (condition.equals(SearchOperator.like.toString())) {
            queryWrapper.like(fieldName, values[0]);
        } else if (condition.equals(SearchOperator.notLike.toString())) {
            queryWrapper.notLike(fieldName, values[0]);
        } else if (condition.equals(SearchOperator.eq.toString())) {
            queryWrapper.eq(fieldName, values[0]);
        } else if (condition.equals(SearchOperator.ne.toString())) {
            queryWrapper.ne(fieldName, values[0]);
        } else if (condition.equals(SearchOperator.in.toString())) {
            queryWrapper.in(fieldName, values[0]);
        } else if (condition.equals(SearchOperator.notIn.toString())) {
            queryWrapper.notIn(fieldName, values[0]);
        } else if (condition.equals(SearchOperator.gt.toString())) {
            queryWrapper.gt(fieldName, values[0]);
        } else if (condition.equals(SearchOperator.gte.toString())) {
            queryWrapper.ge(fieldName, values[0]);
        } else if (condition.equals(SearchOperator.lt.toString())) {
            queryWrapper.lt(fieldName, values[0]);
        } else if (condition.equals(SearchOperator.lte.toString())) {
            queryWrapper.le(fieldName, values[0]);
        } else if (condition.equals(SearchOperator.orcGt.toString())) {
            queryWrapper.and(queryWrapper1 -> queryWrapper1.gt(fieldName,"to_date('"+values[0]+"','yyyy-mm-dd hh24:mi:ss')"));
        } else if (condition.equals(SearchOperator.orcGte.toString())) {
            queryWrapper.and(queryWrapper1 -> queryWrapper1.ge(fieldName,"to_date('"+values[0]+"','yyyy-mm-dd hh24:mi:ss')"));
        } else if (condition.equals(SearchOperator.orcLt.toString())) {
            queryWrapper.and(queryWrapper1 -> queryWrapper1.lt(fieldName,"to_date('"+values[0]+"','yyyy-mm-dd hh24:mi:ss')"));
        } else if (condition.equals(SearchOperator.orcLte.toString())) {
            queryWrapper.and(queryWrapper1 -> queryWrapper1.le(fieldName,"to_date('"+values[0]+"','yyyy-mm-dd hh24:mi:ss')"));
        } else if (condition.equals(SearchOperator.isNull.toString())) {
            queryWrapper.isNull(fieldName);
        } else if (condition.equals(SearchOperator.isNotNull.toString())) {
            queryWrapper.isNotNull(fieldName);
        } else if (condition.equals(SearchOperator.prefixLike.toString())) {
            queryWrapper.likeLeft(fieldName, values[0]);
        } else if (condition.equals(SearchOperator.suffixLike.toString())) {
            queryWrapper.likeRight(fieldName, values[0]);
        } else if (condition.equals(SearchOperator.prefixNotLike.toString())) {
            queryWrapper.notLike(fieldName, values[0]);
        }
    }

    protected Map<String, String[]> getPrefixParameterMap(String namePrefix, NativeWebRequest request, boolean subPrefix) {
        Map<String, String[]> result = new HashMap();
        Map<String, String> variables = this.getUriTemplateVariables(request);
        int namePrefixLength = namePrefix.length();
        Iterator parameterNames = variables.keySet().iterator();

        String name;
        char ch;
        while(parameterNames.hasNext()) {
            name = (String)parameterNames.next();
            if (name.startsWith(namePrefix)) {
                if (subPrefix) {
                    ch = name.charAt(namePrefix.length());
                    if (!this.illegalChar(ch)) {
                        result.put(name.substring(namePrefixLength + 1), new String[]{(String)variables.get(name)});
                    }
                } else {
                    result.put(name, new String[]{(String)variables.get(name)});
                }
            }
        }

        parameterNames = request.getParameterNames();

        while(parameterNames.hasNext()) {
            name = (String)parameterNames.next();
            if (name.startsWith(namePrefix)) {
                if (subPrefix) {
                    ch = name.charAt(namePrefix.length());
                    if (!this.illegalChar(ch)) {
                        result.put(name.substring(namePrefixLength + 1), request.getParameterValues(name));
                    }
                } else {
                    result.put(name, request.getParameterValues(name));
                }
            }
        }

        return result;
    }

    private boolean illegalChar(char ch) {
        return ch != '.' && ch != '_' && (ch < '0' || ch > '9');
    }

    protected final Map<String, String> getUriTemplateVariables(NativeWebRequest request) {
        Map<String, String> variables = (Map)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, 0);
        return variables != null ? variables : Collections.emptyMap();
    }


    @SuppressWarnings("unchecked")
    private String[] filterSearchValues(String[] values) {
        List<String> result = Lists.newArrayList(CollectionUtils.arrayToList(values));
        result = result.stream()
                // 去空
                .filter(StringUtils::isNoneBlank)
                // true转1 false转0
                .map(item -> {
                    if ("true".equalsIgnoreCase(item)) {
                        return "1";
                    }
                    return "false".equalsIgnoreCase(item) ? "0" : item;
                }).collect(Collectors.toList());

        return result.toArray(values);
    }

    private String filterFieldName(String name) {
        if (name.contains("_")) {
            return name.substring(0, name.lastIndexOf("_"));
        }
        return "eq";
    }

    private String filterConditionValue(String name) {
        if (name.contains("_")) {
            return name.substring(name.lastIndexOf("_")+1);
        }
        return "eq";
    }

    private String getSearchPrefix(MethodParameter parameter) {
        Qualifier qualifier = parameter.getParameterAnnotation(Qualifier.class);

        if (qualifier != null) {
            return new StringBuilder(qualifier.value()).append("_").append(prefix).toString();
        }

        return prefix;
    }
}

 

