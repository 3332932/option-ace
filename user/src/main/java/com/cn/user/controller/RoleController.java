package com.cn.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cn.base.utils.ResultMapUtils;
import java.util.Map;
import com.cn.user.entity.Role;
import com.cn.user.service.IRoleService;





/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author ms.x
 * @since 2019-09-30
 */
@Api("角色表 服务")
@RequestMapping("/role")
@RestController
public class RoleController {

    @Autowired
    private IRoleService roleApi;

    @ApiOperation("保存")
    @PostMapping
    public Map<String,Object> insert(@RequestBody Role role) {
        roleApi.save(role);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("修改")
    @PutMapping
    public Map<String,Object> update(Role role, QueryWrapper<Role> wrapper) {
        roleApi.update(role,wrapper);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("查询")
    @GetMapping
    public Map<String,Object> selectById(QueryWrapper<Role> wrapper) {
        return ResultMapUtils.getResultMap(roleApi.list(wrapper));
    }

    @ApiOperation("分页")
    @GetMapping("/page")
    public Map<String,Object> selectPage(Page<Role> page,QueryWrapper<Role> wrapper) {
        return ResultMapUtils.getPageResult(roleApi.page(page,wrapper));
    }
}
