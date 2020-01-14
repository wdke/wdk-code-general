package com.wdk.general.core.utils;


import org.apache.commons.lang.StringUtils;

/**
 * @author wdke
 * @date 2019/4/30
 */
public class StringConversionUtil extends StringUtils {


    /**
     * 字符串分割并且首字母大写
     *
     * @param column
     * @param split
     * @return
     */
    public static String splitStitching(String column, String split) {

        if (StringUtils.isEmpty(column)) {
            return null;
        }
        String[] cols = column.split(split);

        if (cols.length == 1) {
            return cols[0].substring(0, 1).toUpperCase() + cols[0].substring(1).toLowerCase();
        }
        StringBuffer col = new StringBuffer();

        for (int i = 0; i < cols.length; i++) {
            col.append(cols[i].substring(0, 1).toUpperCase() + cols[i].substring(1).toLowerCase());
        }
        return col.toString();
    }
}
