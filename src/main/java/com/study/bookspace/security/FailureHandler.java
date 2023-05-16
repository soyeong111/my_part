package com.study.bookspace.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
//		super.onAuthenticationFailure(request, response, exception);
//		
//		PrintWriter p = response.getWriter();
//		p.write("fail");
//		p.flush();
//		
//		response.getWriter().print(false);
//		response.getWriter().flush();
		
	}

}
