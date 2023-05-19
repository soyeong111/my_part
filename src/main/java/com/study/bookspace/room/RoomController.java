package com.study.bookspace.room;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room")
public class RoomController {
	
	//열람실 페이지
	@GetMapping("/readingRoom")
	public String readinRoom() {
		return "content/room/reading_room";
	}
}
