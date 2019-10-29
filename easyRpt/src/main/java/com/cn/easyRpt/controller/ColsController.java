package com.cn.easyRpt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cn.base.utils.ResultMapUtils;
import java.util.Map;
import com.cn.easyRpt.entity.Cols;
import com.cn.easyRpt.service.ColsService;





/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ms.x
 * @since 2019-10-25
 */
@Api(" 服务")
@RequestMapping("/cols")
@RestController
public class ColsController {

    @Autowired
    private ColsService colsServiceImpl;

    @ApiOperation("保存")
    @PostMapping
    public Map<String,Object> insert(@RequestBody Cols cols) {
        colsServiceImpl.save(cols);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("修改")
    @PutMapping
    public Map<String,Object> update(Cols cols, QueryWrapper<Cols> wrapper) {
        colsServiceImpl.update(cols,wrapper);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("查询")
    @GetMapping
    public Map<String,Object> selectById(QueryWrapper<Cols> wrapper) {
        return ResultMapUtils.getResultMap(colsServiceImpl.list(wrapper));
    }

    @ApiOperation("分页")
    @GetMapping("/page")
    public Map<String,Object> selectPage(Page<Cols> page,QueryWrapper<Cols> wrapper) {
        return ResultMapUtils.getPageResult(colsServiceImpl.page(page,wrapper));
    }
}
