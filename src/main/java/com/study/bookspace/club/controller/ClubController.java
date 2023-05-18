package com.study.bookspace.club.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.bookspace.club.service.ClubService;
import com.study.bookspace.club.vo.BookClubVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/club")
public class ClubController {
	@Resource(name = "clubService")
	private ClubService clubService;
	
	//북클럽 정보
	@GetMapping("/clubInfo")
	public String clubInfo(Model model) {
		//북클럽 목록 조회
		model.addAttribute("clubList", clubService.getClubList());
		
		return "content/club/club_info";
	}
	
	//북 클럽 생성화면
	@GetMapping("/regClubForm")
	public String regClubForm() {
		return "content/club/reg_club_form";
	}
	
	//북클럽명 중복체크
	@ResponseBody
	@PostMapping("/isDuplicateClubName")
	public boolean isDuplicateClubName(String clubName) {
		return clubService.isDuplicateClubName(clubName);
	}
	
	//북 클럽 생성
	@PostMapping("/regClub")
	public String regClub(BookClubVO bookClubVO, MultipartFile clubImg) {
		//bookClubVO.setMemId("java");
		clubService.regClub(bookClubVO);
		
		return "redirect:/club/clubInfo";
	}
	
	//북클럽 상세페이지
	@PostMapping("/clubDetail")
	public String clubDetail(Model model, String clubCode) {
		//클럽 상세 조회
		//model.addAttribute("club", clubService.getClubDetail(clubCode));
		
		return "content/club/club_detail";
	}
	
	//회원 북클립 가입(js사용예정)
	@PostMapping("/joinClub")
	public String joinClub() {
		
		return "redirect:/club/clubInfo";
	}
	
	
	
}
