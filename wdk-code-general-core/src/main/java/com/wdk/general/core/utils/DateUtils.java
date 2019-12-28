package com.wdk.general.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wdke
 * @date 2019/5/30
 */
public class DateUtils {

    /**
     * 根据format 生成格式
     * @param format
     * @return
     */
    public static String nowFormat(String format){
        SimpleDateFormat sdf=new SimpleDateFormat(format);

        return sdf.format(new Date());
    }



}
