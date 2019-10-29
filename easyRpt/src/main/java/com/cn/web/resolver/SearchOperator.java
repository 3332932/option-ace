package com.cn.web.resolver;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * @author QueP
 * @version 1.0
 * @ClassName: SearchOperator
 * @Description:
 * @date 2019/10/9 18:15
 */
public enum SearchOperator {
    eq("等于", "="),
    ne("不等于", "!="),
    gt("大于", ">"),
    gte("大于等于", ">="),
    lt("小于", "<"),
    lte("小于等于", "<="),
    orcGt("大于(适配ORACL)", ">"),
    orcGte("大于等于(适配ORACL)", ">="),
    orcLt("小于(适配ORACL)", "<"),
    orcLte("小于等于(适配ORACL)", "<="),
    prefixLike("前缀模糊匹配", "like"),
    prefixNotLike("前缀模糊不匹配", "not like"),
    suffixLike("后缀模糊匹配", "like"),
    suffixNotLike("后缀模糊不匹配", "not like"),
    like("模糊匹配", "like"),
    notLike("不匹配", "not like"),
    isNull("空", "is null"),
    isNotNull("非空", "is not null"),
    in("包含", "in"),
    notIn("不包含", "not in"),
    custom("自定义默认的", (String)null),
    orderby("自定义排序", "orderby"),
    ASC("自定义排序", "asc");

    private final String info;
    private final String symbol;

    private SearchOperator(String info, String symbol) {
        this.info = info;
        this.symbol = symbol;
    }

    public String getInfo() {
        return this.info;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public static String toStringAllOperator() {
        return Arrays.toString(values());
    }

    public static boolean isAllowBlankValue(SearchOperator operator) {
        return operator == isNotNull || operator == isNull;
    }

    private static String formatSymbol(String symbol) {
        return StringUtils.isBlank(symbol) ? symbol : symbol.trim().toLowerCase().replace("  ", " ");
    }
}
