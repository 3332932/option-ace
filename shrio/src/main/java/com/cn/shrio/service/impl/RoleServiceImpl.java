package com.cn.shrio.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.jwt.entity.Role;
import com.cn.shrio.mapper.RoleMapper;
import com.cn.shrio.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yshh44
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleByUserId(Long userId){
        return roleMapper.getRoleByUserId(userId);
    }

}
