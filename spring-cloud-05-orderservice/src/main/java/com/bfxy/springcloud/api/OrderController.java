package com.bfxy.springcloud.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

	
	@RequestMapping(value="/order")
	public String order() throws Exception {
		return "---------order---------";
	}
}
