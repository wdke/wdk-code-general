package com.wdk.general.core.utills;

/**
 * @author wdke
 * @date 2019/4/30
 */
public class ColumnsUtil {


    /**
     * 数据库转java字段
     * @param column
     * @param type
     * @return
     */
    public static String columns(String column, String type){

        switch (type){
            case "java" :{
                String[] cols=column.split("_");
                StringBuffer col=new StringBuffer();
                for(int i=0;i<cols.length;i++){
                    if(i==0){
                        col.append(cols[i].toLowerCase());
                    }else{
                        col.append(cols[i].substring(0,1).toUpperCase()+cols[i].substring(1).toLowerCase());
                    }
                }
                return col.toString();
            }
            case "getter":{
                String[] cols=column.split("_");
                StringBuffer col=new StringBuffer();
                for(int i=0;i<cols.length;i++){
                    col.append(cols[i].substring(0,1).toUpperCase()+cols[i].substring(1).toLowerCase());
                }
                return col.toString();
            }
            case "objName":{
                if(column.length()>1){
                    return column.substring(0,1).toLowerCase()+column.substring(1);
                }else {
                    return column.toLowerCase();
                }
            }
            default:
                return column;
        }

    }
}
