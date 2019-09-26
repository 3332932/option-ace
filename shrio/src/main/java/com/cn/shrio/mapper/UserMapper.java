package com.cn.shrio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.jwt.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
