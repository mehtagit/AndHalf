package com.learning.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * To work with interceptor, you need to create @Component class that supports
 * it and it should implement the HandlerInterceptor interface.
 * 
 * The following are the three methods you should know about while working on
 * Interceptors −
 * 
 * preHandle() method − This is used to perform operations before sending the
 * request to the controller. This method should return true to return the
 * response to the client.
 * 
 * postHandle() method − This is used to perform operations before sending the
 * response to the client.
 * 
 * afterCompletion() method − This is used to perform operations after
 * completing the request and response.
 * 
 */
@Component
public class ProductServiceInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("Request Completed");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle postHandle");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle preHandle");
		return true;
		// return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
