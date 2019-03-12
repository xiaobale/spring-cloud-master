package com.bfxy.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@EnableDiscoveryClient
@SpringBootApplication		//SpringBoot 核心基础配置
@EnableAutoConfiguration	//使用SpringBoot 自动配置
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}








