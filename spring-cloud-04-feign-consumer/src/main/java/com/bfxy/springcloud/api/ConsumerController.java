package com.bfxy.springcloud.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bfxy.springcloud.feign.IndexFeignClient;

@RestController
public class ConsumerController {

	@Autowired
	private IndexFeignClient indexFeignClient;
	
	@RequestMapping(value="/feign-hello")
	public String hello() throws Exception {
		return indexFeignClient.hello();
	}
	
	@RequestMapping(value="/feign-hi")
	public String hi() throws InterruptedException {
		return indexFeignClient.hi();
	}
}
