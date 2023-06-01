package com.study.bookspace.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.member.service.MemberService;
import com.study.bookspace.member.vo.MemberVO;
import com.study.bookspace.sms.SmsService;
import com.study.bookspace.sms.SmsVO;
import com.study.bookspace.util.MailService;
import com.study.bookspace.util.MailVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Resource(name = "mailService")
	private MailService mailService;
	
	@Resource(name = "smsService")
	private SmsService smsService;
	
	// 회원가입 화면으로
	@GetMapping("/joinForm")
	public String joinForm() {
		return "content/member/join";
	}
	
	// 아이디 중복 체크
	@ResponseBody
	@PostMapping("/idDuplicateCheckAjax")
	public boolean idDuplicateCheckAjax(String memId) {
		return memberService.idDuplicateCheck(memId) == null;
	}
	
	// 이메일 인증
	@ResponseBody
	@PostMapping("/emailAuthAjax")
	public String emailAuthAjax(String email, MailVO mailVO) {
		List<String> recipientList = new ArrayList<>();
		recipientList.add(email);
		mailVO.setRecipientList(recipientList);
		mailVO.setTitle("이메일 인증");
		String pw = mailService.createRandomPassword(6);
		mailVO.setContent("인증 번호 : " + pw);
		mailService.sendSimpleEmail(mailVO);
		return pw;
	}
	
	// 휴대폰 인증
	@ResponseBody
	@PostMapping("/tellAuthAjax")
	public String tellAuthAjax(SmsVO smsVO) throws Exception {
		List<SmsVO> messages = new ArrayList<>();
		String pw = smsService.createRandomNumber(6);
		smsVO.setContent("[한울도서관]\n인증 번호 : " + pw);
		messages.add(smsVO);
		smsService.sendSms(messages);
		return pw;
	}
	
	// 회원가입
	@ResponseBody
	@PostMapping("/joinAjax")
	public boolean joinAjax(MemberVO memberVO) {
		memberVO.setMemPw(encoder.encode(memberVO.getMemPw()));
		return memberService.join(memberVO) == 1;
	}
	
	// 로그인 화면으로
	@GetMapping("/loginForm")
	public String loginForm() {
		return "content/member/login";
	}
	
	// 아이디 찾기 화면으로
	@GetMapping("/findIdForm")
	public String findIdForm() {
		return "content/member/find_id";
	}
	
	// 아이디 찾기
	@ResponseBody
	@PostMapping("/findIdAjax")
	public String findIdAjax(Map<String, Object> mapData) {
		System.out.println(mapData);
		
		
		
		return "";
	}
	
	// 비밀번호 찾기 화면으로
	@GetMapping("/findPwForm")
	public String findPwForm() {
		return "content/member/find_pw";
	}

}
