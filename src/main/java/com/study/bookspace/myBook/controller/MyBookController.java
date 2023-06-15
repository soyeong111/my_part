package com.study.bookspace.myBook.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.menu.vo.SubMenuVO;
import com.study.bookspace.myBook.service.MyBookService;
import com.study.bookspace.myBook.vo.BookRecordVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/mBook")
public class MyBookController {
	
	@Resource(name = "myBookService")
	private MyBookService myBookService;
	
	// 독서기록 페이지로
	@GetMapping("/myBookRecord")
	public String myBookRecord(SubMenuVO subMenuVO, Authentication authentication, Model model) {
		model.addAttribute("bookMapList", myBookService.getBookTitleListThreeMonths(((User)authentication.getPrincipal()).getUsername()));
		return "content/my/my_book_record";
	}
	
	// 독서기록 등록
	@PostMapping("/regBookRecord")
	public String regBookRecord(SubMenuVO subMenuVO, BookRecordVO bookRecordVO, Authentication authentication) {
		bookRecordVO.setMemId(((User)authentication.getPrincipal()).getUsername());
		myBookService.regBookRecord(bookRecordVO);
		return "redirect:/mBook/myBookRecord?mainMenuCode=" + subMenuVO.getMainMenuCode() + "&subMenuCode=" + subMenuVO.getSubMenuCode();
	}
	
	
	
	
	
	
	
	
	

}
