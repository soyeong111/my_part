package com.study.bookspace.room.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.admin.vo.SubMenuVO;
import com.study.bookspace.room.service.RoomService;
import com.study.bookspace.room.vo.SeatVO;
import com.study.bookspace.room.vo.UseVO;

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
	@RequestMapping("/readingRoom")
	public String readingRoom(SubMenuVO subMenuVO, Model model, String seatCode) {
		
		model.addAttribute("sectionList", roomService.getSectionList());
		//model.addAttribute("seatList", roomService.getSeatDetailList());
		
		//좌석 이용 내역 조회
		model.addAttribute("useList", roomService.getSeatUseList());		
		
		return "content/room/reading_room";
	}
	
	// 열람실 좌석 클릭 시 ajax 실행
	@ResponseBody
	@PostMapping("/readingRoomAjax")
	public SeatVO readingRoomAjax(String seatCode) {
		
		return roomService.getSeatDetail(seatCode);
		
	}
	
	
	//입실 버튼 클릭시 좌석 등록
	@ResponseBody
	@PostMapping("/getSeatAjax")
	public SeatVO getSeatAjax(String seatCode, UseVO useVO, Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		useVO.setMemId(memId);
		
		roomService.getSeat(useVO);
		return roomService.getSeatDetail(seatCode);
	}
	
	//입실 버튼 클릭 시 이미 자리 사용중인 회원인지 확인
	@ResponseBody
	@PostMapping("/isUsingSeatAjax")
	public boolean isUsingSeatAjax(String memId) {
		return roomService.isUsingSeat(memId);
	}
	
	//퇴실 버튼 클릭 시
	@GetMapping("/checkOutSeat")
	public String checkOutSeat(String seatCode, SubMenuVO subMenuVO) {
		
		roomService.checkOutSeat(seatCode);
		
		return "redirect:/room/readingRoom?mainMenuCode=" 
			+ subMenuVO.getMainMenuCode() + "&subMenuCode=" + subMenuVO.getSubMenuCode();
	}
	
	
	
	
	
	
}
