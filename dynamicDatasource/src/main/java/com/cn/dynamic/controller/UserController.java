package com.cn.dynamic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cn.base.utils.ResultMapUtils;
import java.util.Map;
import com.cn.dynamic.entity.User;
import com.cn.dynamic.service.UserService;





/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author ms.x
 * @since 2019-10-09
 */
@Api("用户表 服务")
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userApi;

    @ApiOperation("保存")
    @PostMapping
    public Map<String,Object> insert(@RequestBody User user) {
        userApi.save(user);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("修改")
    @PutMapping
    public Map<String,Object> update(User user, QueryWrapper<User> wrapper) {
        userApi.update(user,wrapper);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("查询")
    @GetMapping
    public Map<String,Object> selectById(QueryWrapper<User> wrapper) {
        return ResultMapUtils.getResultMap(userApi.list(wrapper));
    }

    @ApiOperation("分页")
    @GetMapping("/page")
    public Map<String,Object> selectPage(Page<User> page,QueryWrapper<User> wrapper) {
        return ResultMapUtils.getPageResult(userApi.page(page,wrapper));
    }
}
