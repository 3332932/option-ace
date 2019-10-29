package com.cn.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseUtil {
    private static Pattern humpPattern = Pattern.compile("[A-Z]");
    public boolean camelToUnderline=true;
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        if(sb.toString().startsWith("_")){
            return sb.substring(1);
        }else{
            return sb.toString();
        }
    }
}
