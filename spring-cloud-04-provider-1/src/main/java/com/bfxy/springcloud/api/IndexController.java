package com.bfxy.springcloud.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

	@RequestMapping(value="/index", method = {RequestMethod.GET})
	public String hello() throws Exception {
		int a = 1/0;
		return "Hello World!";
	}
	
	@RequestMapping(value="/hi", method = {RequestMethod.GET})
	public String hi() throws InterruptedException {
		Thread.sleep(4000);
		return "Hi Feign!";
	}
	
}
