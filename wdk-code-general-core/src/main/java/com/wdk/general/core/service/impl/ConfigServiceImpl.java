package com.wdk.general.core.service.impl;

import com.wdk.general.core.model.DbMessage;
import com.wdk.general.core.model.ProjectMetadata;
import com.wdk.general.core.service.ConfigService;
import com.wdk.general.core.utils.FileUtil;
import com.wdk.general.core.common.model.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * created by wdk on 2019/12/13
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    private Logger logger = LoggerFactory.getLogger(ConfigService.class);


    /**
     * 生成eureka配置
     *
     * @param sb
     * @param addr
     */
    @Override
    public void eureka(StringBuilder sb, String addr) {
        sb.append("\n")
                .append("eureka:\n")
                .append("  client:\n")
                .append("    serviceUrl: #Eureka客户端与Eureka服务端进行交互的地址\n")
                .append("      defaultZone: ").append(addr).append("\n\n");
    }

    /**
     * 生成redis配置
     *
     * @param sb
     * @param addr
     */
    @Override
    public void redis(StringBuilder sb, String addr) {

        sb.append("\n")
                .append("  #redis 数据库的配置\n")
                .append("  redis:\n")
                .append("    database: 0\n")
                .append("    host: ").append(addr).append("\n")
                .append("    port: 6379\n")
                .append("    timeout: 5000\n\n\n");
    }

    /**
     * 数据源配置
     *
     * @param sb
     */
    @Override
    public void thymeleafAndDatasource(StringBuilder sb) {

        DbMessage dbMessage = UserContext.current().getDbMessage();

        sb.append("spring:  \n")
                .append("  thymeleaf:  \n")
                .append("    enabled: true  \n")
                .append("    prefix: classpath:templates/  \n")
                .append("    suffix: .html  \n")
                .append("    cache: false  \n")
                .append("    mode: HTML  \n")
                .append("    encoding: UTF-8  \n")
                .append("  mvc:  \n")
                .append("    static-path-pattern: /static/**  \n")
                .append("  resources:  \n")
                .append("    static-locations: classpath:/static/\n\n")
                .append("  #数据库连接配置开始\n")
                .append("  datasource:\n")
                .append("    url: jdbc:mysql://")
                .append(dbMessage.getHost())
                .append(":").append(dbMessage.getDbPort()).append("/").append(dbMessage.getDbName())
                .append("?useUnicode=true&characterEncoding=utf-8&useSSL=false&zeroDateTimeBehavior=convertToNull\n")
                .append("    username: ").append(dbMessage.getDbUsername()).append("\n")
                .append("    password: ").append(dbMessage.getDbPassword()).append("\n")
                .append("    driver-class-name: com.mysql.jdbc.Driver\n")
                .append("    type: com.alibaba.druid.pool.DruidDataSource\n")
                .append("    connectionProperties: druid.stat.mergeSql=true\n")
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
                .append("  configuration:\n")
                .append("    map-underscore-to-camel-case: true\n")
                .append("  type-aliases-package: ").append(packages).append(".model\n")
                .append("  mapper-locations: classpath:mapper/*.xml\n")
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
                .append("  helper-dialect: mysql\n")
                .append("  reasonable: true\n")
                .append("  support-methods-arguments: true\n")
                .append("  params: count=countSql\n")
                .append("#分页拦截器配置结束\n\n\n");

    }

    /**
     * 生成application.yml
     *
     * @param name
     */
    @Override
    public void application(String name) {

        String file = UserContext.current().getProjectServerRoot() + "/src/main/resources/application.yml";


        StringBuilder sb = new StringBuilder();

        sb.append("server:\n")
                .append("  port: ").append(UserContext.current().getProjectMetadata().getPoint()).append("\n\n")
                .append("spring:\n")
                .append("  application:\n")
                .append("    name: ").append(name).append("-server #指定服务名\n")
                .append("  profiles:\n")
                .append("    active: dev\n\n")
                .append("logging:\n")
                .append("  config: classpath:logback.xml\n\n");

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

        String file = UserContext.current().getProjectServerRoot() + "/src/main/resources/application-" + branch + ".yml";

        StringBuilder sb = new StringBuilder();

        //eureka 配置
        eureka(sb, "http://192.168.1.101:8802/eureka/");

        //数据源
        thymeleafAndDatasource(sb);


        //redis配置
        redis(sb, "192.168.1.100");

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
     */
    @Override
    public void logBack() {


        String file = UserContext.current().getProjectServerRoot() + "/src/main/resources/logback.xml";
        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();
        StringBuilder sb = new StringBuilder();

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<configuration debug=\"false\">\n")
                .append("    <property name=\"pattern\" value=\"%d{HH:mm:ss} [%thread] %-5level %logger{12} %5p [%X{X-B3-TraceId:-},%X{X-B3-SpanId:-},%X{X-Span-Export:-}] %X{traceId} %X{userId} %msg%n\"/>\n")
                .append("\n")
                .append("    <appender name=\"PerfLog\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n")
                .append("        <File>logs/perf.log</File>\n")
                .append("        <encoder>\n")
                .append("            <pattern>${pattern}</pattern>\n")
                .append("        </encoder>\n")
                .append("        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n")
                .append("            <FileNamePattern>logs/perf.%d{yyyy-MM-dd}.log</FileNamePattern>\n")
                .append("            <MaxHistory>7</MaxHistory>\n")
                .append("            <CleanHistoryOnStart>true</CleanHistoryOnStart>\n")
                .append("        </rollingPolicy>\n")
                .append("    </appender>\n")
                .append("\n")
                .append("    <appender name=\"ErrorLog\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n")
                .append("        <File>logs/error.log</File>\n")
                .append("        <encoder>\n")
                .append("            <pattern>${pattern}</pattern>\n")
                .append("        </encoder>\n")
                .append("\n")
                .append("        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n")
                .append("            <FileNamePattern>logs/error.%d{yyyy-MM-dd}.log</FileNamePattern>\n")
                .append("            <MaxHistory>15</MaxHistory>\n")
                .append("            <CleanHistoryOnStart>true</CleanHistoryOnStart>\n")
                .append("        </rollingPolicy>\n")
                .append("\n")
                .append("        <filter class=\"ch.qos.logback.classic.filter.ThresholdFilter\">\n")
                .append("            <level>ERROR</level>\n")
                .append("        </filter>\n")
                .append("    </appender>\n")
                .append("\n")
                .append("    <appender name=\"AccessLog\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n")
                .append("        <File>logs/access.log</File>\n")
                .append("        <encoder>\n")
                .append("            <pattern>${pattern}</pattern>\n")
                .append("        </encoder>\n")
                .append("        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n")
                .append("            <FileNamePattern>logs/access.%d{yyyy-MM-dd}.log</FileNamePattern>\n")
                .append("            <MaxHistory>7</MaxHistory>\n")
                .append("            <CleanHistoryOnStart>true</CleanHistoryOnStart>\n")
                .append("        </rollingPolicy>\n")
                .append("    </appender>\n")
                .append("\n")
                .append("    <appender name=\"ServerLog\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n")
                .append("        <File>logs/server.log</File>\n")
                .append("        <encoder>\n")
                .append("            <pattern>${pattern}</pattern>\n")
                .append("        </encoder>\n")
                .append("        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n")
                .append("            <FileNamePattern>logs/server.%d{yyyy-MM-dd}.log</FileNamePattern>\n")
                .append("            <MaxHistory>7</MaxHistory>\n")
                .append("            <CleanHistoryOnStart>true</CleanHistoryOnStart>\n")
                .append("        </rollingPolicy>\n")
                .append("    </appender>\n")
                .append("\n")
                .append("    <appender name=\"ExceptionLog\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n")
                .append("        <File>logs/exceptionLog.log</File>\n")
                .append("        <encoder>\n")
                .append("            <pattern>${pattern}</pattern>\n")
                .append("        </encoder>\n")
                .append("        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n")
                .append("            <FileNamePattern>logs/exceptionLog.%d{yyyy-MM-dd}.log</FileNamePattern>\n")
                .append("            <MaxHistory>7</MaxHistory>\n")
                .append("            <CleanHistoryOnStart>true</CleanHistoryOnStart>\n")
                .append("        </rollingPolicy>\n")
                .append("    </appender>\n")
                .append("\n")
                .append("    <appender name=\"EventLog\" class=\"ch.qos.logback.core.rolling.RollingFileAppender\">\n")
                .append("        <File>logs/event.log</File>\n")
                .append("        <encoder>\n")
                .append("            <pattern>${pattern}</pattern>\n")
                .append("        </encoder>\n")
                .append("        <rollingPolicy class=\"ch.qos.logback.core.rolling.TimeBasedRollingPolicy\">\n")
                .append("            <FileNamePattern>logs/event.%d{yyyy-MM-dd}.log</FileNamePattern>\n")
                .append("            <MaxHistory>15</MaxHistory>\n")
                .append("            <CleanHistoryOnStart>true</CleanHistoryOnStart>\n")
                .append("        </rollingPolicy>\n")
                .append("    </appender>\n")
                .append("\n")
                .append("    <!-- 彩色日志 -->\n")
                .append("    <!-- 彩色日志依赖的渲染类 -->\n")
                .append("    <conversionRule conversionWord=\"clr\" converterClass=\"org.springframework.boot.logging.logback.ColorConverter\" />\n")
                .append("    <conversionRule conversionWord=\"wex\" converterClass=\"org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter\" />\n")
                .append("    <conversionRule conversionWord=\"wEx\" converterClass=\"org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter\" />\n")
                .append("    <!-- 彩色日志格式 -->\n")
                .append("    <property name=\"CONSOLE_LOG_PATTERN\" value=\"${CONSOLE_LOG_PATTERN:-%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(${LOG_LEVEL_PATTERN:-%5p}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%logger){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}}\" />\n")
                .append("\n")
                .append("    <appender name=\"STDOUT\" class=\"ch.qos.logback.core.ConsoleAppender\">\n")
                .append("        <!--encoder 默认配置为PatternLayoutEncoder-->\n")
                .append("        <encoder>\n")
                .append("            <pattern>${CONSOLE_LOG_PATTERN}</pattern>\n")
                .append("            <!--<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level -&#45;&#45; [%thread] %logger Line:%-3L - %msg%n</pattern>-->\n")
                .append("            <charset>utf-8</charset>\n")
                .append("        </encoder>\n")
                .append("        <!--此日志appender是为开发使用，只配置最底级别，控制台输出的日志级别是大于或等于此级别的日志信息-->\n")
                .append("        <filter class=\"ch.qos.logback.classic.filter.ThresholdFilter\">\n")
                .append("            <level>debug</level>\n")
                .append("        </filter>\n")
                .append("    </appender>\n")
                .append("\n")
                .append("    <appender name=\"ASYNC\" class=\"ch.qos.logback.classic.AsyncAppender\">\n")
                .append("        <queueSize>2048</queueSize>\n")
                .append("        <neverBlock>true</neverBlock>\n")
                .append("        <!--<appender-ref ref=\"STDOUT\"/>-->\n")
                .append("        <appender-ref ref=\"ServerLog\"/>\n")
                .append("        <appender-ref ref=\"ErrorLog\"/>\n")
                .append("        <appender-ref ref=\"STDOUT\"/>\n")
                .append("    </appender>\n")
                .append("    \n")
                .append("\n")
                .append("<!--监控所有服务日志-->\n")
                .append("    <logger name=\"").append(projectMetadata.getPackages()).append("\" level=\"INFO\" additivity=\"false\">\n")
                .append("        <appender-ref ref=\"ServerLog\"/>\n")
                .append("    </logger>\n")
                .append("    \n")
                .append("    <!--监控数据出参入参-->\n")
                .append("    <logger name=\"").append(projectMetadata.getPackages()).append(".config.logger.AccessLog\" level=\"INFO\" additivity=\"false\">\n")
                .append("        <appender-ref ref=\"AccessLog\"/>\n")
                .append("    </logger>\n")
                .append("    \n")
                .append("    <!--处理耗时-->\n")
                .append("    <logger name=\"").append(projectMetadata.getPackages()).append(".config.logger.TalStopWatch\" level=\"INFO\" additivity=\"false\">\n")
                .append("        <appender-ref ref=\"PerfLog\"/>\n")
                .append("    </logger>\n\n")
                .append("\n")
                .append("    <!-- 日志输出级别 -->\n")
                .append("    <root level=\"INFO\">\n")
                .append("        <appender-ref ref=\"ASYNC\"/>\n")
                .append("        <appender-ref ref=\"STDOUT\"/>\n")
                .append("    </root>\n")
                .append("</configuration>\n");

        try {
            FileUtil.write(file, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
