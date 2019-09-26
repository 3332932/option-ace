package com.cn.shrio.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.jwt.entity.Permission;

import java.util.List;

/**
 * @author yshh44
 */
public interface PermissionService extends IService<Permission> {


    List<Permission> getPermissionByRoleId(Long roleId);

    List<Permission> getPermissionByRoleIds(List<Long> list);
}
