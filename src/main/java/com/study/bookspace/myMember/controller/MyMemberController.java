package com.study.bookspace.myMember.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.bookspace.member.service.MemberService;
import com.study.bookspace.member.vo.MemberVO;
import com.study.bookspace.menu.vo.SubMenuVO;
import com.study.bookspace.myMember.service.MyMemberService;
import com.study.bookspace.sms.SmsService;
import com.study.bookspace.sms.SmsVO;
import com.study.bookspace.util.ConstVariable;
import com.study.bookspace.util.DateUtil;
import com.study.bookspace.util.MailService;
import com.study.bookspace.util.MailVO;
import com.study.bookspace.util.UploadUtil;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/mMember")
public class MyMemberController {
	
	@Resource(name = "myMemberService")
	private MyMemberService myMemberService;
	
	@Resource(name = "memberService")
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Resource(name = "mailService")
	private MailService mailService;
	
	@Resource(name = "smsService")
	private SmsService smsService;
	
	// 내프로필 페이지
	@GetMapping("/myProfile")
	public String myProfile(SubMenuVO subMenuVO, Model model, Authentication authentication, MemberVO memberVO, String nowYear) {
		memberVO.setMemId(((User)authentication.getPrincipal()).getUsername());
		memberVO = myMemberService.getMyProfile(memberVO.getMemId());
		model.addAttribute("memberVO", memberVO);
		if (nowYear == null) {
			nowYear = DateUtil.getNowYear() + "";
		}
		model.addAttribute("nowYear", nowYear);
		model.addAttribute("yearList", DateUtil.getFiveYears());
		return "content/my/my_profile";
	}
	
	// 차트 데이터 받기
	@ResponseBody
	@PostMapping("/getChartDataAjax")
	public List<Map<String, Object>> getChartDataAjax(String nowYear, Authentication authentication) {
		Map<String, String> mapData = new HashMap<>();
		mapData.put("nowYear", nowYear);
		mapData.put("memId", ((User)authentication.getPrincipal()).getUsername());
		return myMemberService.getMyBorrowCntListForChart(mapData);
	}
	
	// 내 프로필 사진 등록
	@ResponseBody
	@PostMapping("/updateMemImgAjax")
	public int updateMemImgAjax(MemberVO memberVO, MultipartFile memImg, Authentication authentication) {
		memberVO.setMemImgUrl(UploadUtil.uploadMemberFile(memImg));
		if (!memberVO.getMemImgUrl().equals("")) {
			memberVO.setMemId(((User)authentication.getPrincipal()).getUsername());
			return myMemberService.updateMemImg(memberVO);
		}
		return 0;
	}
	
	// 내 프로필 사진 삭제
	@ResponseBody
	@PostMapping("/deleteMemImgAjax")
	public int deleteMemImgAjax(MemberVO memberVO, Authentication authentication) {
		memberVO.setMemId(((User)authentication.getPrincipal()).getUsername());
		String imgUrl = ConstVariable.MEMBER_UPLOAD_PATH + memberVO.getMemImgUrl();
		memberVO.setMemImgUrl("");
		int result = myMemberService.updateMemImg(memberVO);
		if (result == 1) {
			File file = new File(imgUrl);
			file.delete();
		}
		return result;
	}
	
	// 내정보변경 페이지
	@GetMapping("/myInfo")
	public String myInfo(SubMenuVO subMenuVO, Model model, Authentication authentication) {
		MemberVO memberVO = myMemberService.getMemberInfo(((User)authentication.getPrincipal()).getUsername());
		model.addAttribute("memberVO", memberVO);
		return "content/my/my_info";
	}
	
	// 내정보변경
	@ResponseBody
	@PostMapping("/updateMemberAjax")
	public boolean updateMemberAjax(MemberVO memberVO) {
		return myMemberService.updateMember(memberVO) == 1;
	}
	
	// 이메일 인증
	@ResponseBody
	@PostMapping("/emailAuthAjax")
	public String emailAuthAjax(String email, MailVO mailVO) {
		List<String> recipientList = new ArrayList<>();
		recipientList.add(email);
		mailVO.setRecipientList(recipientList);
		mailVO.setTitle("한울도서관");
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
	
	// 비밀번호 변경
	@ResponseBody
	@PostMapping("/changePwAjax")
	public int changePwAjax(MemberVO memberVO, Authentication authentication) {
		memberVO.setMemId(((User)authentication.getPrincipal()).getUsername());
		String encMemPw = myMemberService.getPwById(memberVO.getMemId());
		if (encoder.matches(memberVO.getMemPw(), encMemPw)) {
			return 2;
		}
		memberVO.setMemPw(encoder.encode(memberVO.getMemPw()));
		return memberService.changePw(memberVO);
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
	
	// 회원 탈퇴 페이지
	@GetMapping("/withdrawalForm")
	public String withdrawalForm(SubMenuVO subMenuVO) {
		return "content/my/withdrawal";
	}
	
	// 회원 탈퇴
	@ResponseBody
	@PostMapping("/withdrawalAjax")
	public boolean withdrawalAjax(Authentication authentication) {
		String memId = ((User)authentication.getPrincipal()).getUsername();
		String memImgUrl = myMemberService.getMemImgUrlForWithdrawal(memId);
		int result = myMemberService.withdrawal(memId);
		if (memImgUrl != null && result == 1) {
			File file = new File(memImgUrl);
			file.delete();
		}
		return result == 1;
	}
	
}
