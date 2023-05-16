package com.study.bookspace.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SucessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
//		super.onAuthenticationSuccess(request, response, authentication);
//		
//		PrintWriter p = response.getWriter();
//		p.write("success");
//		p.flush();
//		
//		response.getWriter().print(true);
//		response.getWriter().flush();
		
	}

}
