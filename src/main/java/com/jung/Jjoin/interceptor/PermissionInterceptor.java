package com.jung.Jjoin.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		
		String url = request.getRequestURI();
		
		if(userId == null) {
			
			if(url.startsWith("/post")) {
				
				response.sendRedirect("/user/login-view");
				return false;
			}
			
		}else {
			
			if(url.startsWith("user")) {
				response.sendRedirect("/post/timeline-Detail");
				return false;
			
			}
		
		}
		
		return true;
		
	}
	
//	@Override
//	public boolean preHandle(
//			HttpServletRequest request
//			, HttpServletResponse response
//			, Object handler) {
//		
//		// 
		
	}


