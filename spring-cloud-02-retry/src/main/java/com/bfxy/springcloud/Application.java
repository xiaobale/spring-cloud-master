package com.bfxy.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient	//标识具体的一个服务,需要向注册中心注册
@SpringBootApplication	//SpringBoot 核心配置
@EnableRetry			//开启重试
public class Application {

	
	/**
	@Bean
	@LoadBalanced //用于实现内部的服务负载均衡机制: service-id  service-name
	public RestTemplate restTemplate(){
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory.setConnectTimeout(10000);
		httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(10000);
		httpComponentsClientHttpRequestFactory.setReadTimeout(20000);
		return new RestTemplate(httpComponentsClientHttpRequestFactory);
	}
	*/
	
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
