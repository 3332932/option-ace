package com.cn.web.resolver;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.base.utils.BaseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Component
public class PageMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private Logger logger = LoggerFactory.getLogger(PageMethodArgumentResolver.class);
    private static final Page DEFAULT_PAGE_REQUEST = new Page(0, 10);
    private static final String PAGE_PREFIX_PN = "page.pn";
    private static final String PAGE_PREFIX_SIZE = "page.size";
    private static final String SORT_PREFIX = "sort";

    public boolean supportsParameter(MethodParameter parameter) {
        return Page.class.isAssignableFrom(parameter.getParameterType());
    }

    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Map<String, String[]> parameterMap = webRequest.getParameterMap();
        Page page = new Page();
        parameterMap.forEach((K,V)->{
            if (K.equalsIgnoreCase(PAGE_PREFIX_PN)){
                page.setCurrent(Integer.valueOf(V[0]));
            }else if(K.startsWith(PAGE_PREFIX_SIZE)){
                page.setSize(Integer.valueOf(V[0]));
            }
        });
        return page;
    }

}
