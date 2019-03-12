package com.bfxy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@zipkin.server.internal.EnableZipkinServer
@SpringBootApplication	//SpringBoot 核心配置
public class Application {
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
	
}
