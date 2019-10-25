package com.cn.easyRpt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cn.base.utils.ResultMapUtils;
import java.util.Map;
import com.cn.easyRpt.entity.EasyRptExport;
import com.cn.easyRpt.service.EasyRptExportService;





/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ms.x
 * @since 2019-10-24
 */
@Api(" 服务")
@RequestMapping("/easyRptExport")
@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class EasyRptExportController {

    @Autowired
    private EasyRptExportService easyRptExportServiceImpl;

    @ApiOperation("保存")
    @PostMapping
    public Map<String,Object> insert(@RequestBody EasyRptExport easyRptExport) {
        easyRptExportServiceImpl.save(easyRptExport);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("修改")
    @PutMapping
    public Map<String,Object> update(@RequestBody EasyRptExport easyRptExport) {
        easyRptExportServiceImpl.updateById(easyRptExport);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("查询")
    @GetMapping
    public Map<String,Object> selectById(QueryWrapper<EasyRptExport> wrapper) {
        return ResultMapUtils.getResultMap(easyRptExportServiceImpl.list(wrapper));
    }

    @ApiOperation("分页")
    @GetMapping("/page")
    public Map<String,Object> selectPage(Page<EasyRptExport> page,QueryWrapper<EasyRptExport> wrapper) {
        return ResultMapUtils.getPageResult(easyRptExportServiceImpl.page(page,wrapper));
    }
}
