package com.study.bookspace.adminClub.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.club.service.ClubService;
import com.study.bookspace.menu.vo.SubMenuVO;
import com.study.bookspace.util.ConstVariable;

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
		//model.addAttribute("memCnt", clubService.getc)
		
		return "content/admin/club_manage";
	}
	
	//북클럽 삭제 Ajax
	@ResponseBody
	@PostMapping("/deleteClubAjax")
	private void deleteClubAjax(String clubCode) {
		
		String clubImgName = clubService.getClubImageName(clubCode);
		
		File file = new File(ConstVariable.CLUB_UPLOAD_PATH + clubImgName);
		file.delete();		
		
		clubService.deleteClub(clubCode);
	}
	
}
