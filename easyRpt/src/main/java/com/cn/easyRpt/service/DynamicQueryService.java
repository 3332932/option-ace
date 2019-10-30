package com.cn.easyRpt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ms.x
 * @since 2019-10-23
 */
public interface DynamicQueryService {

    List<Map<String, Object>> dynamicQuery(Page page, String sql);

    List<Map<String, Object>> dynamicQuery(Page page, String sql, QueryWrapper wrapper);

    List<Map<String, Object>> dynamicQuery(String sql);

    List<String> getSqlColumnMetaData(String sql);
}
