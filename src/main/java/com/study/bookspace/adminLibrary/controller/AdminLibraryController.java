package com.study.bookspace.adminLibrary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.admin.vo.SubMenuVO;
import com.study.bookspace.room.service.RoomService;
import com.study.bookspace.room.vo.SeatListSearchVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/aLibrary")
public class AdminLibraryController {
	@Resource(name = "roomService")
	private RoomService roomService;
	
	//열람실 관리 페이지로 이동
	@RequestMapping("/readingRoomManage")
	public String readingRoomManage(SubMenuVO subMenuVO, Model model, SeatListSearchVO seatListSearchVO) {
		
		model.addAttribute("useList", roomService.getSeatUseList(seatListSearchVO));
		
		return "content/admin/reading_room_manage";
	}
	
	//전체퇴실 버튼 클릭 시
	@GetMapping("/allCheckOut")
	public String allCheckOut() {
		
		roomService.allCheckOut();
		
		return "redirect:/aLibrary/readingRoomManage"; 
	}
		
	
	
	
	
	
	
	
	
	
}
