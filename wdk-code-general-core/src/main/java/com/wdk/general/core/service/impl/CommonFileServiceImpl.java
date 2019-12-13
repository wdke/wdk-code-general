package com.wdk.general.core.service.impl;

import com.wdk.general.core.service.CommonFileService;
import com.wdk.general.core.utills.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * created by wdk on 2019/12/13
 */
@Service
public class CommonFileServiceImpl implements CommonFileService {

    @Value("${filePath}")
    private String filePath;


    /**
     * 启动函数配置
     *
     * @param name
     * @param packages
     */
    @Override
    public void mainApplication(String name, String packages) {

        String file = filePath + "/src/main/java/" + packages.replaceAll("\\.", "/") + "/" + name + "Application.java";

        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(packages).append(";\n\n")
                .append("import org.springframework.boot.SpringApplication;\n")
                .append("import org.springframework.boot.autoconfigure.SpringBootApplication;\n")
                .append("\n")
                .append("@SpringBootApplication\n")
                .append("public class ").append(name).append("Application {\n")
                .append("\n")
                .append("\tpublic static void main(String[] args) {\n")
                .append("\t\tSpringApplication.run(").append(name).append("Application.class, args);\n")
                .append("\t}\n")
                .append("}\n");

        try {
            FileUtil.write(file, sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
