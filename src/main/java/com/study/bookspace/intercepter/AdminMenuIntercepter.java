package com.study.bookspace.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.study.bookspace.menu.service.MenuService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AdminMenuIntercepter implements HandlerInterceptor {
	
	@Resource(name = "menuService")
	private MenuService menuService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		modelAndView.addObject("sideMenuList", menuService.getMenuListByRoleAndUse("ADMIN"));
		
	}

}
