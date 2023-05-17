package com.study.bookspace.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.member.service.MemberService;
import com.study.bookspace.member.vo.MemberVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	// 회원가입 화면으로
	@GetMapping("/joinForm")
	public String joinForm() {
		return "content/member/join";
	}
	
	// 아이디 중복 체크
	@ResponseBody
	@PostMapping("/idDuplicateCheckAjax")
	public boolean idDuplicateCheckAjax(String memId) {
		System.out.println(memId);
		return memberService.idDuplicateCheck(memId) == null;
	}
	
	// 회원가입
	@ResponseBody
	@PostMapping("/joinAjax")
	public boolean joinAjax(MemberVO memberVO) {
		System.out.println(memberVO);
		memberVO.setMemPw(encoder.encode(memberVO.getMemPw()));
		return memberService.join(memberVO) == 1;
	}
	
	// 로그인 화면으로
	@GetMapping("/loginForm")
	public String loginForm() {
		return "content/member/login";
	}

}
