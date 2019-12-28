package com.wdk.general.core.utils;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wdke
 * @date 2019/5/15
 */
public class XmlUtil {


    /**
     * xml文档Document转对象
     * @param document  
     * @param map
     * @return
     */
    public static void map(Document document, Map<String, Object> map) {
//        Object obj=null;
        //获取根节点
        Element root = document.getRootElement();
        try {
//            obj=clazz.newInstance();//创建对象
            List<Element> properties=root.elements();
            for(Element pro:properties){
                //获取属性名(首字母大写)
                String propertyname=pro.getName();
                String propertyvalue=pro.getText();
                List<Attribute> attributes=pro.attributes();

                map.put(propertyname,attributes);

//                System.out.println("propertyname::"+propertyname +"\tpro.getData().toString()::"+pro.getData().toString()+"\tpropertyvalue::"+propertyvalue);

//                Method m = obj.getClass().getMethod("set"+propertyname,String.class);
//                m.invoke(obj,propertyvalue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        return obj;
    }
    /**
     * xml文档Document转对象
     * @param document  
     * @param clazz
     * @return
     */
    public static Object getObject(Document document, Class<?> clazz) {
        Object obj=null;
        //获取根节点
        Element root = document.getRootElement();
        try {
            obj=clazz.newInstance();//创建对象
            List<Element> properties=root.elements();
            for(Element pro:properties){
//获取属性名(首字母大写)
                String propertyname=pro.getName();
                String propertyvalue=pro.getText();
                Method m = obj.getClass().getMethod("set"+propertyname, String.class);
                m.invoke(obj,propertyvalue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    /**
     * xml字符串转对象
     * @param xmlString  
     * @param clazz
     * @return
     */
    public static Object getObject(String xmlString, Class<?> clazz) throws Exception {
        Document document=null;
        try {
            document = DocumentHelper.parseText(xmlString);
        } catch (DocumentException e) {
            throw new Exception("获取Document异常"+xmlString);
        }
        //获取根节点
        return getObject(document,clazz);
    }

    /**
     * 对象转xml文件
     * @param b
     * @return
     */
    public static Document getDocument(Object b) {
        Document document = DocumentHelper.createDocument();
        try {
           // 创建根节点元素
            Element root = document.addElement(b.getClass().getSimpleName());
            Field[] field = b.getClass().getDeclaredFields(); // 获取实体类b的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) { // 遍历所有有属性
                String name = field[j].getName(); // 获取属属性的名字
                if (!name.equals("serialVersionUID")) {//去除串行化序列属性
                    name = name.substring(0, 1).toUpperCase()
                            + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
                    Method m = b.getClass().getMethod("get" + name);
                    // System.out.println("属性get方法返回值类型:" + m.getReturnType());
                    String propertievalue = (String) m.invoke(b);// 获取属性值
                    Element propertie = root.addElement(name);
                    propertie.setText(propertievalue);

                }
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return document;
    }


    /**
     * 对象转xml文件
     * @param b
     * @return
     */
    public static void getDocument(Element root, Object b) {
        try {
            // 创建根节点元素
            Field[] field = b.getClass().getDeclaredFields(); // 获取实体类b的所有属性，返回Field数组
            for (int j = 0; j < field.length; j++) { // 遍历所有有属性
                String column = field[j].getName(); // 获取属属性的名字
                if (!column.equals("serialVersionUID")) {//去除串行化序列属性
                    String name = column.substring(0, 1).toUpperCase()
                            + column.substring(1); // 将属性的首字符大写，方便构造get，set方法
                    Method m = b.getClass().getMethod("get" + name);
                    Object propertievalue = m.invoke(b);// 获取属性值
                    if(null!=propertievalue){
                        root.addAttribute(column,(String)propertievalue);
                    }
                }
            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 对象转xml格式的字符串
     * @param b
     * @return
     */
    public static String getXmlString(Object b){
        return getDocument(b).asXML();
    }

    public static void main(String[] args) throws DocumentException {

        Document document =null;
        //创建读取文件内容对象
        SAXReader reader=new SAXReader();
        //指定文件并读取
        document=reader.read("/Users/ounoboruka/person/java-code/parents_project/code-general-core/xml/TMenuMapper.xml");
        Map<String, Object> map=new HashMap<>();
        map(document,map);
        map.forEach((key,val)->{
//            System.out.println("key="+key+"\tval="+val);
            for(Attribute attribute:(List<Attribute>)val){

                System.out.println("nodeName="+key+"\tattributeName="+attribute.getName()+"\tval="+attribute.getValue());

            }
//            List<Attribute>

        });
//        Dom4jUtil.IteratorsXML();
    }
}
