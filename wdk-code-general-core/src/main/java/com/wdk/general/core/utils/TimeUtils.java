package com.wdk.general.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: wdk
 * @Date: david 2019/06/03
 */
public class TimeUtils {


    /**
     * 把时间改成年月日，时分秒
     * @param time
     * @return
     */
    public static String format(Long time){
        if(null==time||time<=0){
            return "0秒";
        }
        StringBuffer st=new StringBuffer();

        //计算天数
        if(time>(24*60*60*1000)){
            st.append(time/(24*60*60*1000)+"天");
        }
        //计算小时数
        if(time>=60*60*1000){
            st.append(time%(24*60*60*1000)/(60*60*1000)+"小时");
        }
        //计算分钟数
        if(time>=60*1000){
            st.append(time%(60*60*1000)/(60*1000)+"分钟");
        }
        //计算秒数
        st.append(time%(60*1000)/1000.0f+"秒");

        return st.toString();

    }

    public static long now() {
        return System.currentTimeMillis();
    }

    /**
     * @param date
     * @Author: 郭佳
     * @Description:TODO
     * @Date: 2018-06-19 16:59
     */
    public static String getDateFormat(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateFormat = sdf.format(date);
        return dateFormat;
    }

    /**
     * @param date
     * @param format
     * @Author: 郭佳
     * @Description:按照传递的参数进行解析规则
     * @Date: 2018-12-05 14:19
     */
    public static String getDateFormat(Date date, String format) {

        String formtEnd = "yyyy-MM-dd HH:mm:ss";
        if (format != null && format.length() > 0) {
            formtEnd = format;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(formtEnd);
        String dateFormat = sdf.format(date);
        return dateFormat;
    }

}
