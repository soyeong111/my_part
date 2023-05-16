package com.study.bookspace.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.member.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "content/member/join";
	}
	
	@PostMapping("/join")
	public String join(MemberVO memberVO) {
		System.out.println(memberVO);
		
		return "";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "content/member/login";
	}

}
