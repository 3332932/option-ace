package com.cn.shrio.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.jwt.entity.Permission;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getPermissionByRoleId(@Param("roleId") Long roleId);
    List<Permission> getPermissionByRoleIds(@Param("list") List<Long> list);
}
