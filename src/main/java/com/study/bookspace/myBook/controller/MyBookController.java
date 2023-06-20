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
import com.study.bookspace.myBook.vo.BookRecordSearchVO;
import com.study.bookspace.myBook.vo.BookRecordVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/mBook")
public class MyBookController {
	
	@Resource(name = "myBookService")
	private MyBookService myBookService;
	
	// 독서 기록 페이지로
	@RequestMapping("/myBookRecord")
	public String myBookRecord(SubMenuVO subMenuVO, Authentication authentication, Model model, BookRecordSearchVO bookRecordSearchVO) {
		System.out.println(bookRecordSearchVO);
		bookRecordSearchVO.setSearchMemId(((User)authentication.getPrincipal()).getUsername());
		model.addAttribute("bookMapList", myBookService.getBookTitleListThreeMonths(bookRecordSearchVO.getSearchMemId()));
		if (bookRecordSearchVO.getSearchOrder() == null) {
			bookRecordSearchVO.setSearchOrder("DESC");
		}
		bookRecordSearchVO.setDisplayPageCnt(5);
		bookRecordSearchVO.setDisplayDataCnt(6);
		bookRecordSearchVO.setTotalDataCnt(myBookService.getBookRecordDataCnt(bookRecordSearchVO));
		bookRecordSearchVO.setPageInfo();
		model.addAttribute("recordList", myBookService.getMyBookRecord(bookRecordSearchVO));
		System.out.println(bookRecordSearchVO);
		return "content/my/my_book_record";
	}
	
	// 독서 기록 등록
	@PostMapping("/regBookRecord")
	public String regBookRecord(SubMenuVO subMenuVO, BookRecordVO bookRecordVO, Authentication authentication) {
		bookRecordVO.setMemId(((User)authentication.getPrincipal()).getUsername());
		myBookService.regBookRecord(bookRecordVO);
		return "redirect:/mBook/myBookRecord?mainMenuCode=" + subMenuVO.getMainMenuCode() + "&subMenuCode=" + subMenuVO.getSubMenuCode();
	}
	
	// 독서 기록 삭제
	@ResponseBody
	@PostMapping("/deleteBookRecordAjax")
	public boolean deleteBookRecordAjax(String recordCode) {
		return myBookService.deleteBookRecord(recordCode) == 1;
	}
	
	// 독서 기록 수정
	@ResponseBody
	@PostMapping("/updateBookRecordAjax")
	public boolean updateBookRecordAjax(BookRecordVO bookRecordVO) {
		return myBookService.updateBookRecord(bookRecordVO) == 1;
	}
	
}
