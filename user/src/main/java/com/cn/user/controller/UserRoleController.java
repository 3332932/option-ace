package com.cn.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.base.utils.ResultMapUtils;
import com.cn.user.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import com.cn.user.entity.UserRole;


/**
 * <p>
 * 用户与权限关系表 前端控制器
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Api("用户与权限关系表 服务")
@RequestMapping("/userRole")
@RestController
public class UserRoleController {

    @Autowired
    private UserRoleService userRoleApi;

    @ApiOperation("保存")
    @PostMapping
    public Map<String,Object> insert(@RequestBody UserRole userRole) {
        userRoleApi.save(userRole);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("修改")
    @PutMapping
    public Map<String,Object> update(UserRole userRole, QueryWrapper<UserRole> wrapper) {
        userRoleApi.update(userRole,wrapper);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("查询")
    @GetMapping
    public Map<String,Object> selectById(QueryWrapper<UserRole> wrapper) {
        return ResultMapUtils.getResultMap(userRoleApi.list(wrapper));
    }

    @ApiOperation("分页")
    @GetMapping("/page")
    public Map<String,Object> selectPage(Page<UserRole> page,QueryWrapper<UserRole> wrapper) {
        return ResultMapUtils.getPageResult(userRoleApi.page(page,wrapper));
    }
}
