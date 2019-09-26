package com.cn.shrio.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.jwt.entity.Permission;
import com.cn.jwt.entity.Role;
import com.cn.jwt.entity.RolePermission;
import com.cn.jwt.utils.WorkUtils;
import com.cn.shrio.service.PermissionService;
import com.cn.shrio.service.RolePermissionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author yshh44
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    private static Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private PermissionService permissionServiceImpl;
    @Autowired
    private RolePermissionService rolePermissionServiceImpl;


    @GetMapping("/list")
    @RequiresPermissions("permission:list")
    public Object getInfo(Page page, HttpServletRequest request) {
        Page<Permission> pages = (Page) permissionServiceImpl.page(page,new QueryWrapper<Permission>());
        return WorkUtils.getResultPageDataMap(pages.getRecords(), pages.getTotal());
    }

    @GetMapping("/grant/list")
    @RequiresPermissions("permission:list")
    public Object getInfo(Long roleId) {
        QueryWrapper wrapper = new QueryWrapper();
        List<Permission> distributable = permissionServiceImpl.list(wrapper);
        wrapper = new QueryWrapper();
        StringBuilder sb = new StringBuilder("select 1 from role_permission where permission.permission_id = role_permission.permission_id and role_permission.role_id=");
        sb.append(roleId);
        wrapper.exists(sb.toString());
        List<Role> existPermission = permissionServiceImpl.list(wrapper);
        Map<String, Object> successResultMap = WorkUtils.getSuccessResultMap();
        successResultMap.put("distributable", distributable);
        successResultMap.put("existPermission", existPermission);

        return successResultMap;
    }


    @PostMapping("/grant")
    @RequiresPermissions("permission:grant")
    public Object add(@RequestParam(value = "roleId", defaultValue = "0") Long roleId, @RequestParam(value = "permissionIdStr", defaultValue = "") String permissionIdStr) {
        if (roleId == null || roleId == 0) {
            return WorkUtils.getResultMap("-1", "userId 为空");
        }
        List<String> str = null;
        rolePermissionServiceImpl.remove(new QueryWrapper<RolePermission>().eq("role_id", roleId));
        if (StringUtils.isNoneBlank(permissionIdStr)) {
            str = Arrays.asList(permissionIdStr.split(","));
        }
        if (!CollectionUtils.isEmpty(str)) {
            List<RolePermission> rolePermissions = new ArrayList<>();
            str.forEach(e -> {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setPermissionId(Long.valueOf(e));
                rolePermission.setRoleId(roleId);
                rolePermissions.add(rolePermission);
            });
            rolePermissionServiceImpl.saveBatch(rolePermissions);
        }
        return WorkUtils.getSuccessResultMap();
    }

}

