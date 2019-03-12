package com.bfxy.springcloud;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import zipkin.server.EnableZipkinServer;
@EnableZipkinStreamServer
//@EnableKafkaStreams		//用这个注解会提示缺少kafka jar
@SpringBootApplication	//SpringBoot 核心配置
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
