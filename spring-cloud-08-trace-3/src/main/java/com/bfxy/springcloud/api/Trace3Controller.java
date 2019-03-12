package com.bfxy.springcloud.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Trace3Controller {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/trace3")
	public String trace3(){
		System.err.println("--------------trace3-----------");
		return "success!";
	}
	
	
}
