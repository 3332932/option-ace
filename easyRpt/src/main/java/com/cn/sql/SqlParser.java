package com.cn.sql;

import org.apache.ibatis.jdbc.SQL;

import java.util.StringTokenizer;

/**
 * sql解析
 * 
 * @author luo.yz
 * @Date Jun 19, 2019 3:12:54 PM
 */
public class SqlParser {

	private SqlParser() {
	}

	/**
	 * 解析回建造者
	 * 
	 * @author luo.yz
	 * @Date Jun 19, 2019 3:16:04 PM
	 */
	public static SqlBuilder parse(String baseSql) {
		// 查询关键字 select from where group by having order by 判断包含部分
		SQL sql = new SQL();
		SqlBuilder sqlBuilder = new SqlBuilder();
		baseSql = format(baseSql);

		int selectIndex = baseSql.toLowerCase().indexOf("select ");
		int fromIndex = baseSql.toLowerCase().indexOf(" from ");
		int whereIndex = baseSql.toLowerCase().indexOf(" where ");
		int groupByIndex = baseSql.toLowerCase().indexOf(" group by ");
		int havingIndex = baseSql.toLowerCase().indexOf(" having ");
		int orderByIndex = baseSql.toLowerCase().indexOf(" order by ");

		// 切割select部分
		String selectSegment = baseSql.substring(selectIndex + 7, fromIndex);
		sqlBuilder.appendSelect(selectSegment);

		// 切割from部分
		int fromEndIndex = getEndIndex(baseSql.length(), whereIndex, groupByIndex, havingIndex, orderByIndex);
		String fromSegment = baseSql.substring(fromIndex + 6, fromEndIndex);
		sqlBuilder.appendFrom(fromSegment);

		// 切割where部分
		if (whereIndex != -1) {
			int whereEndIndex = getEndIndex(baseSql.length(), groupByIndex, havingIndex, orderByIndex);
			String whereSegment = baseSql.substring(whereIndex + 7, whereEndIndex);
			sqlBuilder.appendWhere(whereSegment);
		}

		// 切割group by部分
		if (groupByIndex != -1) {
			int groupByEndIndex = getEndIndex(baseSql.length(), havingIndex, orderByIndex);
			String groupBySegment = baseSql.substring(groupByIndex + 10, groupByEndIndex);
			sqlBuilder.appendGroupBy(groupBySegment);
		}

		// 切割group by部分
		if (havingIndex != -1) {
			int havingEndIndex = getEndIndex(baseSql.length(), orderByIndex);
			String havingSegment = baseSql.substring(havingIndex + 8, havingEndIndex);
			sqlBuilder.appendHaving(havingSegment);
		}

		// 切割order by部分
		if (orderByIndex != -1) {
			int orderByEndIndex = baseSql.length();
			String orderBySegment = baseSql.substring(orderByIndex + 10, orderByEndIndex);
			sqlBuilder.appendOrderBy(orderBySegment);
		}

		return sqlBuilder;
	}

	/**
	 * 格式化sql 去掉多余的回车空格换行制表符等
	 * 
	 * @author luo.yz
	 * @Date Jun 20, 2019 9:49:28 AM
	 * @param baseSql
	 * @return
	 */
	private static String format(String baseSql) {
		// 去除\t\n\r\f
		StringTokenizer tokenizer = new StringTokenizer(baseSql);
		StringBuilder builder = new StringBuilder();
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			builder.append(token);
			if (tokenizer.hasMoreTokens()) {
				builder.append(" ");
			}
		}
		return builder.toString();
	}

	/**
	 * 获取非-1里最小的下标
	 * 
	 * @author luo.yz
	 * @Date Jun 19, 2019 5:15:35 PM
	 * @param ints
	 * @return
	 */
	private static int getEndIndex(Integer... ints) {
		int smallest = -1;
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] != -1) {
				if (smallest == -1) {
					smallest = ints[i];
				} else {
					smallest = smallest < ints[i] ? smallest : ints[i];
				}
			}
		}
		return smallest;
	}

}
