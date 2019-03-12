package com.bfxy.springcloud.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Trace1Controller {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/trace1")
	public String trace1(){
		System.err.println("--------------trace1-----------");
		return restTemplate.getForObject("http://trace-2/trace2", String.class);
	}
	
	
}
