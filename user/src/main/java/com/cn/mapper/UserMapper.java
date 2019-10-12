package com.cn.mapper;

import com.cn.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

}
