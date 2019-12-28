package com.wdk.general.core.utils;

import com.wdk.general.core.model.ProjectMetadata;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * created by wdk on 2019/12/16
 */
public class CommonFileUtils {

    private static Logger logger = LoggerFactory.getLogger(ColumnsUtil.class);

    /**
     * 生成所有的文件
     *
     * @param rootFilePath
     * @param rootPackages
     */
    public static void all(String rootFilePath, String rootPackages) {

        //生成AppConfigurer.java文件
        String fileContent = appConfigurer(rootPackages + ".common.config");
        String filePath = rootFilePath + "/common/config/AppConfigurer.java";
        try {

            logger.info("生成appConfigurer.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //生成PageParam.java文件
        fileContent = pageParam(rootPackages + ".common.model");
        filePath = rootFilePath + "/common/model/PageParam.java";
        try {

            logger.info("生成PageParam.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //生成ResponseVo.java文件
        fileContent = responseVo(rootPackages + ".common.model", rootPackages + ".common.enums");
        filePath = rootFilePath + "/common/model/ResponseVo.java";
        try {

            logger.info("生成ResponseVo.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成ReturnStatusEnum.java文件
        fileContent = returnStatusEnum(rootPackages + ".common.enums");
        filePath = rootFilePath + "/common/enums/ReturnStatusEnum.java";
        try {

            logger.info("生成ReturnStatusEnum.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //生成 ResponseStatusEnum.java文件
        fileContent = responseStatusEnum(rootPackages + ".common.enums");
        filePath = rootFilePath + "/common/enums/ResponseStatusEnum.java";
        try {

            logger.info("生成 ResponseStatusEnum.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成DateUtils.java文件
        fileContent = dateUtils(rootPackages + ".utils");
        filePath = rootFilePath + "/utils/DateUtils.java";
        try {

            logger.info("生成DateUtils.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成 TimeUtils.java文件
        fileContent = timeUtils(rootPackages + ".utils");
        filePath = rootFilePath + "/utils/TimeUtils.java";
        try {

            logger.info("生成 TimeUtils.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成 PasswordUtil.java文件
        fileContent = passwordUtil(rootPackages + ".utils");
        filePath = rootFilePath + "/utils/PasswordUtil.java";
        try {

            logger.info("生成 PasswordUtil.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成 RedisConfig.java文件
        fileContent = redisConfig(rootPackages + ".common.config");
        filePath = rootFilePath + "/common/config/RedisConfig.java";
        try {

            logger.info("生成 RedisConfig.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //生成 JwtUtils.java文件
        fileContent = jwtUtils(rootPackages + ".utils");
        filePath = rootFilePath + "/utils/JwtUtils.java";
        try {

            logger.info("生成 JwtUtils.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成 RedisStringDao.java文件
        fileContent = redisStringDao(rootPackages + ".redis");
        filePath = rootFilePath + "/redis/RedisStringDao.java";
        try {

            logger.info("生成 RedisStringDao.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //生成 AccessLog.java文件
        fileContent = accessLog(rootPackages + ".common.logger");
        filePath = rootFilePath + "/common/logger/AccessLog.java";
        try {

            logger.info("生成 AccessLog.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //生成 TalStopWatch.java文件
        fileContent = talStopWatch(rootPackages + ".common.logger");
        filePath = rootFilePath + "/common/logger/TalStopWatch.java";
        try {

            logger.info("生成 TalStopWatch.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //生成 EventLog.java文件
        fileContent = eventLog(rootPackages + ".common.logger");
        filePath = rootFilePath + "/common/logger/EventLog.java";
        try {

            logger.info("生成 EventLog.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //生成 ActionAop.java文件
        fileContent = actionAop(rootPackages + ".common.aop");
        filePath = rootFilePath + "/common/aop/ActionAop.java";
        try {

            logger.info("生成 ActionAop.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成 WebAppConfig.java文件
        fileContent = webAppConfig(rootPackages + ".common.config");
        filePath = rootFilePath + "/common/config/WebAppConfig.java";
        try {

            logger.info("生成 WebAppConfig.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成 TokenInterceptor.java文件
        fileContent = tokenInterceptor(rootPackages + ".web.interceptor");
        filePath = rootFilePath + "/web/interceptor/TokenInterceptor.java";
        try {

            logger.info("生成 TokenInterceptor.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //生成 UserContext.java文件
        fileContent = userContext(rootPackages + ".web.interceptor");
        filePath = rootFilePath + "/web/interceptor/UserContext.java";
        try {

            logger.info("生成 UserContext.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }



        //生成 LoginArgs.java文件
        fileContent = loginArgs(rootPackages + ".web.args");
        filePath = rootFilePath + "/web/args/LoginArgs.java";
        try {

            logger.info("生成 LoginArgs.java文件");
            FileUtil.write(filePath, fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 生成跨域配置
     *
     * @param packages
     * @return
     */
    public static String appConfigurer(String packages) {

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(";\n")
                .append("import org.springframework.boot.web.servlet.FilterRegistrationBean;\n")
                .append("import org.springframework.context.annotation.Bean;\n")
                .append("import org.springframework.context.annotation.Configuration;\n")
                .append("import org.springframework.web.cors.CorsConfiguration;\n")
                .append("import org.springframework.web.cors.UrlBasedCorsConfigurationSource;\n")
                .append("import org.springframework.web.filter.CorsFilter;\n")
                .append("\n")
                .append("@Configuration\n")
                .append("public class AppConfigurer {\n")
                .append("\n")
                .append("\t@Bean\n")
                .append("\tpublic FilterRegistrationBean corsFilter() {\n")
                .append("\t\tUrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();\n")
                .append("\t\tCorsConfiguration config = new CorsConfiguration();\n")
                .append("\t\tconfig.setAllowCredentials(true);\n")
                .append("\t\t// 设置你要允许的网站域名，如果全允许则设为 *\n")
                .append("\t\tconfig.addAllowedOrigin(\"*\");\n")
                .append("\t\t// 如果要限制 HEADER 或 METHOD 请自行更改\n")
                .append("\t\tconfig.addAllowedHeader(\"*\");\n")
                .append("\t\tconfig.addAllowedMethod(\"*\");\n")
                .append("\t\tsource.registerCorsConfiguration(\"/**\", config);\n")
                .append("\t\tFilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));\n")
                .append("\t\t// 这个顺序很重要哦，为避免麻烦请设置在最前\n")
                .append("\t\tbean.setOrder(0);\n")
                .append("\t\treturn bean;\n")
                .append("\t}\n")
                .append("}\n\n");

        return sb.toString();

    }


    /**
     * 生成分页参数
     *
     * @param packages
     * @return
     */
    public static String pageParam(String packages) {

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("/**\n")
                .append(" * @author wdke\n")
                .append(" * @date 2018/10/26\n")
                .append(" */\n")
                .append("public class PageParam {\n")
                .append("\tInteger pageNum=1;\n")
                .append("\n")
                .append("\tInteger pageSize=10;\n")
                .append("\n")
                .append("\tpublic Integer getPageNum() {\n")
                .append("\t\treturn pageNum;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setPageNum(Integer pageNum) {\n")
                .append("\t\tthis.pageNum = pageNum;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic Integer getPageSize() {\n")
                .append("\t\treturn pageSize;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setPageSize(Integer pageSize) {\n")
                .append("\t\tthis.pageSize = pageSize;\n")
                .append("\t}\n")
                .append("}\n");

        return sb.toString();

    }


    /**
     * 返回值类型
     *
     * @param packages
     * @param enumsPackages
     * @return
     */
    public static String responseVo(String packages, String enumsPackages) {

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("import com.fasterxml.jackson.annotation.JsonIgnore;\n")
                .append("import com.fasterxml.jackson.annotation.JsonInclude;\n")
                .append("import ").append(enumsPackages).append(".*;\n")
                .append("\n")
                .append("import java.io.Serializable;\n")
                .append("\n")
                .append("/**\n")
                .append(" * Created by wdke on 1541852927067.\n")
                .append(" */\n")
                .append("@JsonInclude(JsonInclude.Include.NON_NULL)\n")
                .append("public class ResponseVo<T> implements Serializable {\n")
                .append("\n")
                .append("\n")
                .append("\n")
                .append("\tprivate static final long serialVersionUID = 3073226328007685525L;\n")
                .append("\t\n")
                .append("\tprivate int code;\n")
                .append("\n")
                .append("\tprivate String msg;\n")
                .append("\n")
                .append("\tprivate T data;\n")
                .append("\n")
                .append("\n")
                .append("\tpublic ResponseVo() {\n")
                .append("\t  \n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic ResponseVo(ResponseStatusEnum status) {\n")
                .append("\t\tthis.code = status.getCode();\n")
                .append("\t\tthis.msg = status.getMsg();\n")
                .append("\t}\n")
                .append("\n")
                .append("\n")
                .append("\tpublic ResponseVo(ResponseStatusEnum status, T data) {\n")
                .append("\n")
                .append("\t\tthis.code = status.getCode();\n")
                .append("\t\tthis.msg = status.getMsg();\n")
                .append("\t\tthis.data = data;\n")
                .append("\t}\n")
                .append("\t\n")
                .append("\tpublic ResponseVo(int status, String message) {\n")
                .append("\t\tthis.code = status;\n")
                .append("\t\tthis.msg = message;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic ResponseVo(int status, String message, T data) {\n")
                .append("\t\tthis.code = status;\n")
                .append("\t\tthis.msg = message;\n")
                .append("\t\tthis.data = data;\n")
                .append("\t}\n")
                .append("\n")
                .append("\n")
                .append("\tpublic ResponseVo(ReturnStatusEnum returnStatus, T data) {\n")
                .append("\t\tthis.code = returnStatus.getValue();\n")
                .append("\t\tthis.msg = returnStatus.getDesc();\n")
                .append("\t\tthis.data = data;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic ResponseVo(ReturnStatusEnum returnStatus) {\n")
                .append("\t\tthis.code = returnStatus.getValue();\n")
                .append("\t\tthis.msg = returnStatus.getDesc();\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic int getCode() {\n")
                .append("\t\treturn code;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setStatus(int status) {\n")
                .append("\t\tthis.code = status;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic String getMsg() {\n")
                .append("\t\treturn msg;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setMsg(String message) {\n")
                .append("\t\tthis.msg = message;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic T getData() {\n")
                .append("\t\treturn data;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setData(T data) {\n")
                .append("\t\tthis.data = data;\n")
                .append("\t}\n")
                .append("\n")
                .append("\n")
                .append("\t@JsonIgnore\n")
                .append("\tpublic boolean isSuccess() {\n")
                .append("\t\treturn getCode() == 200;\n")
                .append("\t}\n")
                .append("}\n");

        return sb.toString();
    }


    /**
     * 返回值enums
     *
     * @param packages
     * @return
     */
    public static String returnStatusEnum(String packages) {
        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(packages).append(";\n")
                .append("import java.util.HashSet;\n")
                .append("\n")
                .append("public enum ReturnStatusEnum {\n")
                .append("\n")
                .append("\tSC_OK(200, \"成功\"),\n")
                .append("\n")
                .append("\tSC_NOT_MODIFIED(304, \"未处理\"),\n")
                .append("\n")
                .append("\tSC_BAD_REQUEST(400, \"服务器不理解请求的语法\"),\n")
                .append("\t\n")
                .append("\tSC_INTERNAL_SERVER_ERROR(500, \"服务器遇到错误\"),\n")
                .append("\n")
                .append("\t/* API状态码  */\n")
                .append("\tAPI_NET_EXCEPTION(521, \"网络异常\"),\n")
                .append("\n")
                .append("\tAPI_INTERFACE_EXCEPTION(548, \"接口调用异常\"),\n")
                .append("\n")
                .append("\tAPI_STATUS_ERROR(549, \"接口返回状态码错误\"),\n")
                .append("\t\n")
                .append("\tAPI_PARAM_INVALID(4001, \"参数非法\"),\n")
                .append("\t\n")
                .append("\tAPI_NO_PERMISSION(4002, \"没有权限\"),\n")
                .append("\t\n")
                .append("\tAPI_DATA_INVALID(5001, \"数据非法\"),\n")
                .append("\t\n")
                .append("\t/* JSON数据解析 */\n")
                .append("\tJSON_ARRAY_PARSE_FAIL(6001, \"JSON数组解析失败\"),\n")
                .append("\n")
                .append("\tJSON_OBJECT_PARSE_FAIL(6002, \"JSON对象解析失败\");\n")
                .append("\t\n")
                .append("\t/** The value. */\n")
                .append("\tprivate final int value;\n")
                .append("\n")
                .append("\t/** The desc. */\n")
                .append("\tprivate final String desc;\n")
                .append("\n")
                .append("\tprivate ReturnStatusEnum(int value, String desc) {\n")
                .append("\t\t this.value = value;\n")
                .append("\t\t this.desc = desc;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic int getValue() {\n")
                .append("\t\t return value;\n")
                .append("\t}\n")
                .append("\t\n")
                .append("\tpublic String getDesc() {\n")
                .append("\t\t return desc;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tprivate static HashSet<Integer> hashSet;\n")
                .append("\n")
                .append("\tstatic {\n")
                .append("\t\t hashSet = new HashSet<Integer>();\n")
                .append("\t\t hashSet.clear();\n")
                .append("\t\t for (ReturnStatusEnum returnStatus : values()) {\n")
                .append("\t\t\t hashSet.add(returnStatus.getValue());\n")
                .append("\t\t }\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic static boolean isDefined(int value) {\n")
                .append("\t\t if (hashSet.contains(value)) {\n")
                .append("\t\t\t return true;\n")
                .append("\t\t }\n")
                .append("\t\t return false;\n")
                .append("\t}\n")
                .append("}\n");

        return sb.toString();
    }


    /**
     * 返回的enums
     *
     * @param packages
     * @return
     */
    public static String responseStatusEnum(String packages) {

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("public enum ResponseStatusEnum {\n")
                .append("\tSUCCESS(200,\"成功\"),\n")
                .append("\tLOGOUT(300,\"未登录\"),\n")
                .append("\tNO_PERMISSIONS(300,\"用户没有权限操作此接口\"),\n")
                .append("\tUSER_ERROR(301,\"用户名不存在\"),\n")
                .append("\tPASSWORD_ERROR(301,\"密码错误\"),\n")
                .append("\tUSER_ISLIVE(301,\"用户名已存在\"),\n")
                .append("\tERROR(201,\"请求错误\"),\n")
                .append("\tPARRAM_ERROR(202,\"参数错误\"),\n")
                .append("\tDATA_REPETITION(203,\"参数错误\"),\n")
                .append("\tDATA_EMPTY(200,\"数据为空\"),\n")
                .append("\tTABLES_EMPTY(204,\"数据表不存在或已经被删除\"),\n")
                .append("\tCOLUMNS_EMPTY(204,\"数据表字段不存在\");\n")
                .append("\n")
                .append("\n")
                .append("\tprivate int code;\n")
                .append("\tprivate String msg;\n")
                .append("\n")
                .append("\tpublic int getCode() {\n")
                .append("\t\treturn code;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic String getMsg() {\n")
                .append("\t\treturn msg;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tResponseStatusEnum(int code, String msg) {\n")
                .append("\t\tthis.code = code;\n")
                .append("\t\tthis.msg = msg;\n")
                .append("\t}\n")
                .append("}\n");

        return sb.toString();

    }

    /**
     * 生成日期工具类
     *
     * @param packages
     * @return
     */
    public static String dateUtils(String packages) {

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("import java.text.SimpleDateFormat;\n")
                .append("import java.util.Date;\n")
                .append("\n")
                .append("/**\n")
                .append(" * @author wdke\n")
                .append(" * @date 2019/5/30\n")
                .append(" */\n")
                .append("public class DateUtils {\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 根据format 生成格式\n")
                .append("\t * @param format\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\tpublic static String nowFormat(String format){\n")
                .append("\t\tSimpleDateFormat sdf=new SimpleDateFormat(format);\n")
                .append("\n")
                .append("\t\treturn sdf.format(new Date());\n")
                .append("\t}\n")
                .append("\n")
                .append("\n")
                .append("\n")
                .append("}\n");

        return sb.toString();

    }


    /**
     * 生成TimeUtils.java
     *
     * @return
     */
    public static String timeUtils(String packages) {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("import java.text.SimpleDateFormat;\n")
                .append("import java.util.Date;\n")
                .append("\n")
                .append("/**\n")
                .append(" * @Author: wdk\n")
                .append(" * @Date: david 2019/06/03\n")
                .append(" */\n")
                .append("public class TimeUtils {\n")
                .append("\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 把时间改成年月日，时分秒\n")
                .append("\t * @param time\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\tpublic static String format(Long time){\n")
                .append("\t\tif(null==time||time<=0){\n")
                .append("\t\t\treturn \"0秒\";\n")
                .append("\t\t}\n")
                .append("\t\tStringBuffer st=new StringBuffer();\n")
                .append("\n")
                .append("\t\t//计算天数\n")
                .append("\t\tif(time>(24*60*60*1000)){\n")
                .append("\t\t\tst.append(time/(24*60*60*1000)+\"天\");\n")
                .append("\t\t}\n")
                .append("\t\t//计算小时数\n")
                .append("\t\tif(time>=60*60*1000){\n")
                .append("\t\t\tst.append(time%(24*60*60*1000)/(60*60*1000)+\"小时\");\n")
                .append("\t\t}\n")
                .append("\t\t//计算分钟数\n")
                .append("\t\tif(time>=60*1000){\n")
                .append("\t\t\tst.append(time%(60*60*1000)/(60*1000)+\"分钟\");\n")
                .append("\t\t}\n")
                .append("\t\t//计算秒数\n")
                .append("\t\tst.append(time%(60*1000)/1000.0f+\"秒\");\n")
                .append("\n")
                .append("\t\treturn st.toString();\n")
                .append("\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic static long now() {\n")
                .append("\t\treturn System.currentTimeMillis();\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * @param date\n")
                .append("\t * @Author: 郭佳\n")
                .append("\t * @Description:TODO\n")
                .append("\t * @Date: 2018-06-19 16:59\n")
                .append("\t */\n")
                .append("\tpublic static String getDateFormat(Date date) {\n")
                .append("\t\tif (date == null) {\n")
                .append("\t\t\treturn \"\";\n")
                .append("\t\t}\n")
                .append("\t\tSimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n")
                .append("\t\tString dateFormat = sdf.format(date);\n")
                .append("\t\treturn dateFormat;\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * @param date\n")
                .append("\t * @param format\n")
                .append("\t * @Author: 郭佳\n")
                .append("\t * @Description:按照传递的参数进行解析规则\n")
                .append("\t * @Date: 2018-12-05 14:19\n")
                .append("\t */\n")
                .append("\tpublic static String getDateFormat(Date date, String format) {\n")
                .append("\n")
                .append("\t\tString formtEnd = \"yyyy-MM-dd HH:mm:ss\";\n")
                .append("\t\tif (format != null && format.length() > 0) {\n")
                .append("\t\t\tformtEnd = format;\n")
                .append("\t\t}\n")
                .append("\n")
                .append("\t\tSimpleDateFormat sdf = new SimpleDateFormat(formtEnd);\n")
                .append("\t\tString dateFormat = sdf.format(date);\n")
                .append("\t\treturn dateFormat;\n")
                .append("\t}\n")
                .append("\n")
                .append("}\n");

        return sb.toString();
    }


    /**
     * 生成密码管理工具
     *
     * @param packages
     * @return
     */
    public static String passwordUtil(String packages) {

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("\n")
                .append("import org.apache.commons.codec.binary.Base64;\n")
                .append("\n")
                .append("import javax.crypto.Cipher;\n")
                .append("import javax.crypto.KeyGenerator;\n")
                .append("import javax.crypto.SecretKey;\n")
                .append("import javax.crypto.spec.SecretKeySpec;\n")
                .append("import java.security.MessageDigest;\n")
                .append("import java.security.SecureRandom;\n")
                .append("\n")
                .append("public class PasswordUtil {\n")
                .append("\t/**\n")
                .append("\t * 密码加密处理（MD5）\n")
                .append("\t * @param src 原密码\n")
                .append("\t * @return 加密后的内容\n")
                .append("\t */\n")
                .append("\tpublic static String md5(String src){\n")
                .append("\t\ttry{//采用MD5处理\n")
                .append("\t\t\tMessageDigest md =\n")
                .append("\t\t\t\t\tMessageDigest.getInstance(\"MD5\");\n")
                .append("\t\t\tbyte[] output = md.digest(\n")
                .append("\t\t\t\t\tsrc.getBytes());//加密处理\n")
                .append("\t\t\t//将加密结果output利用Base64转成字符串输出\n")
                .append("\t\t\tString ret =\n")
                .append("\t\t\t\t\tBase64.encodeBase64String(output);\n")
                .append("\t\t\treturn ret;\n")
                .append("\t\t}catch(Exception e){\n")
                .append("\t\t\treturn \"\";\n")
                .append("\t\t}\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 加密\n")
                .append("\t *\n")
                .append("\t * @param content\n")
                .append("\t *            待加密内容\n")
                .append("\t * @param key\n")
                .append("\t *            加密的密钥\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\tpublic static String encrypt(String content, String key){\n")
                .append("\t\ttry{\n")
                .append("\t\t\tKeyGenerator kgen= KeyGenerator.getInstance(\"AES\");\n")
                .append("\t\t\tSecureRandom secureRandom = SecureRandom.getInstance(\"SHA1PRNG\" );\n")
                .append("\t\t\tsecureRandom.setSeed(key.getBytes());\n")
                .append("\t\t\tkgen.init(128,secureRandom);\n")
                .append("\t\t\tSecretKey secretKey=kgen.generateKey();\n")
                .append("\t\t\tbyte[] enCodeFormat=secretKey.getEncoded();\n")
                .append("\t\t\tSecretKeySpec secretKeySpec=new SecretKeySpec(enCodeFormat,\"AES\");\n")
                .append("\t\t\tCipher cipher= Cipher.getInstance(\"AES\");\n")
                .append("\t\t\tbyte[] byteContent=content.getBytes(\"utf-8\");\n")
                .append("\t\t\tcipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);\n")
                .append("\t\t\tbyte[] byteRresult=cipher.doFinal(byteContent);\n")
                .append("\t\t\tStringBuffer sb=new StringBuffer();\n")
                .append("\t\t\tfor(int i=0;i<byteRresult.length;i++){\n")
                .append("\t\t\t\tString hex= Integer.toHexString(byteRresult[i]&0xFF);\n")
                .append("\t\t\t\tif(hex.length()==1){\n")
                .append("\t\t\t\t\thex='0'+hex;\n")
                .append("\t\t\t\t}\n")
                .append("\t\t\t\tsb.append(hex.toUpperCase());\n")
                .append("\t\t\t}\n")
                .append("\t\t\treturn sb.toString();\n")
                .append("\t\t}catch(Exception e){\n")
                .append("\t\t\te.printStackTrace();\n")
                .append("\t\t}\n")
                .append("\t\treturn null;\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 解密\n")
                .append("\t *\n")
                .append("\t * @param content\n")
                .append("\t *            待解密内容\n")
                .append("\t * @param key\n")
                .append("\t *            解密的密钥\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\tpublic static String decrypt(String content, String key){\n")
                .append("\t\tif(content.length()<1)\n")
                .append("\t\t\treturn null;\n")
                .append("\t\tbyte[] byteRresult=new byte[content.length()/2];\n")
                .append("\t\tfor(int i=0;i<content.length()/2;i++){\n")
                .append("\t\t\tint high= Integer.parseInt(content.substring(i*2,i*2+1),16);\n")
                .append("\t\t\tint low= Integer.parseInt(content.substring(i*2+1,i*2+2),16);\n")
                .append("\t\t\tbyteRresult[i]=(byte)(high*16+low);\n")
                .append("\t\t}\n")
                .append("\t\ttry{\n")
                .append("\t\t\tKeyGenerator kgen= KeyGenerator.getInstance(\"AES\");\n")
                .append("\t\t\tSecureRandom secureRandom = SecureRandom.getInstance(\"SHA1PRNG\" );\n")
                .append("\t\t\tsecureRandom.setSeed(key.getBytes());\n")
                .append("\t\t\tkgen.init(128,secureRandom);\n")
                .append("\t\t\tSecretKey secretKey=kgen.generateKey();\n")
                .append("\t\t\tbyte[] enCodeFormat=secretKey.getEncoded();\n")
                .append("\t\t\tSecretKeySpec secretKeySpec=new SecretKeySpec(enCodeFormat,\"AES\");\n")
                .append("\t\t\tCipher cipher= Cipher.getInstance(\"AES\");\n")
                .append("\t\t\tcipher.init(Cipher.DECRYPT_MODE,secretKeySpec);\n")
                .append("\t\t\tbyte[] result=cipher.doFinal(byteRresult);\n")
                .append("\t\t\treturn new String(result);\n")
                .append("\t\t}catch(Exception e){\n")
                .append("\t\t\te.printStackTrace();\n")
                .append("\t\t}\n")
                .append("\t\treturn null;\n")
                .append("\t}\n")
                .append("}\n")
                .append("\n");

        return sb.toString();

    }


    /**
     * 生成redis的配置文件
     *
     * @param packages
     * @return
     */
    public static String redisConfig(String packages) {

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(";\n\n")
                .append("import com.fasterxml.jackson.annotation.JsonAutoDetect;\n")
                .append("import com.fasterxml.jackson.annotation.PropertyAccessor;\n")
                .append("import com.fasterxml.jackson.databind.ObjectMapper;\n")
                .append("import org.springframework.cache.annotation.EnableCaching;\n")
                .append("import org.springframework.context.annotation.Bean;\n")
                .append("import org.springframework.context.annotation.Configuration;\n")
                .append("import org.springframework.data.redis.connection.RedisConnectionFactory;\n")
                .append("import org.springframework.data.redis.core.*;\n")
                .append("import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;\n")
                .append("import org.springframework.data.redis.serializer.StringRedisSerializer;\n")
                .append("\n\n")
                .append("@Configuration\n")
                .append("@EnableCaching\n")
                .append("public class RedisConfig {\n")
                .append("\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * retemplate相关配置\n")
                .append("\t *\n")
                .append("\t * @param factory\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\t@Bean\n")
                .append("\tpublic RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {\n")
                .append("\n")
                .append("\t\tRedisTemplate<String, Object> template = new RedisTemplate<>();\n")
                .append("\t\t// 配置连接工厂\n")
                .append("\t\ttemplate.setConnectionFactory(factory);\n")
                .append("\n")
                .append("\t\t//使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）\n")
                .append("\t\tJackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);\n")
                .append("\n")
                .append("\t\tObjectMapper om = new ObjectMapper();\n")
                .append("\t\t// 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public\n")
                .append("\t\tom.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);\n")
                .append("\t\t// 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常\n")
                .append("\t\tom.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);\n")
                .append("\t\tjacksonSeial.setObjectMapper(om);\n")
                .append("\n")
                .append("\t\t// 值采用json序列化\n")
                .append("\t\ttemplate.setValueSerializer(jacksonSeial);\n")
                .append("\t\t//使用StringRedisSerializer来序列化和反序列化redis的key值\n")
                .append("\t\ttemplate.setKeySerializer(new StringRedisSerializer());\n")
                .append("\n")
                .append("\t\t// 设置hash key 和value序列化模式\n")
                .append("\t\ttemplate.setHashKeySerializer(new StringRedisSerializer());\n")
                .append("\t\ttemplate.setHashValueSerializer(jacksonSeial);\n")
                .append("\t\ttemplate.afterPropertiesSet();\n")
                .append("\n")
                .append("\t\treturn template;\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 对hash类型的数据操作\n")
                .append("\t *\n")
                .append("\t * @param redisTemplate\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\t@Bean\n")
                .append("\tpublic HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {\n")
                .append("\t\treturn redisTemplate.opsForHash();\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 对redis字符串类型数据操作\n")
                .append("\t *\n")
                .append("\t * @param redisTemplate\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\t@Bean\n")
                .append("\tpublic ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {\n")
                .append("\t\treturn redisTemplate.opsForValue();\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 对链表类型的数据操作\n")
                .append("\t *\n")
                .append("\t * @param redisTemplate\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\t@Bean\n")
                .append("\tpublic ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {\n")
                .append("\t\treturn redisTemplate.opsForList();\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 对无序集合类型的数据操作\n")
                .append("\t *\n")
                .append("\t * @param redisTemplate\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\t@Bean\n")
                .append("\tpublic SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {\n")
                .append("\t\treturn redisTemplate.opsForSet();\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 对有序集合类型的数据操作\n")
                .append("\t *\n")
                .append("\t * @param redisTemplate\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\t@Bean\n")
                .append("\tpublic ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {\n")
                .append("\t\treturn redisTemplate.opsForZSet();\n")
                .append("\t}\n")
                .append("\n")
                .append("}\n");
        return sb.toString();
    }


    /**
     * RedisStringDao数据库访问
     *
     * @param packages
     * @return
     */
    public static String redisStringDao(String packages) {

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("import org.springframework.beans.factory.annotation.Autowired;\n")
                .append("import org.springframework.data.redis.core.RedisTemplate;\n")
                .append("import org.springframework.data.redis.core.ValueOperations;\n")
                .append("import org.springframework.stereotype.Component;\n")
                .append("\n")
                .append("import java.util.concurrent.TimeUnit;\n")
                .append("\n")
                .append("/**\n")
                .append(" * @author wdke\n")
                .append(" * @date 2019/9/15\n")
                .append(" */\n")
                .append("@Component\n")
                .append("public class RedisStringDao {\n")
                .append("\n")
                .append("\n")
                .append("\t@Autowired\n")
                .append("\tprivate ValueOperations valueOperations;\n")
                .append("\n")
                .append("\n")
                .append("\t@Autowired\n")
                .append("\tprivate RedisTemplate<String, Object> redisTemplate;\n")
                .append("\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 新增redis\n")
                .append("\t *\n")
                .append("\t * @param key\n")
                .append("\t * @param value\n")
                .append("\t */\n")
                .append("\tpublic void set(String key, Object value) {\n")
                .append("\t\tvalueOperations.set(key, value);\n")
                .append("\t}\n")
                .append("\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 新增redis,设置过期时间\n")
                .append("\t * <p>\n")
                .append("\t * =nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set\n")
                .append("\t * <p>\n")
                .append("\t * exConstant.REDIS_KEY exConstant.REDIS_KEY的值只能取EX或者Constant.REDIS_KEY，代表数据过期时间的单位，EX代表秒，Constant.REDIS_KEY代表毫秒。\n")
                .append("\t *\n")
                .append("\t * @param key\n")
                .append("\t * @param value\n")
                .append("\t * @param time\n")
                .append("\t */\n")
                .append("\tpublic void set(String key, Object value, int time) {\n")
                .append("\t\tvalueOperations.set(key, value, time, TimeUnit.SECONDS);\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 设置健值一秒过期\n")
                .append("\t *\n")
                .append("\t * @param key\n")
                .append("\t */\n")
                .append("\tpublic void remove(String key) {\n")
                .append("\t\tredisTemplate.delete(key);\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 获取redis数据\n")
                .append("\t *\n")
                .append("\t * @param key\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\tpublic String get(String key) {\n")
                .append("\t\treturn (String) valueOperations.get(key);\n")
                .append("\t}\n")
                .append("}\n");

        return sb.toString();
    }


    /**
     * 生成token 生成工具
     *
     * @param packages
     * @return
     */
    public static String jwtUtils(String packages) {

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("import io.jsonwebtoken.Claims;\n")
                .append("import io.jsonwebtoken.JwtBuilder;\n")
                .append("import io.jsonwebtoken.Jwts;\n")
                .append("import io.jsonwebtoken.SignatureAlgorithm;\n")
                .append("\n")
                .append("import java.util.Date;\n")
                .append("import java.util.HashMap;\n")
                .append("import java.util.Map;\n")
                .append("import java.util.UUID;\n")
                .append("\n")
                .append("/**\n")
                .append(" * @author wdke\n")
                .append(" * @date 2019/5/10\n")
                .append(" */\n")
                .append("public class JwtUtils {\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 用户登录成功后生成Jwt\n")
                .append("\t * 使用Hs256算法  私匙使用用户密码\n")
                .append("\t *\n")
                .append("\t * @param map 登录成功的user对象\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\tpublic static String createJWT(Map<String, Object> map) {\n")
                .append("\t\tlong ttlMillis = 24 * 60 * 60 * 1000;\n")
                .append("\t\t//指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。\n")
                .append("\t\tSignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;\n")
                .append("\n")
                .append("\t\t//生成JWT的时间\n")
                .append("\t\tlong nowMillis = System.currentTimeMillis();\n")
                .append("\t\tDate now = new Date(nowMillis);\n")
                .append("\n")
                .append("\t\t//创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）\n")
                .append("\t\tMap<String, Object> claims = new HashMap<String, Object>();\n")
                .append("\t\tclaims.putAll(map);\n")
                .append("\n")
                .append("\t\t//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。\n")
                .append("\t\tString key = \"wdke20191212\";\n")
                .append("\t\tclaims.put(\"creator\", key);\n")
                .append("\n")
                .append("\t\t//生成签发人\n")
                .append("\t\tString subject = \"wdke20191212\";\n")
                .append("\n")
                .append("\n")
                .append("\t\t//下面就是在为payload添加各种标准声明和私有声明了\n")
                .append("\t\t//这里其实就是new一个JwtBuilder，设置jwt的body\n")
                .append("\t\tJwtBuilder builder = Jwts.builder()\n")
                .append("\t\t\t\t//如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的\n")
                .append("\t\t\t\t.setClaims(claims)\n")
                .append("\t\t\t\t//设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。\n")
                .append("\t\t\t\t.setId(UUID.randomUUID().toString())\n")
                .append("\t\t\t\t//iat: jwt的签发时间\n")
                .append("\t\t\t\t.setIssuedAt(now)\n")
                .append("\t\t\t\t//代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。\n")
                .append("\t\t\t\t.setSubject(subject)\n")
                .append("\t\t\t\t//设置签名使用的签名算法和签名使用的秘钥\n")
                .append("\t\t\t\t.signWith(signatureAlgorithm, key);\n")
                .append("\t\tif (ttlMillis >= 0) {\n")
                .append("\t\t\tlong expMillis = nowMillis + ttlMillis;\n")
                .append("\t\t\tDate exp = new Date(expMillis);\n")
                .append("\t\t\t//设置过期时间\n")
                .append("\t\t\tbuilder.setExpiration(exp);\n")
                .append("\t\t}\n")
                .append("\t\treturn builder.compact();\n")
                .append("\t}\n")
                .append("\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * Token的解密\n")
                .append("\t *\n")
                .append("\t * @param token 加密后的token\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\tpublic static Claims parseJWT(String token) {\n")
                .append("\t\t//签名秘钥，和生成的签名的秘钥一模一样\n")
                .append("\t\tString key = \"wdke20191212\";\n")
                .append("\n")
                .append("\t\t//得到DefaultJwtParser\n")
                .append("\t\tClaims claims = Jwts.parser()\n")
                .append("\t\t\t\t//设置签名的秘钥\n")
                .append("\t\t\t\t.setSigningKey(key)\n")
                .append("\t\t\t\t//设置需要解析的jwt\n")
                .append("\t\t\t\t.parseClaimsJws(token).getBody();\n")
                .append("\t\treturn claims;\n")
                .append("\t}\n")
                .append("\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 校验token\n")
                .append("\t * 在这里可以使用官方的校验，我这里校验的是token中携带的密码于数据库一致的话就校验通过\n")
                .append("\t *\n")
                .append("\t * @param token\n")
                .append("\t * @return\n")
                .append("\t */\n")
                .append("\tpublic static Boolean isVerify(String token) {\n")
                .append("\t\t//签名秘钥，和生成的签名的秘钥一模一样\n")
                .append("\t\tString key = \"phoenix201905101612\";\n")
                .append("\n")
                .append("\t\t//得到DefaultJwtParser\n")
                .append("\t\tClaims claims = Jwts.parser()\n")
                .append("\t\t\t\t//设置签名的秘钥\n")
                .append("\t\t\t\t.setSigningKey(key)\n")
                .append("\t\t\t\t//设置需要解析的jwt\n")
                .append("\t\t\t\t.parseClaimsJws(token).getBody();\n")
                .append("\n")
                .append("\t\tif (claims.get(\"creator\").equals(key)) {\n")
                .append("\t\t\treturn true;\n")
                .append("\t\t}\n")
                .append("\n")
                .append("\t\treturn false;\n")
                .append("\t}\n")
                .append("}\n");
        return sb.toString();
    }


    /**
     * accessLog 文件
     *
     * @param packages
     * @return
     */
    public static String accessLog(String packages) {

        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("import org.slf4j.Logger;\n")
                .append("import org.slf4j.LoggerFactory;\n")
                .append("\n")
                .append("/**\n")
                .append(" * Created by david on 18-4-16.\n")
                .append(" */\n")
                .append("public class AccessLog {\n")
                .append("\tprivate static Logger logger = LoggerFactory.getLogger(AccessLog.class);\n")
                .append("\n")
                .append("\tpublic static Logger getLogger() {\n")
                .append("\t\treturn logger;\n")
                .append("\t}\n")
                .append("}\n");
        return sb.toString();
    }


    /**
     * TalStopWatch 文件
     *
     * @param packages
     * @return
     */
    public static String talStopWatch(String packages) {

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("//\n")
                .append("// Source code recreated from a .class file by IntelliJ IDEA\n")
                .append("// (powered by Fernflower decompiler)\n")
                .append("//\n")
                .append("\n")
                .append("\n")
                .append("import com.alibaba.druid.util.StringUtils;\n")
                .append("import org.slf4j.Logger;\n")
                .append("import org.slf4j.LoggerFactory;\n")
                .append("\n")
                .append("import java.util.ArrayList;\n")
                .append("import java.util.Iterator;\n")
                .append("import java.util.List;\n")
                .append("\n")
                .append("public class TalStopWatch {\n")
                .append("\tprivate static final long NANOS_IN_A_MILLI = 1000000L;\n")
                .append("\tprivate static Logger logger = LoggerFactory.getLogger(TalStopWatch.class);\n")
                .append("\tprivate String globalTag;\n")
                .append("\tprivate long startTime;\n")
                .append("\tprivate long nanoStartTime;\n")
                .append("\tprivate long nanoLapStartTime;\n")
                .append("\tprivate long elapsedTime;\n")
                .append("\tprivate long timeThreshold = 50L;\n")
                .append("\tprivate List<String> stopWatchStrings;\n")
                .append("\n")
                .append("\tpublic TalStopWatch(String globalTag) {\n")
                .append("\t\tthis.start(globalTag);\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic long getStartTime() {\n")
                .append("\t\treturn this.startTime;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic long getElapsedTime() {\n")
                .append("\t\treturn this.elapsedTime == -1L ? (System.nanoTime() - this.nanoLapStartTime) / 1000000L : this.elapsedTime;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void start(String globalTag) {\n")
                .append("\t\tthis.globalTag = globalTag;\n")
                .append("\t\tthis.startTime = System.currentTimeMillis();\n")
                .append("\t\tthis.nanoStartTime = System.nanoTime();\n")
                .append("\t\tthis.stopWatchStrings = new ArrayList();\n")
                .append("\t\tthis.restLapTime();\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void stop() {\n")
                .append("\t\tthis.log();\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void stop(String tag) {\n")
                .append("\t\tthis.stop(tag, \"\");\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void stop(String tag, String message) {\n")
                .append("\t\tthis.lap(tag, message);\n")
                .append("\t\tthis.log();\n")
                .append("\t}\n")
                .append("\n")
                .append("\tprivate void log() {\n")
                .append("\t\tlong totalElapsedTime = (System.nanoTime() - this.nanoStartTime) / 1000000L;\n")
                .append("\t\tif (this.timeThreshold == 0L || totalElapsedTime > this.timeThreshold) {\n")
                .append("\t\t\tStringBuilder sb = new StringBuilder(\"StopWatch:start[\" + this.getStartTime() + \"] tag[\" + this.globalTag + \"] total[\" + totalElapsedTime + \"]\");\n")
                .append("\t\t\tString logStr = sb.toString();\n")
                .append("\t\t\tif (!this.stopWatchStrings.isEmpty()) {\n")
                .append("\t\t\t\tsb.append(\" :\");\n")
                .append("\t\t\t\tIterator var5 = this.stopWatchStrings.iterator();\n")
                .append("\n")
                .append("\t\t\t\twhile (var5.hasNext()) {\n")
                .append("\t\t\t\t\tString lapStr = (String) var5.next();\n")
                .append("\t\t\t\t\tsb.append(lapStr);\n")
                .append("\t\t\t\t\tsb.append(\",\");\n")
                .append("\t\t\t\t}\n")
                .append("\n")
                .append("\t\t\t\tlogStr = sb.substring(0, sb.length() - 1);\n")
                .append("\t\t\t}\n")
                .append("\n")
                .append("\t\t\tlogger.info(logStr);\n")
                .append("\t\t}\n")
                .append("\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void lap(String tag) {\n")
                .append("\t\tthis.lap(tag, \"\");\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void lap(String tag, String message) {\n")
                .append("\t\tthis.stopWatchStrings.add(this.createLapLog(tag, message));\n")
                .append("\t\tthis.restLapTime();\n")
                .append("\t}\n")
                .append("\n")
                .append("\tprivate String createLapLog(String tag, String message) {\n")
                .append("\t\treturn \" tag[\" + tag + \"] \" + this.getElapsedTime() + (StringUtils.isEmpty(message) ? \"\" : \" message[\" + message + \"]\");\n")
                .append("\t}\n")
                .append("\n")
                .append("\tprivate void restLapTime() {\n")
                .append("\t\tthis.nanoLapStartTime = System.nanoTime();\n")
                .append("\t\tthis.elapsedTime = -1L;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic long getTimeThreshold() {\n")
                .append("\t\treturn this.timeThreshold;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setTimeThreshold(long timeThreshold) {\n")
                .append("\t\tthis.timeThreshold = timeThreshold;\n")
                .append("\t}\n")
                .append("}\n");

        return sb.toString();

    }


    /**
     * 生成EventLog 文件
     *
     * @param packages
     * @return
     */
    public static String eventLog(String packages) {

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("import org.slf4j.Logger;\n")
                .append("import org.slf4j.LoggerFactory;\n")
                .append("\n")
                .append("/**\n")
                .append(" * @Author: junwei\n")
                .append(" * @Date: david 2018/10/29\n")
                .append(" */\n")
                .append("public class EventLog {\n")
                .append("\tprivate static Logger logger = LoggerFactory.getLogger(EventLog.class);\n")
                .append("\n")
                .append("\tpublic static Logger getLogger() {\n")
                .append("\t\treturn logger;\n")
                .append("\t}\n")
                .append("}\n");

        return sb.toString();

    }


    /**
     * ActionAop 文件
     *
     * @param packages
     * @return
     */
    public static String actionAop(String packages) {
        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        StringBuilder sb = new StringBuilder();
        sb.append("/**\n")
                .append(" * Created by zhangrui on 2018/3/21 0021.\n")
                .append(" */\n")
                .append("package ").append(packages).append(".common.aop;\n")
                .append("\n")
                .append("import ").append(projectMetadata.getPackages()).append(".common.logger.AccessLog;\n")
                .append("import ").append(projectMetadata.getPackages()).append(".common.logger.TalStopWatch;\n")
                .append("import org.aspectj.lang.JoinPoint;\n")
                .append("import org.aspectj.lang.ProceedingJoinPoint;\n")
                .append("import org.aspectj.lang.annotation.*;\n")
                .append("import org.springframework.stereotype.Component;\n")
                .append("\n")
                .append("/**\n")
                .append(" * AOP配置信息\n")
                .append(" *\n")
                .append(" * @author zhangrui\n")
                .append(" * @createDate 2018/3/21 0021\n")
                .append(" */\n")
                .append("@Component\n")
                .append("@Aspect\n")
                .append("public class ActionAop {\n")
                .append("\t\n")
                .append("\t@Pointcut(\"execution(* ").append(projectMetadata.getPackages()).append(".service..*(..))\")\n")
                .append("\tpublic void executeService() {\n")
                .append("\t}\n")
                .append("\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 前置通知，方法调用前被调用\n")
                .append("\t *\n")
                .append("\t * @param joinPoint\n")
                .append("\t */\n")
                .append("\t@Before(value = \"execution(* ").append(projectMetadata.getPackages()).append(".web..*(..))\")\n")
                .append("\tpublic void doBefore(JoinPoint joinPoint) {\n")
                .append("\t\tObject[] args = joinPoint.getArgs();\n")
                .append("\t\tAccessLog.getLogger().info(\"doBefore service:{} arg:{}\", joinPoint.getSignature().getName(), args);\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 后置返回通知\n")
                .append("\t * 这里需要注意的是:\n")
                .append("\t * 如果参数中的第一个参数为JoinPoint，则第二个参数为返回值的信息\n")
                .append("\t * 如果参数中的第一个参数不为JoinPoint，则第一个参数为returning中对应的参数\n")
                .append("\t * returning 限定了只有目标方法返回值与通知方法相应参数类型时才能执行后置返回通知，否则不执行，对于returning对应的通知方法参数为Object类型将匹配任何目标返回值\n")
                .append("\t *\n")
                .append("\t * @param joinPoint\n")
                .append("\t */\n")
                .append("\t@AfterReturning(value = \"execution(* ").append(projectMetadata.getPackages()).append(".web..*(..))\", returning = \"result\")\n")
                .append("\tpublic void doAfterReturning(JoinPoint joinPoint, Object result) {\n")
                .append("\t\tAccessLog.getLogger().info(\"doAfterReturning service:{} arg:{} result:{}\", joinPoint.getSignature().getName(), joinPoint.getArgs(), result);\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 后置异常通知\n")
                .append("\t * 定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；\n")
                .append("\t * throwing 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，\n")
                .append("\t * 对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。\n")
                .append("\t *\n")
                .append("\t * @param joinPoint\n")
                .append("\t * @param exception\n")
                .append("\t */\n")
                .append("\t@AfterThrowing(value = \"executeService()\", throwing = \"exception\")\n")
                .append("\tpublic void doAfterThrowing(JoinPoint joinPoint, Throwable exception) {\n")
                .append("\n")
                .append("\t\tAccessLog.getLogger().warn(\"doAfterThrowing service:{} exception:{}\", joinPoint.getSignature().getName(), exception.toString());\n")
                .append("\t}\n")
                .append("\n")
                .append("\t/**\n")
                .append("\t * 环绕通知：\n")
                .append("\t * 环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。\n")
                .append("\t * 环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型\n")
                .append("\t */\n")
                .append("\t@Around(\"executeService()\")\n")
                .append("\tpublic Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {\n")
                .append("\t\tTalStopWatch stopWatch = new TalStopWatch(proceedingJoinPoint.getSignature().getName());\n")
                .append("\t\tObject ret = proceedingJoinPoint.proceed();//调用执行目标方法\n")
                .append("\t\tstopWatch.stop();\n")
                .append("\t\treturn ret;\n")
                .append("\t}\n")
                .append("}\n")
                .append("\n");
        return sb.toString();
    }


    /**
     * webAppConfig 文件
     *
     * @return
     */
    public static String webAppConfig(String packages) {
        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("import ").append(projectMetadata.getPackages()).append(".web.interceptor.TokenInterceptor;\n")
                .append("import org.springframework.beans.factory.annotation.Autowired;\n")
                .append("import org.springframework.context.annotation.Configuration;\n")
                .append("import org.springframework.web.servlet.config.annotation.InterceptorRegistry;\n")
                .append("import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;\n")
                .append("\n")
                .append("@Configuration\n")
                .append("public class WebAppConfig extends WebMvcConfigurerAdapter {\n")
                .append("\n")
                .append("\n")
                .append("\t@Autowired\n")
                .append("\tprivate TokenInterceptor tokenInterceptor;\n")
                .append("\n")
                .append("\t@Override\n")
                .append("\tpublic void addInterceptors(InterceptorRegistry registry) {\n")
                .append("\t\t//注册自定义拦截器，添加拦截路径和排除拦截路径\n")
                .append("\t\tregistry.addInterceptor(tokenInterceptor).addPathPatterns(\"/**\");\n")
                .append("\t\tsuper.addInterceptors(registry);\n")
                .append("\t}\n")
                .append("}\n")
                .append("\n");
        return sb.toString();
    }


    /**
     * tokenInterceptor 文件
     *
     * @return
     */
    public static String tokenInterceptor(String packages) {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("import com.alibaba.druid.util.StringUtils;\n")
                .append("import org.slf4j.Logger;\n")
                .append("import org.slf4j.LoggerFactory;\n")
                .append("import org.springframework.beans.factory.annotation.Value;\n")
                .append("import org.springframework.stereotype.Component;\n")
                .append("import org.springframework.web.servlet.HandlerInterceptor;\n")
                .append("import org.springframework.web.servlet.ModelAndView;\n")
                .append("\n")
                .append("import javax.servlet.http.HttpServletRequest;\n")
                .append("import javax.servlet.http.HttpServletResponse;\n")
                .append("\n")
                .append("/**\n")
                .append(" * Created by wdk on 2018/6/20.\n")
                .append(" */\n")
                .append("@Component\n")
                .append("public class TokenInterceptor implements HandlerInterceptor {\n")
                .append("\n")
                .append("\n")
                .append("\tprivate static final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);\n")
                .append("\n")
                .append("\n")
                .append("\t@Override\n")
                .append("\tpublic boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {\n")
                .append("\n")
                .append("\t\t/* 解决跨域请求问题 */\n")
                .append("\t\tresponse.setHeader(\"Access-Control-Allow-Credentials\", \"true\");\n")
                .append("\t\tresponse.setHeader(\"Access-Control-Allow-Origin\", request.getHeader(\"Origin\"));\n")
                .append("\t\tresponse.setHeader(\"Access-Control-Allow-Methods\", \"HEAD,POST,GET,PUT,DELETE,OPTIONS\");\n")
                .append("\t\tresponse.setHeader(\"Access-Control-Max-Age\", \"3600\");\n")
                .append("\t\tresponse.setHeader(\"Access-Control-Allow-Headers\", \"x-requested-with,Authorization,Cache-Control,Pragma,Content-Type\");\n")
                .append("\n")
                .append("\t\tUserContext.current().setRequest(request);\n")
                .append("\t\tUserContext.current().setResponse(response);\n")
                .append("\t\tString traceId = request.getSession().getId();\n")
                .append("\n")
                .append("\t\tUserContext.current().setTraceId(traceId);\n")
                .append("\t\tlong start = System.currentTimeMillis();\n")
                .append("\t\tUserContext.current().setStartTime(start);\n")
                .append("\n")
                .append("\t\tString requestURI = request.getRequestURI();\n")
                .append("\t\tlogger.info(\"requestURI->{}\", requestURI);\n")
                .append("\n")
                .append("\t\tif (requestURI.startsWith(\"/permissions-server/api\")\n")
                .append("\t\t\t\t||requestURI.startsWith(\"/permissions-server/login\")\n")
                .append("\t\t\t\t||requestURI.startsWith(\"/permissions-server/toLogin\")) {\n")
                .append("\t\t\treturn true;\n")
                .append("\t\t} else {\n")
                .append("\t\t\treturn preHandleService(request, response);\n")
                .append("\t\t}\n")
                .append("\t}\n")
                .append("\n")
                .append("\t@Override\n")
                .append("\tpublic void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,\n")
                .append("\t\t\t\t\t\t   ModelAndView modelAndView) throws Exception {\n")
                .append("\t}\n")
                .append("\n")
                .append("\t@Override\n")
                .append("\tpublic void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)\n")
                .append("\t\t\tthrows Exception {\n")
                .append("\t\tlong start = UserContext.current().getStartTime();\n")
                .append("\t\tlong duration = System.currentTimeMillis() - start;\n")
                .append("\t\tif (duration > 3000) {\n")
                .append("\t\t\tlogger.info(\"超时报警短信【__trace_id：{}】【duration：{}】\", UserContext.current().getTraceId(), duration);\n")
                .append("\t\t}\n")
                .append("\t\tUserContext.release();\n")
                .append("\t}\n")
                .append("\n")
                .append("\tprivate boolean preHandleService(HttpServletRequest request, HttpServletResponse response) {\n")
                .append("\n")
                .append("\n")
                .append("\t\treturn true;\n")
                .append("\t}\n")
                .append("\n")
                .append("}\n");
        return sb.toString();

    }


    /**
     * 生成UserContext 文件
     *
     * @param packages
     * @return
     */
    public static String userContext(String packages) {

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("\n")
                .append("import javax.servlet.http.HttpServletRequest;\n")
                .append("import javax.servlet.http.HttpServletResponse;\n")
                .append("\n")
                .append("/**\n")
                .append(" * created by wdk on 2019/12/13\n")
                .append(" */\n")
                .append("public class UserContext {\n")
                .append("\n")
                .append("\tprivate static ThreadLocal<UserContext> threadLocal = ThreadLocal.withInitial(() -> new UserContext());\n")
                .append("\n")
                .append("\n")
                .append("\tprivate String lastPages;\n")
                .append("\n")
                .append("\tprivate String remortIP;\n")
                .append("\n")
                .append("\n")
                .append("\tprivate String traceId;\n")
                .append("\n")
                .append("\tprivate long startTime;\n")
                .append("\n")
                .append("\tprivate boolean creator;\n")
                .append("\n")
                .append("\tprivate HttpServletRequest request;\n")
                .append("\n")
                .append("\tprivate HttpServletResponse response;\n")
                .append("\n")
                .append("\tpublic String getLastPages() {\n")
                .append("\t\treturn lastPages;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setLastPages(String lastPages) {\n")
                .append("\t\tthis.lastPages = lastPages;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic static void release() {\n")
                .append("\t\tthreadLocal.remove();\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic static UserContext current() {\n")
                .append("\t\treturn threadLocal.get();\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic String getRemortIP() {\n")
                .append("\t\treturn remortIP;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setRemortIP(String remortIP) {\n")
                .append("\t\tthis.remortIP = remortIP;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic static ThreadLocal<UserContext> getThreadLocal() {\n")
                .append("\t\treturn threadLocal;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic static void setThreadLocal(ThreadLocal<UserContext> threadLocal) {\n")
                .append("\t\tUserContext.threadLocal = threadLocal;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic String getTraceId() {\n")
                .append("\t\treturn traceId;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setTraceId(String traceId) {\n")
                .append("\t\tthis.traceId = traceId;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic long getStartTime() {\n")
                .append("\t\treturn startTime;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setStartTime(long startTime) {\n")
                .append("\t\tthis.startTime = startTime;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic HttpServletRequest getRequest() {\n")
                .append("\t\treturn request;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setRequest(HttpServletRequest request) {\n")
                .append("\t\tthis.request = request;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic HttpServletResponse getResponse() {\n")
                .append("\t\treturn response;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setResponse(HttpServletResponse response) {\n")
                .append("\t\tthis.response = response;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic boolean isCreator() {\n")
                .append("\t\treturn creator;\n")
                .append("\t}\n")
                .append("\n")
                .append("\tpublic void setCreator(boolean creator) {\n")
                .append("\t\tthis.creator = creator;\n")
                .append("\t}\n")
                .append("}\n");

        return sb.toString();
    }


    /**
     * 登陆参数loginArgs
     *
     * @param packages
     * @return
     */
    public static String loginArgs(String packages) {
        StringBuilder sb = new StringBuilder();
        sb.append("package ").append(packages).append(";\n")
                .append("\n")
                .append("import lombok.Data;\n")
                .append("\n")
                .append("import javax.validation.constraints.NotBlank;\n")
                .append("import java.io.Serializable;\n")
                .append("\n")
                .append("/**\n")
                .append(" * created by wdk on 2019/12/24\n")
                .append(" */\n")
                .append("@Data\n")
                .append("public class LoginArgs implements Serializable {\n")
                .append("\n")
                .append("\n")
                .append("\t//用户名称\n")
                .append("\t@NotBlank(message = \"cannot be empty\")\n")
                .append("\tprivate String username;\n")
                .append("\n")
                .append("\n")
                .append("\t//用户密码\n")
                .append("\t@NotBlank(message = \"cannot be empty\")\n")
                .append("\tprivate String passord;\n")
                .append("}\n");
        return sb.toString();
    }
}
