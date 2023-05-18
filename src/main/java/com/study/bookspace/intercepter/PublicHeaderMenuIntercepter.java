package com.study.bookspace.intercepter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.study.bookspace.admin.service.AdminService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PublicHeaderMenuIntercepter implements HandlerInterceptor {
	
	@Resource(name = "adminService")
	private AdminService adminService;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		List<String> roleList = new ArrayList<>();
		roleList.add("PUBLIC");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority authority : authorities) {
			String role = authority.getAuthority();
			if (!role.equals("ROLE_ANONYMOUS")) {
				roleList.add("USER");
			}
			if (role.equals("ROLE_ADMIN")) {
				roleList.add("ADMIN");
			}
		}
		modelAndView.addObject("headerMenuList", adminService.getMenuListByRole(roleList));
	}

}
