package com.bfxy.springcloud.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Trace2Controller {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/trace2")
	public String trace2(){
		System.err.println("--------------trace2-----------");
		return restTemplate.getForObject("http://trace-3/trace3", String.class);
	}
	
	
}
