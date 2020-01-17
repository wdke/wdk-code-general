package com.wdk.code.general.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.wdk.permissions.api","com.wdk.code.general.server","com.wdk.general.core"})
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"com.wdk.permissions.api.http","com.wdk.code.general.server.https"})
@MapperScan("com.wdk.code.general.**.storage.dao")
public class WdkCodeGeneralServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(WdkCodeGeneralServerApplication.class, args);
	}
}
