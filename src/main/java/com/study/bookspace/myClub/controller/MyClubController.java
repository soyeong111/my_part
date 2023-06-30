package com.study.bookspace.myClub.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.alram.service.AlramService;
import com.study.bookspace.alram.vo.AlramVO;
import com.study.bookspace.club.service.ClubService;
import com.study.bookspace.club.vo.BookClubMemberVO;
import com.study.bookspace.menu.vo.SubMenuVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/mClub")
public class MyClubController {
	@Resource(name = "clubService")
	private ClubService clubService;
	
	@Resource(name = "alramService")
	private AlramService alramService;
	
	//내가 가입한 북클럽
	@GetMapping("/myBookClub")
	public String myBookClub(SubMenuVO subMenuVO,Model model, Authentication authentication, BookClubMemberVO bookClubMemberVO) {
		
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		bookClubMemberVO.setMemId(memId);
		
		
		//내 북클럽 상태 조회
		model.addAttribute("myClubDetail", clubService.getMyClubDetail(memId));
		System.out.println("!!!!!!!!!!!!!!!1" + bookClubMemberVO);
		model.addAttribute("hasApprovedClubs", clubService.getMyClubDetail(memId).stream().anyMatch(club -> club.getClubMemStatus() == 2));
		model.addAttribute("hasApproveWaitClubs", clubService.getMyClubDetail(memId).stream().anyMatch(club -> club.getClubMemStatus() == 1));
		
		return "content/my/my_book_club";
	}
	
	//내가 만든 북클럽
	@GetMapping("/myCreatedClub")
	public String myCreatedClub(SubMenuVO subMenuVO, Model model, Authentication authentication) {
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		String clubCode = clubService.getClubCode(memId);
		
		if(clubCode != null) {
			//북클럽 회원 목록 조회
			model.addAttribute("memberList", clubService.getClubMemberList(clubCode));
			//북클럽 가입 신청 회원 목록 조회 (승인 전)
			model.addAttribute("applyList", clubService.getApplyMemberList(clubCode));
			//클럽장 아이디 조회
			model.addAttribute("bossId", clubService.getClubBossId(clubCode));
			//클럽코드
			model.addAttribute("clubCode", clubCode);
		}
		
		return "content/my/my_created_club";
	}
	
	//북클럽 회원 승인
	@ResponseBody
	@PostMapping("/acceptMemberAjax")
	public void acceptMemberAjax(String acceptCode, AlramVO alramVO, SubMenuVO subMenuVO) {
		
		String alramId = clubService.acceptMember(acceptCode);
		
		
		alramVO.setMemId(alramId);
		alramVO.setAContent("북클럽 가입 승인이 완료되었습니다.");
		alramVO.setSection(2);
		alramService.insertAlram(alramVO);
	}
	
	//북클럽 회원 거절
	@ResponseBody
	@PostMapping("/refuseMemberAjax")
	public void refuseMemberAjax(String acceptCode, AlramVO alramVO, SubMenuVO subMenuVO) {
		
		
		String alramId = clubService.refuseApply(acceptCode);
		
		alramVO.setMemId(alramId);
		alramVO.setAContent("북클럽 가입신청이 거절되었습니다.");
		alramVO.setSection(2);
		alramService.insertAlram(alramVO);
		
	}
	
	//북클럽 회원 강퇴
	@ResponseBody
	@PostMapping("/kickOutMemberAjax")
	public void kickOutMemberAjax(String acceptCode, AlramVO alramVO, SubMenuVO subMenuVO) {
		
		String alramId = clubService.kickOutMember(acceptCode);
		
		
		alramVO.setMemId(alramId);
		alramVO.setAContent("가입한 북클럽에서 강퇴당했습니다.");
		alramVO.setSection(2);
		alramService.insertAlram(alramVO);
		
	}
	
	//북클럽 신청 취소
	@ResponseBody
	@PostMapping("/deleteBtnAjax")
	public void cancelApplyAjax(String acceptCode, SubMenuVO subMenuVO) {
		clubService.cancelApply(acceptCode);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
