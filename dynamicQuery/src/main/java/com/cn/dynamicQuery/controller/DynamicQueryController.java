package com.cn.dynamicQuery.controller;

import com.cn.base.utils.ResultMapUtils;
import com.cn.dynamicQuery.service.DynamicQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;





/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ms.x
 * @since 2019-10-23
 */
@Api(" 服务")
@RequestMapping("/query")
@RestController
public class DynamicQueryController {

    @Autowired
    private DynamicQueryService dynamicQueryServiceImpl;

    @ApiOperation("动态查询")
    @GetMapping
    public Map<String,Object> dynamicQuery() {
        String sql ="select * from user";
        List<Map<String, Object>> maps = dynamicQueryServiceImpl.dynamicQuery(sql);
        return ResultMapUtils.getResultMap(maps);
    }


}
