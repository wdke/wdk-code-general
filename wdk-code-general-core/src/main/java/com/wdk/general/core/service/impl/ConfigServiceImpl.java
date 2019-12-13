package com.wdk.general.core.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.service.ConfigService;
import com.wdk.general.core.utills.FileUtil;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * created by wdk on 2019/12/13
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    private Logger logger = LoggerFactory.getLogger(ConfigService.class);

    @Value("${filePath}")
    private String filePath;

    /**
     * 数据源配置
     *
     * @param sb
     */
    @Override
    public void datasource(StringBuilder sb) {

        DbMessage dbMessage = UserContext.current().getDbMessage();

        sb.append("spring:\n")
                .append("\t#数据库连接配置开始\n")
                .append("\tdatasource:\n")
                .append("\t\turl: jdbc:mysql:#")
                .append(dbMessage.getHost())
                .append(":").append(dbMessage.getDbport()).append("/").append(dbMessage.getDbname())
                .append("?useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull\n")
                .append("\t\tusername: ").append(dbMessage.getDbuser()).append("\n")
                .append("\t\tpassword: ").append(dbMessage.getDbpassword()).append("\n")
                .append("\t\tdriver-class-name: com.mysql.jdbc.Driver\n")
                .append("\t\ttype: com.alibaba.druid.pool.DruidDataSource\n")
                .append("\t\tconnectionProperties: druid.stat.mergeSql=true\n")
                .append("#数据库连接配置结束\n\n\n");

    }

    /**
     * mybatis配置
     *
     * @param sb
     * @param packages
     */
    @Override
    public void mybatis(StringBuilder sb, String packages) {


        sb.append("#mybatis配置开始\n")
                .append("mybatis:\n")
                .append("\tconfiguration:\n")
                .append("\t\tmap-underscore-to-camel-case: true\n")
                .append("\ttype-aliases-package: ").append(packages).append(".model\n")
                .append("\tmapper-locations: classpath:mapper/*.xml\n")
                .append("#mybatis配置结束\n\n\n");

    }

    /**
     * 分页插件配置
     *
     * @param sb
     */
    @Override
    public void pagehelper(StringBuilder sb) {

        sb.append("#分页拦截器配置\n")
                .append("pagehelper:\n")
                .append("\thelper-dialect: mysql\n")
                .append("\treasonable: true\n")
                .append("\tsupport-methods-arguments: true\n")
                .append("\tparams: count=countSql\n")
                .append("#分页拦截器配置结束\n\n\n");

    }

    /**
     * 生成application.yml
     *
     * @param name
     */
    @Override
    public void application(String name) {

        String file = filePath + "/src/main/resources/application.yml";


        StringBuilder sb = new StringBuilder();

        sb.append("server:\n")
                .append("\tport: 8080\n\n")
                .append("spring:\n")
                .append("\tapplication:\n")
                .append("\t\tname: ").append(name).append(" #指定服务名\n")
                .append("\tprofiles:\n")
                .append("\t\tactive: dev\n\n")
                .append("logging:\n")
                .append("\tconfig: classpath:logback-${spring.profiles.active}.xml\n\n");

        try {
            FileUtil.write(file, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 生成application-xxx.yml
     *
     * @param branch
     * @param packages
     */
    @Override
    public void applicationBranch(String branch, String packages) {

        String file = filePath + "/src/main/resources/application-" + branch + ".yml";

        StringBuilder sb = new StringBuilder();

        //数据源
        datasource(sb);

        //mybatis配置
        mybatis(sb, packages);

        //分页配置
        pagehelper(sb);


        try {
            FileUtil.write(file, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * log文件
     *
     * @param branch
     * @param path
     */
    @Override
    public void logBack(String branch, String path) {


        String file = filePath + "/src/main/resources/logback-" + branch + ".xml";

        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<configuration debug=\"false\">\n")
                .append("    <!--定义日志文件的存储地址 勿在 LogBack 的配置中使用相对路径-->\n");

        if (!StringUtils.isEmpty(path)) {
            sb.append("    <property name=\"LOG_HOME\" value=\"/tmp/log\" />\n");
        }

        sb.append("    <!-- 控制台输出 -->\n")
                .append("    <appender name=\"STDOUT\" class=\"ch.qos.logback.core.ConsoleAppender\">\n")
                .append("        <encoder class=\"ch.qos.logback.classic.encoder.PatternLayoutEncoder\">\n")
                .append("            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->\n")
                .append("            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>\n")
                .append("        </encoder>\n")
                .append("    </appender>\n")
                .append("    <!-- 按照每天生成日志文件 -->\n")
                .append("    <appender name=\"FILE\"  class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n")
                .append("        <rollingPolicy class=\"ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy\">\n")
                .append("            <!--日志文件输出的文件名-->\n")
                .append("            <FileNamePattern>${LOG_HOME}/ws.log.%d{yyyy-MM-dd}.%i.log</FileNamePattern>\n")
                .append("            <MaxFileSize>20MB</MaxFileSize>\n")
                .append("            <MaxHistory>30</MaxHistory>\n")
                .append("        </rollingPolicy>\n")
                .append("        <encoder class=\"ch.qos.logback.classic.encoder.PatternLayoutEncoder\">\n")
                .append("            <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度%msg：日志消息，%n是换行符-->\n")
                .append("            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>\n")
                .append("        </encoder>\n")
                .append("    </appender>\n")
                .append("\n")
                .append("    <!--myibatis log configure-->\n")
                .append("    <logger name=\"com.apache.ibatis\" level=\"TRACE\"/>\n")
                .append("    <logger name=\"java.sql.Connection\" level=\"DEBUG\"/>\n")
                .append("    <logger name=\"java.sql.Statement\" level=\"DEBUG\"/>\n")
                .append("    <logger name=\"java.sql.PreparedStatement\" level=\"DEBUG\"/>\n")
                .append("    <!--<logger name=\"com.magicmirror.basicdata.dao\" level=\"DEBUG\" />-->\n")
                .append("\n")
                .append("    <!-- 日志输出级别 -->\n")
                .append("    <root level=\"INFO\">\n")
                .append("        <appender-ref ref=\"STDOUT\"/>\n")
                .append("        <appender-ref ref=\"FILE\" />\n")
                .append("    </root>\n")
                .append("</configuration>\n");

        try {
            FileUtil.write(file, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
