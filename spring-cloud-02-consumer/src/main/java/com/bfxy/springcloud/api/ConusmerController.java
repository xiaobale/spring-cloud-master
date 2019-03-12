package com.bfxy.springcloud.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bfxy.springcloud.entity.User;

@RestController
public class ConusmerController {

	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping(value="/get")
	public String get(){
		ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://provider-service/getUser?id=001", User.class);
		User user = responseEntity.getBody();
		System.err.println("username: " + user.getName());
		return "get success!";
	}
	
	
	@RequestMapping(value="/post")
	public String post(){
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.set("id", "002");
		ResponseEntity<User> responseEntity = restTemplate.postForEntity("http://provider-service/postUser", params, User.class);
		User user = responseEntity.getBody();
		System.err.println("username: " + user.getName());
		return "post success!";
	}
}
