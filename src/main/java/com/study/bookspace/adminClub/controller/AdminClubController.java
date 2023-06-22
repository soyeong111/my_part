package com.study.bookspace.adminClub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.club.service.ClubService;
import com.study.bookspace.menu.vo.SubMenuVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/aClub")
public class AdminClubController {
	@Resource(name = "clubService")
	private ClubService clubService;
	
	//북클럽 관리 페이지
	@GetMapping("/clubManage")
	public String clubManage(SubMenuVO subMenuVO, Model model) {
		
		model.addAttribute("clubList", clubService.getBookClubListForAdmin());
		
		return "content/admin/club_manage";
	}
	
	
}
