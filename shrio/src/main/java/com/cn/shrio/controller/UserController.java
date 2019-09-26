package com.cn.shrio.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.jwt.entity.Role;
import com.cn.jwt.entity.User;
import com.cn.jwt.utils.ThreadLocals;
import com.cn.jwt.utils.WorkUtils;
import com.cn.shrio.service.RoleService;
import com.cn.shrio.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yshh44
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger logger= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userServiceImpl;
    @Autowired
    private RoleService roleServiceImpl;
    @GetMapping("/list")
    @RequiresPermissions("user:list")
    public Object getInfo(Page page, User user, HttpServletRequest request){
        User currentUser = ThreadLocals.getCurrentUser();
        WorkUtils.setBlankToNull(user);
        QueryWrapper wrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(user.getUsername())){
            wrapper.likeRight("user_name",user.getUsername());
            user.setUsername(null);
        }
        wrapper.setEntity(user);

        Page<User> s = (Page) userServiceImpl.page(page,wrapper);
        return  WorkUtils.getResultPageDataMap(s.getRecords(),s.getTotal());
    }

    @GetMapping("/get")
    public Object getDtl(HttpServletRequest request){
        User currentUser = ThreadLocals.getCurrentUser();
        Wrapper wrapper = new QueryWrapper();
        ((QueryWrapper) wrapper).eq("user_name",currentUser.getUsername());
        User user = userServiceImpl.getOne(wrapper);
        List<Role> roles = roleServiceImpl.getRoleByUserId(user.getUserId());
        List<String> roleStr = new ArrayList<>();
        roles.forEach(e->{
            roleStr.add(e.getRoleName());
        });
        Map<String, Object> resultDataMap = WorkUtils.getResultDataMap(user);
        resultDataMap.put("roles",roleStr);
        return  resultDataMap;
    }

}

