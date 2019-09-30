package com.cn.user.service.impl;

import com.cn.user.entity.User;
import com.cn.user.mapper.UserMapper;
import com.cn.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
