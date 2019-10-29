package com.cn.easyRpt.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cn.base.utils.ResultMapUtils;
import com.cn.easyRpt.entity.EasyRptExport;
import com.cn.easyRpt.service.DynamicQueryService;
import com.cn.easyRpt.service.EasyRptExportService;
import com.cn.easyRpt.utils.EasyUtils;
import com.cn.sql.SqlBuilder;
import com.cn.sql.SqlParser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Api(" 动态sql查询")
@RequestMapping("/querys")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
public class DynamicController {
    @Autowired
    private DynamicQueryService dynamicQueryServiceImpl;
    @Autowired
    private EasyRptExportService easyRptExportServiceImpl;
    @ApiOperation("动态查询")
    @PostMapping("/page")
    public Map<String, Object> selectPage(Page page, String rptNo,QueryWrapper queryWrapper) {
        EasyRptExport rptExport = easyRptExportServiceImpl.getOne(
                new QueryWrapper<EasyRptExport>().eq(EasyRptExport.RPT_NO,rptNo));
        SqlBuilder sqlBuilder = SqlParser.parse(rptExport.getSelectSql());
        page.setRecords(dynamicQueryServiceImpl.dynamicQuery(
                page, sqlBuilder.build(), queryWrapper));
        return ResultMapUtils.getPageResult(page);
    }

    @ApiOperation("获取列")
    @GetMapping("/column")
    public Map<String, Object> column(String rptNo) {
        EasyRptExport rptExport = easyRptExportServiceImpl.getOne(new QueryWrapper<EasyRptExport>().eq(EasyRptExport.RPT_NO,rptNo));
        String sql = rptExport.getSelectSql();
        if (EasyUtils.isDMLOrDCLOrOrDDL(sql)) {
            return ResultMapUtils.getErrorResultMap("仅支持查询SQL语句");
        }
        //sql =  EasyUtils.sqlWhereHandler(sql);
        Map<String, Object> resultMap = ResultMapUtils.getSuccessResultMap();
        List<Map<String, Object>> maps = dynamicQueryServiceImpl.dynamicQuery(sql);
        Set<String> strings = maps.get(0).keySet();

        resultMap.put("rows", strings);

        return resultMap;
    }

}
