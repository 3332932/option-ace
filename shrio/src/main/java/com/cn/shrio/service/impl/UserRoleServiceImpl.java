package com.cn.shrio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.jwt.entity.UserRole;
import com.cn.shrio.mapper.UserRoleMapper;
import com.cn.shrio.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yshh44
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Autowired
    private UserRoleMapper userRoleMapper;

}
