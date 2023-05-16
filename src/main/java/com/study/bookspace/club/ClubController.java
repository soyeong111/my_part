package com.study.bookspace.club;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/club")
public class ClubController {
	
	//북클럽 정보
	@GetMapping("/clubInfo")
	public String clubInfo() {
		return "content/club/club_info";
	}
	
	//북 클럽 생성화면
	@GetMapping("/regClubForm")
	public String regClubForm() {
		return "content/club/reg_club_form";
	}
	
	//북 클럽 생성
	@PostMapping("/regClub")
	public String regClub() {
		return "redirect:/club/clubInfo";
	}
	
	
}
