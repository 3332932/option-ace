package com.cn.easyRpt.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;
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
    List<Map<String, Object>> dynamicQuery(Map<String, Object> map);
    List<Map<String, Object>> dynamicQueryWrapper(Map<String, Object> map);

}
