package com.wdk.general.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.wdk.permissions.api","com.wdk.general.core"})
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"com.wdk.permissions.api.http"})
@MapperScan("com.wdk.general.core.storage.dao")
public class WdkCodeGeneralCoreApplication {

    public static void main(String[] args) {

        SpringApplication.run(WdkCodeGeneralCoreApplication.class, args);
    }

}
