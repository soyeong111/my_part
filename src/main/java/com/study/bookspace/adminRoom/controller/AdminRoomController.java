package com.study.bookspace.adminRoom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.admin.vo.SubMenuVO;
import com.study.bookspace.room.service.RoomService;
import com.study.bookspace.room.vo.SeatListSearchVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("aRoom")
public class AdminRoomController {
	@Resource(name = "roomService")
	private RoomService roomService;
	
	//열람실 관리 페이지로 이동
	@RequestMapping("/readingRoomManage")
	public String readingRoomManage(SubMenuVO subMenuVO, Model model, SeatListSearchVO seatListSearchVO) {
		
		model.addAttribute("useList", roomService.getSeatUseList(seatListSearchVO));
		
		return "content/admin/reading_room_manage";
	}
}
