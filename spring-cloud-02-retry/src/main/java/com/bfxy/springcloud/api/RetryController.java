package com.bfxy.springcloud.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bfxy.springcloud.entity.User;

@RestController
public class RetryController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/retry")
	public String retry(){
		User user = restTemplate.getForObject("http://provider-service/getUser?id={1}", User.class, "001");
		System.err.println("username: " + user.getName());
		return "retry success!";
	}
	
	
	
}
