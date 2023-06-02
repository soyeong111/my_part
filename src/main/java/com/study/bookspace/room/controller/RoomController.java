package com.study.bookspace.room.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.admin.vo.SubMenuVO;
import com.study.bookspace.room.service.RoomService;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/room")
public class RoomController {
	@Resource(name = "roomService")
	private RoomService roomService;
	
	//열람실 안내 페이지
	@GetMapping("/readingRoomInfo")
	public String readinRoomInfo(SubMenuVO subMenuVO) {
		return "content/room/reading_room_info";
	}
	
	//열람실 현황 페이지
	@GetMapping("/readingRoom")
	public String readingRoom(SubMenuVO subMenuVO, Model model) {
		
		model.addAttribute("sectionList", roomService.getSectionList());
		
		return "content/room/reading_room";
	}
	
	// 열람실 좌석 클릭 시 ajax 실행
	@ResponseBody
	@PostMapping("/readingRoomAjax")
	public void readingRoomAjax(String seatCode) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
