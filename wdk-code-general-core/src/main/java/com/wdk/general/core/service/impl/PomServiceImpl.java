package com.wdk.general.core.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.wdk.general.core.model.ProjectMetadata;
import com.wdk.general.core.service.PomService;
import com.wdk.general.core.utils.FileUtil;
import com.wdk.general.core.web.Interceptor.UserContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

/**
 * created by wdk on 2019/12/12
 */
@Service
public class PomServiceImpl implements PomService {


    /**
     * 生成pom 文件
     */
    @Override
    public void pom() {

        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();

        String fileName = UserContext.current().getProjectRoot() + "/pom.xml";
        Document document = DocumentHelper.createDocument();//创建xml文档
        Element root = document.addElement("project");
        root.addAttribute(" xmlns", "http://maven.apache.org/POM/4.0.0")
                .addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
                .addAttribute("xsi:schemaLocation", "http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd");//创建根元素

        //项目信息
        root.addComment("项目信息");
        root.addElement("modelVersion").addText("4.0.0");
        root.addElement("groupId").addText(projectMetadata.getGroup());
        root.addElement("artifactId").addText(projectMetadata.getArtifact());
        root.addElement("version").addText(projectMetadata.getVersion());
        root.addElement("name").addText(projectMetadata.getName());
        root.addElement("description").addText(projectMetadata.getDescription());
        root.addElement("packaging").addText("pom");

        //项目父pom
        root.addComment("项目父pom");
        Element parent = root.addElement("parent");
        parent.addElement("groupId").addText("org.springframework.boot");
        parent.addElement("artifactId").addText("spring-boot-starter-parent");
        parent.addElement("version").addText("2.2.2.RELEASE");
        parent.addElement("relativePath");


        //版本信息
        root.addComment("版本信息");
        Element properties = root.addElement("properties");
        properties.addElement("project.build.sourceEncoding").addText("UTF-8");
        properties.addElement("project.reporting.outputEncoding").addText("UTF-8");
        properties.addElement("java.version").addText(projectMetadata.getJavaVersion());
//        properties.addElement("spring-cloud.version").addText("Finchley.SR2");

        root.addComment("modules 信息");
        root.addElement("modules").addElement("module").addText(projectMetadata.getName() + "-server");

        //导入包
//        root.addComment("导入包");
//        Element dependencies = root.addElement("dependencies");
//        dependencies(dependencies);
//
//        //生成dependencyManagement
//        Element dependency = root.addElement("dependencyManagement")
//                .addElement("dependencies").addElement("dependency");
//        dependency.addElement("groupId").addText("org.springframework.cloud");
//        dependency.addElement("artifactId").addText("spring-cloud-dependencies");
//        dependency.addElement("version").addText("${spring-cloud.version}");
//        dependency.addElement("type").addText("pom");
//        dependency.addElement("scope").addText("import");

        root.addComment("构建配置");
        Element build = root.addElement("build");
        build.addComment("插件信息");
        Element plugins = build.addElement("plugins");

        //插件1
        plugins.addComment("资源配置");
        Element plugin1 = plugins.addElement("plugin");
        plugin1.addElement("groupId").addText("org.springframework.boot");
        plugin1.addElement("artifactId").addText("spring-boot-maven-plugin");
        Element configuration1 = plugin1.addElement("configuration");
        configuration1.addElement("fork").addText("true");
        configuration1.addElement("addResources").addText("true");

        //插件2
        plugins.addComment("跳过单元测试");
        Element plugin2 = plugins.addElement("plugin");
        plugin2.addElement("groupId").addText("org.apache.maven.plugins");
        plugin2.addElement("artifactId").addText("maven-surefire-plugin");
        plugin2.addElement("version").addText("2.18.1");
        plugin2.addElement("configuration").addElement("skipTests").addText("true");

        //插件3
        plugins.addComment("版本信息");
        Element plugin3 = plugins.addElement("plugin");
        plugin3.addElement("groupId").addText("org.apache.maven.plugins");
        plugin3.addElement("artifactId").addText("maven-compiler-plugin");
        plugin3.addElement("version").addText("3.1");
        Element configuration3 = plugin3.addElement("configuration");
        configuration3.addElement("source").addText("${java.version}");
        configuration3.addElement("target").addText("${java.version}");

        /**
         * 结束生成xml内容
         */
        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1  
        outputFormat.setEncoding("UTF-8");
        //是否生产xml头
//        outputFormat.setSuppressDeclaration(true);
        //第二行不空行
        outputFormat.setNewLineAfterDeclaration(true);
        //设置是否缩进
        outputFormat.setIndent(true);
        //以四个空格方式实现缩进
        outputFormat.setIndent("\t");
        //设置是否换行
        outputFormat.setNewlines(true);
//        outputFormat.setNewLineAfterDeclaration(true);
//        outputFormat.setNewLineAfterNTags(1);
        //设置文本换行
        outputFormat.setTrimText(false);
        // stringWriter字符串是用来保存XML文档的  
        StringWriter stringWriter = new StringWriter();
        try {
            // xmlWriter是用来把XML文档写入字符串的(工具)  
//            String fileName="/Users/ounoboruka/person/java-code/parents_project/code-general-core/xml/"+param.getModelName()+"Mapper.xml";
            FileUtil.creatFile(fileName);
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileName), outputFormat);
            xmlWriter.setEscapeText(true);
            // 把创建好的XML文档写入字符串  
            xmlWriter.write(document);
            // 打印字符串,即是XML文档

            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void serverPom() {


        ProjectMetadata projectMetadata = UserContext.current().getProjectMetadata();

        String fileName = UserContext.current().getProjectServerRoot() + "/pom.xml";
        Document document = DocumentHelper.createDocument();//创建xml文档
        Element root = document.addElement("project");
        root.addAttribute(" xmlns", "http://maven.apache.org/POM/4.0.0")
                .addAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
                .addAttribute("xsi:schemaLocation", "http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd");//创建根元素

        //项目信息
        root.addComment("项目信息");
        root.addElement("modelVersion").addText("4.0.0");
        root.addElement("groupId").addText(projectMetadata.getGroup());
        root.addElement("artifactId").addText(projectMetadata.getArtifact() + "-server");
        root.addElement("version").addText(projectMetadata.getVersion());
        root.addElement("name").addText(projectMetadata.getName() + "-server");
        root.addElement("description").addText(projectMetadata.getDescription());
        root.addElement("packaging").addText(projectMetadata.getPackaging());

        //项目父pom
        root.addComment("项目父pom");
        Element parent = root.addElement("parent");
        parent.addElement("groupId").addText("org.springframework.boot");
        parent.addElement("artifactId").addText("spring-boot-starter-parent");
        parent.addElement("version").addText("2.2.2.RELEASE");
        parent.addElement("relativePath");


        //版本信息
        root.addComment("版本信息");
        Element properties = root.addElement("properties");
        properties.addElement("project.build.sourceEncoding").addText("UTF-8");
        properties.addElement("project.reporting.outputEncoding").addText("UTF-8");
        properties.addElement("java.version").addText(projectMetadata.getJavaVersion());
        properties.addElement("spring-cloud.version").addText("Finchley.SR2");

        //导入包
        root.addComment("导入包");
        Element dependencies = root.addElement("dependencies");
        dependencies(dependencies);

        //生成dependencyManagement
        Element dependency = root.addElement("dependencyManagement")
                .addElement("dependencies").addElement("dependency");
        dependency.addElement("groupId").addText("org.springframework.cloud");
        dependency.addElement("artifactId").addText("spring-cloud-dependencies");
        dependency.addElement("version").addText("${spring-cloud.version}");
        dependency.addElement("type").addText("pom");
        dependency.addElement("scope").addText("import");

        root.addComment("构建配置");
        Element build = root.addElement("build");
        build.addComment("插件信息");
        Element plugins = build.addElement("plugins");

        //插件1
        plugins.addComment("资源配置");
        Element plugin1 = plugins.addElement("plugin");
        plugin1.addElement("groupId").addText("org.springframework.boot");
        plugin1.addElement("artifactId").addText("spring-boot-maven-plugin");
        Element configuration1 = plugin1.addElement("configuration");
        configuration1.addElement("fork").addText("true");
        configuration1.addElement("addResources").addText("true");

        //插件2
        plugins.addComment("跳过单元测试");
        Element plugin2 = plugins.addElement("plugin");
        plugin2.addElement("groupId").addText("org.apache.maven.plugins");
        plugin2.addElement("artifactId").addText("maven-surefire-plugin");
        plugin2.addElement("version").addText("2.18.1");
        plugin2.addElement("configuration").addElement("skipTests").addText("true");

        //插件3
        plugins.addComment("版本信息");
        Element plugin3 = plugins.addElement("plugin");
        plugin3.addElement("groupId").addText("org.apache.maven.plugins");
        plugin3.addElement("artifactId").addText("maven-compiler-plugin");
        plugin3.addElement("version").addText("3.1");
        Element configuration3 = plugin3.addElement("configuration");
        configuration3.addElement("source").addText("${java.version}");
        configuration3.addElement("target").addText("${java.version}");

        /**
         * 结束生成xml内容
         */
        // 设置XML文档格式
        OutputFormat outputFormat = OutputFormat.createPrettyPrint();
        // 设置XML编码方式,即是用指定的编码方式保存XML文档到字符串(String),这里也可以指定为GBK或是ISO8859-1  
        outputFormat.setEncoding("UTF-8");
        //是否生产xml头
//        outputFormat.setSuppressDeclaration(true);
        //第二行不空行
        outputFormat.setNewLineAfterDeclaration(true);
        //设置是否缩进
        outputFormat.setIndent(true);
        //以四个空格方式实现缩进
        outputFormat.setIndent("\t");
        //设置是否换行
        outputFormat.setNewlines(true);
//        outputFormat.setNewLineAfterDeclaration(true);
//        outputFormat.setNewLineAfterNTags(1);
        //设置文本换行
        outputFormat.setTrimText(false);
        // stringWriter字符串是用来保存XML文档的  
        StringWriter stringWriter = new StringWriter();
        try {
            // xmlWriter是用来把XML文档写入字符串的(工具)  
//            String fileName="/Users/ounoboruka/person/java-code/parents_project/code-general-core/xml/"+param.getModelName()+"Mapper.xml";
            FileUtil.creatFile(fileName);
            XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(fileName), outputFormat);
            xmlWriter.setEscapeText(true);
            // 把创建好的XML文档写入字符串  
            xmlWriter.write(document);
            // 打印字符串,即是XML文档

            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void dependencies(Element root) {

        root.addComment("web包");
        dependency(root, "org.springframework.boot", "spring-boot-starter-web");

        root.addComment("starter包");
        dependency(root, "org.springframework.boot", "spring-boot-starter");


        root.addComment("eureka");
        dependency(root, "org.springframework.cloud", "spring-cloud-starter-netflix-eureka-client");

        root.addComment("测试包");
        Element dependency = root.addElement("dependency");
        dependency.addElement("groupId").addText("org.springframework.boot");
        dependency.addElement("artifactId").addText("spring-boot-starter-test");
        dependency.addElement("scope").addText("test");
        exclusion(dependency.addElement("exclusions"), "org.junit.vintage", "junit-vintage-engine");

        //redis 工具包
        root.addComment("redis 工具包");
        dependency(root, "org.springframework.boot", "spring-boot-starter-redis", "1.4.7.RELEASE");

        //ThymeLeaf 依赖
        root.addComment("html模板");
        dependency(root, "org.springframework.boot", "spring-boot-starter-thymeleaf");

        //mysql工具包
        root.addComment("mysql 工具包");
        dependency(root, "mysql", "mysql-connector-java", "5.1.38");

        //mybatis工具包
        root.addComment("mybatis工具包");
        dependency(root, "org.mybatis.generator", "mybatis-generator-core", "1.3.5");

        //xml  dom4j
        root.addComment("dom4j包");
        dependency(root, "dom4j", "dom4j", "1.6.1");

        root.addComment("分页插件");
        dependency(root, "com.github.pagehelper", "pagehelper-spring-boot-starter", "1.2.3");

        root.addComment("csv插件");
        dependency(root, "com.opencsv", "opencsv", "3.9");

        root.addComment("json插件");
        dependency(root, "com.alibaba", "fastjson", "1.2.15");

        root.addComment("jdbc连接池");
        dependency(root, "com.alibaba", "druid", "1.1.0");

        root.addComment("common工具包");
        dependency(root, "commons-codec", "commons-codec", "1.10");

        root.addComment("common工具包");
        dependency(root, "commons-io", "commons-io", "2.6");

        root.addComment("common工具包");
        dependency(root, "commons-lang", "commons-lang", "2.6");

        root.addComment("工具包");
        dependency(root, "joda-time", "joda-time", "2.10.1");

        //lombok
        root.addComment("lombok工具包（idea需要安装这个插件）");
        Element dependency1 = root.addElement("dependency");
        dependency1.addElement("groupId").addText("org.projectlombok");
        dependency1.addElement("artifactId").addText("lombok");
        dependency1.addElement("version").addText("1.18.6");
        dependency1.addElement("scope").addText("provided");


        //jjwt 工具包
        root.addComment("jjwt 工具包");
        dependency(root, "io.jsonwebtoken", "jjwt", "0.9.1");


    }


    /**
     * 生成exclusion
     *
     * @param root
     * @param groupId
     * @param artifactId
     */
    public void exclusion(Element root, String groupId, String artifactId) {
        dependency(root, "exclusion", groupId, artifactId, null);

    }

    /**
     * 生成dependency
     *
     * @param root
     * @param groupId
     * @param artifactId
     */
    public void dependency(Element root, String groupId, String artifactId) {
        dependency(root, "dependency", groupId, artifactId, null);

    }


    /**
     * 生成dependency
     *
     * @param root
     * @param groupId
     * @param artifactId
     * @param version
     */
    public void dependency(Element root, String groupId, String artifactId, String version) {
        dependency(root, "dependency", groupId, artifactId, version);

    }

    /**
     * 生成dependency
     *
     * @param root
     * @param groupId
     * @param artifactId
     * @param version
     */
    public void dependency(Element root, String name, String groupId, String artifactId, String version) {


        Element dependency = root.addElement(name);
        dependency.addElement("groupId").addText(groupId);
        dependency.addElement("artifactId").addText(artifactId);
        if (!StringUtils.isEmpty(version)) {
            dependency.addElement("version").addText(version);
        }

    }

}
