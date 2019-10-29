import com.cn.Cols;
import com.cn.EasyRptExport;
import com.cn.Where;
import com.cn.base.utils.BaseUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateTable {
    public static Map<String, String> javaProperty2SqlColumnMap = new HashMap<>();

  
    static {  
        javaProperty2SqlColumnMap.put("Integer_key", "INTEGER NOT NULL AUTO_INCREMENT COMMENT '主键'");
        javaProperty2SqlColumnMap.put("Long_key", "bigint NOT NULL AUTO_INCREMENT COMMENT '主键'");
        javaProperty2SqlColumnMap.put("Integer", "INTEGER DEFAULT NULL COMMENT ''");
        javaProperty2SqlColumnMap.put("Short", "tinyint DEFAULT NULL COMMENT ''");
        javaProperty2SqlColumnMap.put("Long", "bigint DEFAULT NULL COMMENT ''");
        javaProperty2SqlColumnMap.put("BigDecimal", "decimal(19,2) default null COMMENT ''");
        javaProperty2SqlColumnMap.put("Double", "double precision not null");  
        javaProperty2SqlColumnMap.put("Float", "float DEFAULT NULL COMMENT ''");
        javaProperty2SqlColumnMap.put("Boolean", "bit DEFAULT NULL COMMENT ''");
        javaProperty2SqlColumnMap.put("Timestamp", "datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''");
        javaProperty2SqlColumnMap.put("Date", "datetime DEFAULT CURRENT_TIMESTAMP COMMENT ''");
        javaProperty2SqlColumnMap.put("String", "VARCHAR(255) DEFAULT NULL COMMENT ''");
    }  
  
    /** 
     * @param args 
     * @throws IOException 
     */  
    public static void main(String[] args) throws IOException {
        createTable(Where.class, null);
    }  
  
    public static String createTable(Class obj, String tableName) throws IOException {
        Field[] fields = obj.getDeclaredFields();
        String param = null;
        String column = null;  
        StringBuilder sb = new StringBuilder(50);
        if (tableName == null || tableName.equals("")) {  
            tableName = obj.getName();
            tableName = tableName.substring(tableName.lastIndexOf(".") + 1);  
        }  
        sb.append("create table ").append(BaseUtil.humpToLine(tableName)).append(" ( \r\n");
        boolean isFirstId = true;
        String firstId = "";
        for (Field f : fields) {
            column = f.getName();  
            if (column.equals("serialVersionUID")) {  
                continue;  
            }  
            param = f.getType().getSimpleName();
            if (StringUtils.isEmpty(javaProperty2SqlColumnMap.get(param))){
                //如果字段类型不在定义的范围内，不生成sql
                continue;
            }
            sb.append(" `").append(BaseUtil.humpToLine(column)).append("`");
            if (isFirstId) {//类型转换
                firstId=column;
                isFirstId = false;
                sb.append(" ").append(javaProperty2SqlColumnMap.get(param+"_key")).append(" ");
            }else {
                sb.append(" ").append(javaProperty2SqlColumnMap.get(param)).append(" ");
            }
            sb.append(",\n");
        }
        sb.append(" PRIMARY KEY(`").append(firstId).append("`) ");
        String sql = sb.toString();
        sql = sql.substring(0, sql.length() - 1) + "\n)ENGINE =INNODB DEFAULT  CHARSET= utf8;\r\n";
        System.out.println(sql);
        return sql;  

    }
} 