package com.cn.dynamicQuery.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ms.x
 * @since 2019-10-23
 */
@Repository
public interface DynamicQueryMapper {
    @Select("${sql}")
    List<Map<String, Object>> dynamicQuery(Map<String, Object> map);

    @Select("${sql}")
    List<Map<String, Object>> dynamicPageQuery(Map<String, Object> map, Page<Map<String, Object>> page);
}
