package com.cn.dynamicQuery.service.impl;

import com.cn.dynamicQuery.mapper.DynamicQueryMapper;
import com.cn.dynamicQuery.service.DynamicQueryService;
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

    @Autowired
    private DynamicQueryMapper dynamicQueryMapper;

    @Override
    public List<Map<String, Object>> dynamicQuery(String sql) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put(SQL, sql);
        return dynamicQueryMapper.dynamicQuery(queryMap);
    }
}
