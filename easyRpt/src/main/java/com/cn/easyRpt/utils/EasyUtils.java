package com.cn.easyRpt.utils;

import org.apache.commons.lang3.StringUtils;

public class EasyUtils {


    public static boolean isDMLOrDCLOrOrDDL(final String sql) {
        String newSql = sql.toLowerCase().trim();

        return newSql.startsWith("update ") || newSql.startsWith("insert ")
                || newSql.startsWith("delete ") || newSql.startsWith("grant ") || newSql.startsWith("create ");
    }


    /**
     * add where 1 = 0
     *
     * @param sql 语句
     * @return
     */
    public static String sqlWhereHandler(String sql) {
        if (StringUtils.isBlank(sql)) {
            return sql;
        }
        String newSql = sql;
        String where = "where";
        if (!sql.toLowerCase().contains(where)) {
            newSql = newSql + " where 1 = 0";
        } else {
            newSql = newSql + " AND 1 = 0";
        }
        return newSql;
    }
}
