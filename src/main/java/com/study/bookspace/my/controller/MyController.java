package com.study.bookspace.my.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.menu.vo.SubMenuVO;

@Controller
@RequestMapping("/my")
public class MyController {
	
	// 프로필 페이지로 이동
	@GetMapping("/myProfile")
	public String profile(SubMenuVO subMenuVO) {
		return "content/my/my_profile";
	}
	
	// 내정보 페이지로 이동
	@GetMapping("/myInfo")
	public String myInfo(SubMenuVO subMenuVO) {
		return "content/my/my_info";
	}
	
	// 내북클럽 페이지로 이동
	@GetMapping("/myBookClub")
	public String myBookClub(SubMenuVO subMenuVO) {
		return "content/my/my_book_club";
	}
	
	// 독서기록 페이지로 이동
	@GetMapping("/myReadingRecord")
	public String myReadingRecord(SubMenuVO subMenuVO) {
		return "content/my/my_reading_record";
	}
	
	// 도서대여내역 페이지로 이동
	@GetMapping("/myBorrow")
	public String myBorrow(SubMenuVO subMenuVO) {
		return "content/my/my_borrow";
	}
	
	// 대여예약내역 페이지로 이동
	@GetMapping("/myReserve")
	public String myReserve(SubMenuVO subMenuVO) {
		return "content/my/my_reserve";
	}
	
	// 도서신청내역 페이지로 이동
	@GetMapping("/myRequest")
	public String myRequest(SubMenuVO subMenuVO) {
		return "content/my/my_request";
	}
	
	// 상품구매내역 페이지로 이동
	@GetMapping("/myBuyList")
	public String myBuyList(SubMenuVO subMenuVO) {
		return "content/my/my_buy_list";
	}

}
