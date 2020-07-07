package com.apress.springrecipes.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MeasurementInterceptor implements AsyncHandlerInterceptor {
	
	public static final String START_TIME = "startTIme";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if (request.getAttribute(START_TIME) == null) {
			request.setAttribute(START_TIME, System.currentTimeMillis());
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = (Long) request.getAttribute(START_TIME);
		request.removeAttribute(START_TIME);
		long endTime = System.currentTimeMillis();
		
		System.out.println("Request-Processing-Time: " + (endTime - startTime) + "ms.");
		System.out.println("Request-Processing-Thread: " + Thread.currentThread().getName());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
		long startTime = (Long) request.getAttribute(START_TIME);
		request.setAttribute(START_TIME, System.currentTimeMillis());
		long endTime = System.currentTimeMillis();
		
		System.out.println("Request-Processing-Time: " + (endTime - startTime) + "ms.");
		System.out.println("Request-Processing-Thread: " + Thread.currentThread().getName());
	}
}
