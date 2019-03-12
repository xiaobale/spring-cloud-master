package com.bfxy.springcloud.api;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bfxy.springcloud.entity.User;
import com.bfxy.springcloud.service.HelloService;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

@RestController
public class ConsumerController {

	@Autowired
	private HelloService helloService;
	
	@RequestMapping(value="/hystrix-hello")
	public String hello() throws Exception {
		return helloService.callhello();
	}
	
	@RequestMapping(value="/hystrix-hi")
	public String hi() throws Exception {
		return helloService.callhi();
	}
	
	@RequestMapping(value="/hystrix-hello-timeout")
	public String hellotimeout() throws Exception {
		return helloService.callhello4timeout();
	}
	
	@RequestMapping(value="/hystrix-batch")
	public String batch() throws Exception {
		
		HystrixRequestContext ctx = HystrixRequestContext.initializeContext();
		
		Future<User> f1 = helloService.find("1");
		Future<User> f2 = helloService.find("2");
		Future<User> f3 = helloService.find("3");
		Future<User> f4 = helloService.find("4");
		
		
		System.err.println(f1.get());
		System.err.println(f2.get());
		System.err.println(f3.get());
		System.err.println(f4.get());
		
		
		Thread.sleep(1000);
		Future<User> f5 = helloService.find("5");
		System.err.println(f5.get());
		
		ctx.close();
		
		return "batch success!";
	}
	
	
	@RequestMapping(value="/hystrix-thread")
	public String thread() throws Exception {
		return this.helloService.thread();
	}
	
	
	@RequestMapping(value="/hystrix-semaphore")
	public String semaphore() throws Exception {
		return this.helloService.semaphore();
	}
	
	
	
	
	
	
	
	
	
}
