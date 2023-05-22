package com.study.bookspace.room.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.admin.vo.SubMenuVO;

@Controller
@RequestMapping("/room")
public class RoomController {
	
	//열람실 페이지
	@GetMapping("/readingRoom")
	public String readinRoom(SubMenuVO subMenuVO) {
		return "content/room/reading_room";
	}
}
