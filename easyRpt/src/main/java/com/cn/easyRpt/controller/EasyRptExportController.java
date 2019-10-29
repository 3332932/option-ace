package com.cn.easyRpt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.easyRpt.entity.Cols;
import com.cn.easyRpt.entity.WhereCondition;
import com.cn.easyRpt.entity.dto.ColsDto;
import com.cn.easyRpt.entity.dto.EasyRptParam;
import com.cn.easyRpt.entity.dto.WheresDto;
import com.cn.easyRpt.service.ColsService;
import com.cn.easyRpt.service.DynamicQueryService;
import com.cn.easyRpt.service.WhereConditionService;
import com.cn.easyRpt.utils.EasyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.cn.base.utils.ResultMapUtils;

import java.util.*;
import java.util.stream.Collectors;

import com.cn.easyRpt.entity.EasyRptExport;
import com.cn.easyRpt.service.EasyRptExportService;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ms.x
 * @since 2019-10-24
 */
@Api(" 服务")
@RequestMapping("/easyRptExport")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class EasyRptExportController {

    @Autowired
    private EasyRptExportService easyRptExportServiceImpl;
    @Autowired
    private DynamicQueryService dynamicQueryServiceImpl;
    @Autowired
    private ColsService colsServiceImpl;
    @Autowired
    private WhereConditionService whereConditionServiceImpl;

    @ApiOperation("保存")
    @PostMapping
    public Map<String, Object> insert(@RequestBody EasyRptExport easyRptExport) {
        easyRptExportServiceImpl.save(easyRptExport);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("修改")
    @PutMapping
    public Map<String, Object> update(@RequestBody EasyRptExport easyRptExport) {
        easyRptExportServiceImpl.updateById(easyRptExport);
        return ResultMapUtils.getSuccessResultMap();
    }

    @ApiOperation("查询")
    @GetMapping
    public Map<String, Object> selectById(QueryWrapper<EasyRptExport> wrapper) {
        return ResultMapUtils.getResultMap(easyRptExportServiceImpl.list(wrapper));
    }

    @ApiOperation("分页")
    @GetMapping("/page")
    public Map<String, Object> selectPage(Page<EasyRptExport> page, QueryWrapper<EasyRptExport> wrapper) {
        return ResultMapUtils.getPageResult(easyRptExportServiceImpl.page(page, wrapper));
    }

    @ApiOperation("获取列属性")
    @GetMapping("where")
    public Map<String, Object> where(String rptNo) {
        EasyRptExport rptExport = easyRptExportServiceImpl.getOne(new QueryWrapper<EasyRptExport>().eq(EasyRptExport.RPT_NO,rptNo));

        List<Cols> list = colsServiceImpl.list(new QueryWrapper<Cols>().eq(Cols.RPTNO, rptExport.getRptNo()));
        List<WhereCondition> whereConditions = whereConditionServiceImpl.list(new QueryWrapper<WhereCondition>().eq(WhereCondition.RPT_NO, rptExport.getRptNo()));
        Map<String, Object> map = new HashMap<>();
        map.put("easyrptExport", rptExport);
        map.put("colsList", list);
        map.put("whereList", whereConditions);
        Map<String, Object> resultMap = ResultMapUtils.getSuccessResultMap();
        resultMap.put("rows", map);
        return resultMap;
    }

    @ApiOperation("获取列属性")
    @PostMapping("save")
    public Map<String, Object> save(@RequestBody EasyRptParam easyRptParam) {
        ColsDto cols = easyRptParam.getCols();
        List<Cols> deleteRows = cols.getDeleteRows();
        if (!CollectionUtils.isEmpty(deleteRows)) {
            colsServiceImpl.removeByIds(deleteRows.stream().map(e -> e.getRptNo()).collect(Collectors.toList()));
        }
        List<Cols> insertRows = cols.getInsertRows();
        if (!CollectionUtils.isEmpty(insertRows)) {
            colsServiceImpl.saveBatch(insertRows);
        }
        List<Cols> updateRows = cols.getUpdateRows();
        if (!CollectionUtils.isEmpty(updateRows)) {
            colsServiceImpl.updateBatchById(updateRows);
        }
        WheresDto wheres = easyRptParam.getWheres();
        List<WhereCondition> deleteRows1 = wheres.getDeleteRows();

        if (!CollectionUtils.isEmpty(deleteRows1)) {
            whereConditionServiceImpl.removeByIds(deleteRows1.stream().map(e -> e.getRptNo()).collect(Collectors.toList()));
        }
        List<WhereCondition> insertRows1 = wheres.getInsertRows();
        if (!CollectionUtils.isEmpty(insertRows1)) {
            whereConditionServiceImpl.saveBatch(insertRows1);
        }
        List<WhereCondition> updateRows1 = wheres.getUpdateRows();
        if (!CollectionUtils.isEmpty(updateRows1)) {
            whereConditionServiceImpl.updateBatchById(updateRows1);
        }
        return ResultMapUtils.getSuccessResultMap();
    }


}
