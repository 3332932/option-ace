package com.cn.easyRpt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cn.base.utils.ResultMapUtils;
import java.util.Map;
import com.cn.easyRpt.entity.WhereCondition;
import com.cn.easyRpt.service.WhereConditionService;





/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ms.x
 * @since 2019-10-25
 */
@Api(" 服务")
@RequestMapping("/whereCondition")
@RestController
public class WhereConditionController {

    @Autowired
    private WhereConditionService whereConditionServiceImpl;

    @ApiOperation("保存")
    @PostMapping
    public Map<String,Object> insert(@RequestBody WhereCondition whereCondition) {
        whereConditionServiceImpl.save(whereCondition);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("修改")
    @PutMapping
    public Map<String,Object> update(WhereCondition whereCondition, QueryWrapper<WhereCondition> wrapper) {
        whereConditionServiceImpl.update(whereCondition,wrapper);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("查询")
    @GetMapping
    public Map<String,Object> selectById(QueryWrapper<WhereCondition> wrapper) {
        return ResultMapUtils.getResultMap(whereConditionServiceImpl.list(wrapper));
    }

    @ApiOperation("分页")
    @GetMapping("/page")
    public Map<String,Object> selectPage(Page<WhereCondition> page,QueryWrapper<WhereCondition> wrapper) {
        return ResultMapUtils.getPageResult(whereConditionServiceImpl.page(page,wrapper));
    }
}
