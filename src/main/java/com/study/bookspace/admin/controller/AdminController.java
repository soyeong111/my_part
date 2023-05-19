package com.study.bookspace.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.admin.vo.SubMenuVO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	// 도서관리 페이지로 이동
	@GetMapping("/bookManage")
	public String bookManage(SubMenuVO subMenuVO) {
		return "content/admin/book_manage";
	}
	
	// 도서신청관리 페이지로 이동
	@GetMapping("/requestManage")
	public String requestManage(SubMenuVO subMenuVO) {
		return "content/admin/request_manage";
	}
	
	// 도서예약관리 페이지로 이동
	@GetMapping("/reserveManage")
	public String reserveManage(SubMenuVO subMenuVO) {
		return "content/admin/reserve_manage";
	}
	
	// 도서대여관리 페이지로 이동
	@GetMapping("/borrowManage")
	public String borrowManage(SubMenuVO subMenuVO) {
		return "content/admin/borrow_manage";
	}
	
	// 회원관리 페이지로 이동
	@GetMapping("/memberManage")
	public String memberManage(SubMenuVO subMenuVO) {
		return "content/admin/member_manage";
	}
	
	// 북클럽관리 페이지로 이동
	@GetMapping("/clubManage")
	public String clubManage(SubMenuVO subMenuVO) {
		return "content/admin/club_manage";
	}
	
	// 상품관리 페이지로 이동
	@GetMapping("/goodsManage")
	public String goodsManage(SubMenuVO subMenuVO) {
		return "content/admin/goods_manage";
	}
	
	// 상품구매관리 페이지로 이동
	@GetMapping("/buyManage")
	public String buyManage(SubMenuVO subMenuVO) {
		return "content/admin/buy_manage";
	}

}
