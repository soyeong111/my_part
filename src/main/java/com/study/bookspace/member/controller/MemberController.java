package com.study.bookspace.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "content/member/join";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "content/member/login";
	}

}
