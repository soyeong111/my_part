package com.study.bookspace.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.study.bookspace.alram.service.AlramService;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SucessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Resource(name = "alramService")
	private AlramService alramService;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
//		super.onAuthenticationSuccess(request, response, authentication);
		User user = (User)authentication.getPrincipal();
		
		//알람 개수 조회
		int alramCnt = alramService.getAlramCnt(user.getUsername());
		
		//새로운 알람이 있다면 세션에 저장
		if(alramCnt > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("alramCnt", alramCnt);
		}
		
		PrintWriter p = response.getWriter();
		p.write("success");
		p.flush();
		
//		response.getWriter().print(true);
//		response.getWriter().flush();
		
	}

}
