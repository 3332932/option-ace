package com.cn.easyRpt.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.easyRpt.mapper.DynamicQueryMapper;
import com.cn.easyRpt.service.DynamicQueryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ms.x
 * @since 2019-10-23
 */
@Service
public class DynamicQueryServiceImpl  implements DynamicQueryService {
    /**
     * 动态查询入参key
     */
    public static final String SQL = "sql";
    public static final String EW = "ew";
    public static final String PAGE = "page";
    public static final String CONDITION = "condition";

    @Autowired
    private DynamicQueryMapper dynamicQueryMapper;

    @Override
    public List<Map<String, Object>> dynamicQuery(Page page, String sql, QueryWrapper wrapper) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put(SQL, sql);
        if (page != null){
            queryMap.put(PAGE, page);
        }
        String sqlSegment = wrapper.getSqlSegment();
        if (wrapper != null && StringUtils.isNotEmpty(sqlSegment)){
            queryMap.put(EW,wrapper);
            if (!sqlSegment.trim().startsWith("and")&&!sqlSegment.trim().startsWith("ORDER BY")&&!sqlSegment.trim().startsWith("GROUP BY")){
                queryMap.put(CONDITION,true);
            }else {
                queryMap.put(CONDITION,false);

            }
            return dynamicQueryMapper.dynamicQueryWrapper(queryMap);
        }
        return dynamicQueryMapper.dynamicQuery(queryMap);

    }

    @Override
    public List<Map<String, Object>> dynamicQuery(Page page,String sql) {
        return this.dynamicQuery(page,sql,null);
    }

    @Override
    public List<Map<String, Object>> dynamicQuery(String sql) {
        return this.dynamicQuery(null,sql,null);
    }

}
