package com.bfxy.springcloud.feign.hystrix;

import org.springframework.stereotype.Component;

import com.bfxy.springcloud.feign.IndexFeignClient;

@Component
public class IndexFeignFailback implements IndexFeignClient {

	@Override
	public String hello() throws Exception  {
		return "-----hello接口的降级方法!--------";
	}

	@Override
	public String hi() throws InterruptedException {
		return "-----hi接口的降级方法!--------";
	}

}
