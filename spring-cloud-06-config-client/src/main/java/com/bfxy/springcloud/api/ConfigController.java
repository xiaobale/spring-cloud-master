package com.bfxy.springcloud.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope		//post方法进行手工强制刷新:  http://localhost:7001/refresh
@RestController
public class ConfigController {

	
	@Value("${from}")
	private String from;
	
	@RequestMapping(value="/from")
	public String from(){
		System.err.println("from: " + from);
		return this.from;
	}
	
	
}
