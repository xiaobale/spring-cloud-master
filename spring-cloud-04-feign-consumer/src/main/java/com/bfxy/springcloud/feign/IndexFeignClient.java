package com.bfxy.springcloud.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bfxy.springcloud.feign.hystrix.IndexFeignFailback;

@FeignClient(name="provider-service", fallback=IndexFeignFailback.class)
public interface IndexFeignClient {
	
	@RequestMapping(value="/index", method = {RequestMethod.GET})
	public String hello() throws Exception ;
	
	@RequestMapping(value="/hi", method = {RequestMethod.GET})
	public String hi() throws InterruptedException ;
}
