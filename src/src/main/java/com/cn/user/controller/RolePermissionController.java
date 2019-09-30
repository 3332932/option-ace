package com.cn.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cn.base.utils.ResultMapUtils;
import java.util.Map;
import com.cn.user.entity.RolePermission;
import com.cn.user.service.IRolePermissionService;





/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Api(" 服务")
@RequestMapping("/rolePermission")
@RestController
public class RolePermissionController {

    @Autowired
    private IRolePermissionService rolePermissionApi;

    @ApiOperation("保存")
    @PostMapping
    public Map<String,Object> insert(@RequestBody RolePermission rolePermission) {
        rolePermissionApi.save(rolePermission);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("修改")
    @PutMapping
    public Map<String,Object> update(RolePermission rolePermission, QueryWrapper<RolePermission> wrapper) {
        rolePermissionApi.update(rolePermission,wrapper);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("查询")
    @GetMapping
    public Map<String,Object> selectById(QueryWrapper<RolePermission> wrapper) {
        return ResultMapUtils.getResultMap(rolePermissionApi.list(wrapper));
    }

    @ApiOperation("分页RolePermission")
    @GetMapping("/page")
    public Map<String,Object> selectPage(Page<RolePermission> page,QueryWrapper<RolePermission> wrapper) {
        return ResultMapUtils.getPageResult(rolePermissionApi.page(page,wrapper));
    }
}
