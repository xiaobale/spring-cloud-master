package com.bfxy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@EnableCircuitBreaker	//1.开启断路器的功能
@EnableDiscoveryClient	//标识具体的一个服务,需要向注册中心注册
@SpringBootApplication	//SpringBoot 核心配置
public class Application {

	
	@Bean
	@ConfigurationProperties(prefix = "custom.requestFactory")
	public HttpComponentsClientHttpRequestFactory customHttpRequestFactory(){
		return new HttpComponentsClientHttpRequestFactory();
	}
	
	@Bean
	@LoadBalanced //用于实现内部的服务负载均衡机制: service-id  service-name
	public RestTemplate restTemplate(){
		return new RestTemplate(customHttpRequestFactory());
	}
	
	
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
	
}
