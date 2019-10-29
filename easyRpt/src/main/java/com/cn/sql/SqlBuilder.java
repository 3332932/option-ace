package com.cn.sql;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.SqlUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

public class SqlBuilder {
    private StringBuilder select = new StringBuilder();
    private StringBuilder from = new StringBuilder();
    private StringBuilder where = new StringBuilder();
    private StringBuilder orderBy = new StringBuilder();
    private StringBuilder groupBy = new StringBuilder();
    private StringBuilder having = new StringBuilder();
    private StringBuilder limit = new StringBuilder();


    public SqlBuilder() {

    }

    public void eq(String column, Object param) {
        String format;
        if (param instanceof String) {
            format = String.format("%s = '%s'",column, param);
        } else {
            format = String.format("%s >= {0}", column, param);
        }
            this.appendWhere(format);

    }


    public void in(String column, String value) {
        List<String> args = com.baomidou.mybatisplus.toolkit.StringUtils.splitWorker(value, ",", -1, false);
        String s = this.inExpression(column, args, false);
        String format = MessageFormat.format(s, args.toArray());
        if (StringUtils.isNotEmpty(this.where)) {
            this.where.append(" and ").append(format);
        } else {
            this.where.append(format);
        }
    }

    public void notIn(String column, String value) {
        List<String> args = com.baomidou.mybatisplus.toolkit.StringUtils.splitWorker(value, ",", -1, false);
        String s = this.inExpression(column, args, true);
        String format = MessageFormat.format(s, args.toArray());
        if (StringUtils.isNotEmpty(this.where)) {
            this.where.append(" and ").append(format);
        } else {
            this.where.append(format);
        }
    }


    public void between(String column, String val1, String val2) {


        Object[] objects = {column, val1, val2};
        String format = String.format("%s BETWEEN '%s' AND '%s'", objects);
        if (StringUtils.isNotEmpty(this.where)) {
            this.where.append(" and ").append(format);
        } else {
            this.where.append(format);
        }

    }

    public void notBetween(String column, Object val1, Object val2) {


        Object[] objects = {column, val1, val2};
        String format = MessageFormat.format("%s NOT BETWEEN '%s' AND '%s'", objects);
        if (StringUtils.isNotEmpty(this.where)) {
            this.where.append(" and ").append(format);
        } else {
            this.where.append(format);
        }

    }


    public void like(boolean condition, String column, String value) {
        if (condition) {
            this.handerLike(column, value, SqlLike.DEFAULT, false);
        }


    }

    public void like(String column, String value) {
        this.handerLike(column, value, SqlLike.DEFAULT, false);
    }

    public void like(String column, SqlLike sqlLike, String value) {
        this.handerLike(column, value, sqlLike, false);
    }

    public void notLike(String column, String value) {
        this.handerLike(column, value, SqlLike.DEFAULT, true);

    }

    public void notLike(String column, SqlLike sqlLike, String value) {
        this.handerLike(column, value, sqlLike, true);

    }


    public void gt(String column, Object params) {
        Object[] obj = {column, params};
        String format;
        if (params instanceof String) {
            format = String.format("%s > '%s'", obj);
        } else {
            format = String.format("%s > {0}", obj);
        }
        this.appendWhere(format);

    }

    public void ge(String column, Object params) {
        Object[] obj = {column, params};
        String format;
        if (params instanceof String) {
            format = String.format("%s >= '%s'", obj);
        } else {
            format = String.format("%s >= {0}", obj);
        }
        this.appendWhere(format);

    }

    public void lt(String column, Object params) {
        Object[] obj = {column, params};
        String format;
        if (params instanceof String) {
            format = String.format("%s < '%s'", obj);
        } else {
            format = String.format("%s < {0}", obj);
        }
        this.appendWhere(format);

    }

    public void le(String column, Object params) {
        Object[] obj = {column, params};
        String format;
        if (params instanceof String) {
            format = String.format("%s <= '%s'", obj);
        } else {
            format = String.format("%s <= {0}", obj);
        }
        this.appendWhere(format);

    }


    private void handerLike(String column, String value, SqlLike type, boolean isNot) {
        StringBuilder inSql = new StringBuilder();
        inSql.append(column);
        if (isNot) {
            inSql.append(" NOT");
        }

        inSql.append(" LIKE {0}");
        String format = MessageFormat.format(inSql.toString(), SqlUtils.concatLike(value, type));
        this.appendWhere(format);

    }


    private String inExpression(String column, Collection<?> value, boolean isNot) {
        if (com.baomidou.mybatisplus.toolkit.StringUtils.isNotEmpty(column) && CollectionUtils.isNotEmpty(value)) {
            StringBuilder inSql = new StringBuilder();
            inSql.append(column);
            if (isNot) {
                inSql.append(" NOT");
            }

            inSql.append(" IN ");
            inSql.append("(");
            int size = value.size();

            for (int i = 0; i < size; ++i) {
                inSql.append(String.format("{%s}", i));
                if (i + 1 < size) {
                    inSql.append(",");
                }
            }

            inSql.append(")");
            return inSql.toString();
        } else {
            return null;
        }
    }

    public StringBuilder getSelect() {
        return select;
    }

    public void appendSelect(String select) {
        this.select.append(select);
    }

    public StringBuilder getFrom() {
        return from;
    }

    public void appendFrom(String from) {
        this.from.append(from);
    }

    public StringBuilder getWhere() {
        return where;
    }

    public void appendWhere(String where) {
        if (StringUtils.isEmpty(where)) {
            return;
        }
        where = where.trim();
        if (StringUtils.isNotBlank(this.where.toString().trim())) {
            if (where.startsWith("and") && this.where.toString().endsWith("and")) {

                this.where.append(where.substring("and".length()));
            } else if (!where.startsWith("and") && !this.where.toString().endsWith("and")) {
                this.where.append(" and ").append(where);
            } else {
                this.where.append(where);
            }
        } else {
            if (where.startsWith("and")) {
                this.where.append(where.substring("and".length()));
            } else {
                this.where.append(where);
            }
        }
    }

    public StringBuilder getOrderBy() {
        return orderBy;
    }

    public void appendOrderBy(String orderBy) {
        this.orderBy.append(orderBy);
    }

    public StringBuilder getLimit() {
        return limit;
    }

    public void appendLimit(String limit) {
        this.limit.append(limit);
    }

    public StringBuilder getGroupBy() {
        return groupBy;
    }

    public void appendGroupBy(String groupBy) {
        this.groupBy.append(groupBy);
    }

    public StringBuilder getHaving() {
        return having;
    }

    public void appendHaving(String having) {
        this.having.append(having);
    }

    public String toCount() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ").append("count(1) as total ");
        stringBuilder.append(" from ").append(from);
        if (StringUtils.isNotEmpty(this.where.toString().trim())) {
            stringBuilder.append(" where ").append(where);
        }
        return stringBuilder.toString();
    }

    public String build() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select ").append(select);
        stringBuilder.append(" from ").append(from);
        if (StringUtils.isNotEmpty(this.where.toString().trim())) {
            stringBuilder.append(" where ").append(where);
        }
        if (StringUtils.isNotEmpty(this.orderBy.toString().trim())) {
            stringBuilder.append(orderBy);
        }
        if (StringUtils.isNotEmpty(this.limit.toString().trim())) {
            stringBuilder.append(" ").append(limit);
        }
        return stringBuilder.toString();
    }

}
