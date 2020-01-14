package com.wdk.general.core.handle.impl;

import com.wdk.general.core.handle.IResultSetHandler;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * created by wdk on 2019/12/11
 */
public class BeanHandler<T> implements IResultSetHandler<List<T>> {

    // 创建字节码对象
    private Class<T> clazz;

    // 创建有参构造函数，用于传入具体操作对象的类型
    public BeanHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 数据库集操作类
     *
     * @param rSet 数据库处理结果集
     * @return 数据库结果集 List 集合
     */
    @Override
    public List<T> handler(ResultSet rSet) {
        // 创建 List 用于存放装箱后的对象
        List<T> list = new ArrayList<T>();
        try {
            // 获取类的属性描述符
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz, Object.class);
            PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
            // 对结果集进行装箱操作
            while (rSet.next()) {
                T obj = clazz.newInstance();
                for (PropertyDescriptor descriptor : descriptors) {
                    Object value =null;
                    try{
                        value = rSet.getObject(descriptor.getName());
                    }catch (Exception e){
//                        e.printStackTrace();
                        continue;
                    }
                    if (null == value) {
                        continue;
                    }
                    Class<?> propertyType = descriptor.getPropertyType();

                    if(propertyType.equals(value.getClass())){
                        descriptor.getWriteMethod().invoke(obj, value);
                    }else if(value.getClass().equals(BigInteger.class)){
                        descriptor.getWriteMethod().invoke(obj, ((BigInteger)value).longValue());
                    }else{

                        System.out.println(propertyType + "\t" + value.getClass());
                        System.out.println(descriptor.getName() + "\t" + value);
                    }
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}