package com.cn.dynamicQuery.service;

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

    List<Map<String, Object>> dynamicQuery(String sql);
}
