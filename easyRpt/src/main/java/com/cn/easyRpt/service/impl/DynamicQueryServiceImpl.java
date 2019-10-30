package com.cn.easyRpt.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.easyRpt.mapper.DynamicQueryMapper;
import com.cn.easyRpt.service.DynamicQueryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.cn.easyRpt.utils.EasyUtils.sqlWhereHandler;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ms.x
 * @since 2019-10-23
 */
@Service
@Slf4j
public class DynamicQueryServiceImpl  implements DynamicQueryService {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
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

        if (wrapper != null && StringUtils.isNotEmpty(wrapper.getSqlSegment())){
            queryMap.put(EW,wrapper);
            if (!wrapper.getSqlSegment().trim().startsWith("and")&&!wrapper.getSqlSegment().trim().startsWith("ORDER BY")&&!wrapper.getSqlSegment().trim().startsWith("GROUP BY")){
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
    @Override
    public List<String> getSqlColumnMetaData(String sql) {
        Connection conn = sqlSessionTemplate.getSqlSessionFactory().openSession().getConnection();
        Statement stmt;
        List<String> colList = new ArrayList<>();

        try {
            stmt = conn.createStatement();
            String newSql = sqlWhereHandler(sql);
            log.info("Execute SQL:" + newSql);
            ResultSet rs = stmt.executeQuery(newSql);

            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String colLabel = resultSetMetaData.getColumnName(i);
                colList.add(colLabel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return colList;
    }

}
