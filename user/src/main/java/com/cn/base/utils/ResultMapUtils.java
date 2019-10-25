package com.cn.base.utils;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yshh44
 */
public class ResultMapUtils {


    /**
     * 返回码 0
     * 返回信息 success
     *
     * @return
     */
    public static Map<String, Object> getSuccessResultMap() {
        return getResultMap("0", "success");
    }

    /**
     * 返回码 -1
     * 返回信息 service error
     *
     * @return
     */
    public static Map<String, Object> getErrorResultMap() {
        return getResultMap("-1", "service error");
    }
    /**
     * 返回码 -1
     * 返回信息 service error
     *
     * @return
     */
    public static Map<String, Object> getErrorResultMap(String error) {
        return getResultMap("202", error);
    }
    /**
     * 返回码 0
     * 返回信息 success
     * 返回数据  data
     * 返回数据条数 total
     *
     * @return
     */
    public static Map<String, Object> getPageResult(Object data, Long total) {
        return getResultMap("0", "success", data, total);
    }
    public static Map<String, Object> getPageResult(IPage page) {
        return getResultMap("0", "success", page.getRecords(), page.getTotal());
    }

    /**
     * 返回码 0
     * 返回信息 success
     * 返回数据  data
     *
     * @return
     */
    public static Map<String, Object> getResultMap(Object data) {
        return getResultMap("0", "success", data, null);
    }

    /**
     * 返回码 ？
     * 返回信息 ？
     * 返回数据  data
     *
     * @return
     */
    public static Map<String, Object> getResultMap(String retCode, String retMsg, Object data) {
        return getResultMap(retCode, retMsg, data, null);
    }

    public static Map<String, Object> getResultMap(String retCode, String retMsg) {
        return getResultMap(retCode, retMsg, null, null);
    }

    public static Map<String, Object> getResultMap(String retCode, String retMsg, Object data, Long total) {
        Map<String, Object> map = new HashMap<>(8);
        Map<String, Object> flag = new HashMap<>(8);
        flag.put("retCode", retCode);
        flag.put("retMsg", retMsg);
        map.put("flag", flag);
        if (data != null) {
            map.put("rows", data);
        }
        if (total != null) {
            map.put("total", total);
        }
        return map;
    }

}
