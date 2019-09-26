package com.cn.shrio.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.jwt.entity.Role;
import com.cn.jwt.entity.User;
import com.cn.jwt.entity.UserRole;
import com.cn.jwt.utils.ThreadLocals;
import com.cn.jwt.utils.WorkUtils;
import com.cn.shrio.service.RoleService;
import com.cn.shrio.service.UserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author yshh44
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    private static Logger logger= LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleServiceImpl;
    @Autowired
    private UserRoleService userRoleServiceImpl;


    @GetMapping("/grant/list")
    @RequiresPermissions("role:list")
    public Object getInfo(Long userId){
        User currentUser = ThreadLocals.getCurrentUser();
        QueryWrapper wrapper = new QueryWrapper();
        List<Role> distributable =  roleServiceImpl.list(wrapper);
        wrapper = new QueryWrapper();
        StringBuilder sb = new StringBuilder("select 1 from user_role where role.role_id = user_role.role_id and user_role.user_id=");
        sb.append(userId);
        wrapper.exists(sb.toString());
        List<Role> existRoles =  roleServiceImpl.list(wrapper);
        Map<String, Object> successResultMap = WorkUtils.getSuccessResultMap();
        successResultMap.put("distributable",distributable);
        successResultMap.put("existRoles",existRoles);

        return  successResultMap;
    }
    @GetMapping("/list")
    @RequiresPermissions("role:list")
    public Object getRoleGrantList(Role role){
        User currentUser = ThreadLocals.getCurrentUser();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.setEntity(role);
        List<Role> roles =  roleServiceImpl.list(wrapper);
        return  WorkUtils.getResultDataMap(roles);
    }
    @PostMapping("/grant")
    @RequiresPermissions("role:grant")
    public Object add(@RequestParam(value = "userId",defaultValue = "0") Long userId, @RequestParam(value = "roleIdStr",defaultValue = "")String roleIdStr){
        if (userId == null ||userId==0){
            return WorkUtils.getResultMap("-1","userId 为空");
        }
        List<String> str=null;
        userRoleServiceImpl.remove(new QueryWrapper<UserRole>().eq("user_id",userId));
        if (StringUtils.isNoneBlank(roleIdStr)){
             str = Arrays.asList(roleIdStr.split(","));
        }
        if (!CollectionUtils.isEmpty(str)){
            List<UserRole> userRoles = new ArrayList<>();
            str.forEach(e->{
                UserRole userRole = new UserRole();
                userRole.setRoleId(Long.valueOf(e));
                userRole.setUserId(userId);
                userRoles.add(userRole);
            });
            userRoleServiceImpl.saveBatch(userRoles);
        }
        return  WorkUtils.getSuccessResultMap();
    }
}

