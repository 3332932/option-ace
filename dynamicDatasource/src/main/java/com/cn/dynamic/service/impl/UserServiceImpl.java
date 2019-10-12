package com.cn.dynamic.service.impl;

import com.cn.dynamic.entity.User;
import com.cn.dynamic.mapper.UserMapper;
import com.cn.dynamic.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author ms.x
 * @since 2019-10-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
