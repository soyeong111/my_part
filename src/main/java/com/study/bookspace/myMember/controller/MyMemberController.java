package com.study.bookspace.myMember.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.menu.vo.SubMenuVO;
import com.study.bookspace.myMember.service.MyMemberService;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/mMember")
public class MyMemberController {
	
	@Resource(name = "myMemberService")
	private MyMemberService myMemberService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	// 내프로필 페이지
	@GetMapping("/myProfile")
	public String myProfile(SubMenuVO subMenuVO) {
		return "content/my/my_profile";
	}
	
	// 내정보변경 페이지
	@GetMapping("/myInfo")
	public String myInfo(SubMenuVO subMenuVO) {
		return "content/my/my_info";
	}
	
	// 비밀번호변경 페이지
	@GetMapping("/myPassword")
	public String myPassword(SubMenuVO subMenuVO) {
		return "content/my/my_password";
	}
	
	// 비밀번호 확인
	@ResponseBody
	@PostMapping("/checkPwAjax")
	public boolean checkPwAjax(String memPw, Authentication authentication) {
		String encMemPw = myMemberService.getPwById(((User)authentication.getPrincipal()).getUsername());
		return encoder.matches(memPw, encMemPw);
	}

	// 로그인 회원 상태 조회
	@ResponseBody
	@PostMapping("/checkMemStatusAjax")
	public String checkMemStatusAjax(String memId) {
		return myMemberService.checkMemStatus(memId);
	}
	
	// 마지막 로그인 날짜 수정
	@ResponseBody
	@PostMapping("/updateLoginDateAjax")
	public void updateLoginDateAjax(String memId) {
		myMemberService.updateMemLoginDate(memId);
	}
	
}
