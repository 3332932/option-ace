package com.cn.dynamic.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cn.base.utils.ResultMapUtils;
import java.util.Map;
import com.cn.dynamic.entity.DmExpressB30;
import com.cn.dynamic.service.DmExpressB30Service;





/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ms.x
 * @since 2019-10-09
 */
@Api(" 服务")
@RequestMapping("/dmExpressB30")
@RestController
public class DmExpressB30Controller {

    @Autowired
    private DmExpressB30Service dmExpressB30Api;

    @ApiOperation("保存")
    @PostMapping
    public Map<String,Object> insert(@RequestBody DmExpressB30 dmExpressB30) {
        dmExpressB30Api.save(dmExpressB30);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("修改")
    @PutMapping
    public Map<String,Object> update(DmExpressB30 dmExpressB30, QueryWrapper<DmExpressB30> wrapper) {
        dmExpressB30Api.update(dmExpressB30,wrapper);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("查询")
    @GetMapping
    public Map<String,Object> selectById(QueryWrapper<DmExpressB30> wrapper) {
        return ResultMapUtils.getResultMap(dmExpressB30Api.list(wrapper));
    }

    @ApiOperation("分页")
    @GetMapping("/page")
    public Map<String,Object> selectPage(Page<DmExpressB30> page,QueryWrapper<DmExpressB30> wrapper) {
        return ResultMapUtils.getPageResult(dmExpressB30Api.page(page,wrapper));
    }
}
