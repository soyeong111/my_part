package com.study.bookspace.intercepter;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.study.bookspace.admin.service.AdminService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PublicMenuIntercepter implements HandlerInterceptor {
	
	@Resource(name = "adminService")
	private AdminService adminService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		modelAndView.addObject("publicMenuList", adminService.getMenuListByRoleAndUse("PUBLIC"));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			String role = authority.getAuthority();
			if (!role.equals("ROLE_ANONYMOUS")) {
				modelAndView.addObject("userMainMenuList", adminService.getMainMenuListByRoleAndUse("USER"));
			}
			if (role.equals("ROLE_ADMIN")) {
				modelAndView.addObject("adminMainMenuList", adminService.getMainMenuListByRoleAndUse("ADMIN"));
			}
		}
		
	}

}
