package com.bfxy.springcloud;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
@Component
public class MyZuulFilter extends ZuulFilter {

	/**
	 * 表示当前的过滤器是否被调用
	 */
	@Override
	public boolean shouldFilter() {
		return true;
	}

	/**
	 * filterOrder表示执行的优先级，值越小表示优先级越高
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 *  pre:		在请求被路由之前调用
		routing: 	在请求被路由之中调用
		post: 		在请求被路由之后调用
		error: 		处理请求发生错误时调用

	 */
	@Override
	public String filterType() {
		return "pre";
	}
	
	/**
	 * 真正执行Filter逻辑的方法
	 */
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		System.err.println("--------------uri: -------------- " + request.getRequestURI());
		
		/**
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String token = request.getHeader("x-token");
		
		
		if(!StringUtils.isBlank(token) && "1234".equals(token)){
			ctx.addZuulRequestHeader("token", token);
			//当前用户的角色信息. 用户等级. ...
			ctx.addZuulRequestHeader("level", "10");
		} else {
			System.err.println("------access token error!-----------");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("------access token error!-----------");
			return null;		//阻止向下游执行
		} 
		*/
		
		return ctx;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
