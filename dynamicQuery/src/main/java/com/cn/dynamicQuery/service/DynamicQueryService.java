package com.cn.dynamicQuery.service;

import com.cn.dynamicQuery.entity.EdwFactBaseOutStoreDetail;
import com.baomidou.mybatisplus.extension.service.IService;

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
