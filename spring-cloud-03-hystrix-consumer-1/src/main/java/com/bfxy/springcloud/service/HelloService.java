package com.bfxy.springcloud.service;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bfxy.springcloud.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class HelloService {

	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 服务超时调用异常（服务的调用方）
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "callhelloFailback")
	public String callhello(){
		String ret= restTemplate.getForObject("http://provider-service/hello", String.class);
		System.err.println("hello service ret: " + ret);
		return ret;
	}
	
	public String callhelloFailback(){
		return "hello服务调用失败,进降级处理!!";
	}

	/**
	 * 服务内部异常（被调用方）
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "callhiFailback")
	public String callhi() {
		String ret = restTemplate.getForObject("http://provider-service/hi", String.class);
		System.err.println("hi service ret: " + ret);
		return null;
	}
	
	public String callhiFailback(){
		return "hi服务调用失败,进降级处理!!";
	}
	
	/**
	 * 在服务的内部指定一个超时时间配置:
	 * 一般来讲 我们需要设置一个全局的默认断路器超时时间,如果有需要会在具体的服务上去单独设置超时时间
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "timeoutFailback",
					commandKey = "helloKey",
					commandProperties ={
							@HystrixProperty(name="execution.timeout.enabled", value="true"),
							@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="3000")
					}
			)
	public String callhello4timeout(){
		String ret= restTemplate.getForObject("http://provider-service/hello", String.class);
		System.err.println("hello service ret: " + ret);
		return ret;
	}
	
	public String timeoutFailback(){
		return "在callhello4timeout服务接口上指定超时时间,调用失败,进降级处理!!";
	}
	
	/**
	 * 使用hystrix的批量合并请求功能
	 * 
	 */
	@HystrixCollapser(batchMethod="findAll", 
		collapserProperties = {
				@HystrixProperty(name="timerDelayInMilliseconds", value="200"),	//单个请求的延迟时间
				@HystrixProperty(name="maxRequestsInBatch", value="50"),		//允许最大的合并请求数量
				@HystrixProperty(name="requestCache.enabled", value="false")	//是否允许开启请求的本地缓存（对于一些静态数据可以进行启用）
		}
	)
	public Future<User> find(String id){
		return null;
	}
	
	@HystrixCommand
	public List<User> findAll(List<String> ids) {
		System.err.println("合并请求线程操作: --------> " + Thread.currentThread().getName());
		List<User> users = restTemplate.getForObject("http://provider-service/users?ids={1}", List.class, StringUtils.join(ids, ","));
		return users;
	}
	
	/**
	 * Hystrix实现限流机制： 使用THREAD方式: 线程池的方式进行隔离限流策略
	 */
	@HystrixCommand(
			commandKey = "threadKey1",
			/*使用commandProperties -->  execution.isolation.strategy 指定限流的策略  也可以省略*/
			commandProperties = {
					@HystrixProperty(name="execution.isolation.strategy", value="THREAD")
			},			
			threadPoolKey = "threadPoolKey1",
			threadPoolProperties = {
					//核心线程数,最大的并发线程数量
					@HystrixProperty(name="coreSize", value="10"),	
					//因为底层机制使用的是有界队列,有界队列必须要指定队列的容量上限.当一旦指定容量上限后就不能进行动态的调整
					@HystrixProperty(name="maxQueueSize", value="2000"),
					//使用THREAD方式： 关键的服务降级指标, 设置拒绝的阈值（可以动态的进行调整）
					@HystrixProperty(name="queueSizeRejectionThreshold", value="30")
			},
			fallbackMethod="threadFailback"
	)
	public String thread(){
		String ret = restTemplate.getForObject("http://provider-service/thread", String.class);
		System.err.println("输出调用结果: " + ret);
		return ret;
	}
	
	public String threadFailback(){
		System.err.println("--------thread限流,降级策略!--------");
		return "--------thread限流,降级策略!--------";
	}
	
	/**
	 * Hystrix实现限流机制： 使用SEMAPHORE方式: 使用java.util.concurrent.SEMAPHORE
	 */
	@HystrixCommand(
			commandKey ="semaphoreKey1",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE"),
					@HystrixProperty(name="execution.isolation.semaphore.maxConcurrentRequests", value="10")	//某一时间最大允许并发请求个数
			},
			fallbackMethod="semaphoreFailback"
	)
	public String semaphore(){
		String ret = restTemplate.getForObject("http://provider-service/semaphore", String.class);
		System.err.println("输出调用结果: " + ret);
		return ret;
	}
	
	public String semaphoreFailback(){
		System.err.println("--------semaphore限流,降级策略!--------");
		return "--------semaphore限流,降级策略!--------";
	}
	
	
	
}
