package com.bfxy.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication		//SpringBoot 核心基础配置
@EnableAutoConfiguration	//使用SpringBoot 自动配置
public class Application {

	//数据查看地址: http://localhost:7003/hystrix.stream
	
	//监控台访问地址: http://localhost:2001/hystrix 
	//在输入框中输入: 数据查看地址后可以看到流量监控
	
	
	//查询集群状态则需要输入turbine的地址: http://localhost:3000/turbine.stream
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}








