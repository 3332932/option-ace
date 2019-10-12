package com.cn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cn.base.utils.ResultMapUtils;
import java.util.Map;
import com.cn.entity.Permission;
import com.cn.service.PermissionService;





/**
 * <p>
 * 权限 前端控制器
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Api("权限 服务")
@RequestMapping("/permission")
@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionApi;

    @ApiOperation("保存")
    @PostMapping
    public Map<String,Object> insert(@RequestBody Permission permission) {
        permissionApi.save(permission);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("修改")
    @PutMapping
    public Map<String,Object> update(Permission permission, QueryWrapper<Permission> wrapper) {
        permissionApi.update(permission,wrapper);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("查询")
    @GetMapping
    public Map<String,Object> selectById(QueryWrapper<Permission> wrapper) {
        return ResultMapUtils.getResultMap(permissionApi.list(wrapper));
    }

    @ApiOperation("分页")
    @GetMapping("/page")
    public Map<String,Object> selectPage(Page<Permission> page,QueryWrapper<Permission> wrapper) {
        return ResultMapUtils.getPageResult(permissionApi.page(page,wrapper));
    }
}
