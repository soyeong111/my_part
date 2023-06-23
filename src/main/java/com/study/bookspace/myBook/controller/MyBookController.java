package com.study.bookspace.myBook.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.book.service.BookService;
import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.BorrowVO;
import com.study.bookspace.book.vo.ReserveVO;
import com.study.bookspace.menu.vo.SubMenuVO;
import com.study.bookspace.myBook.service.MyBookService;
import com.study.bookspace.myBook.vo.BookRecordSearchVO;
import com.study.bookspace.myBook.vo.BookRecordVO;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/mBook")
public class MyBookController {
	
	@Resource(name = "myBookService")
	private MyBookService myBookService;
	
	@Resource(name = "bookService")
	private BookService bookService;
	
	// 독서 기록 페이지로
	@RequestMapping("/myBookRecord")
	public String myBookRecord(SubMenuVO subMenuVO, Authentication authentication, Model model, BookRecordSearchVO bookRecordSearchVO) {
		bookRecordSearchVO.setSearchMemId(((User)authentication.getPrincipal()).getUsername());
		if (bookRecordSearchVO.getSearchOrder() == null) {
			bookRecordSearchVO.setSearchOrder("DESC");
		}
		bookRecordSearchVO.setDisplayPageCnt(5);
		bookRecordSearchVO.setDisplayDataCnt(6);
		bookRecordSearchVO.setTotalDataCnt(myBookService.getBookRecordDataCnt(bookRecordSearchVO));
		bookRecordSearchVO.setPageInfo();
		model.addAttribute("recordList", myBookService.getMyBookRecord(bookRecordSearchVO));
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
	
	// 도서 검색 모달
	@ResponseBody
	@PostMapping("/bookSearchAjax")
	public List<BookVO> bookSearchAjax(@RequestBody Map<String, Object> mapData) {
		return myBookService.bookSearchForModal(mapData);
	}
	
	
// 	내 정보)) 도서 대여 관리
	@GetMapping("/myBorrow")
	public String borrowManage(Model model, SubMenuVO subMenuVO, HttpSession session) {
		
		String memId = SecurityContextHolder.getContext().getAuthentication().getName();
	    BorrowVO borrowVO = new BorrowVO();
	    borrowVO.setMemId(memId);
	    

		model.addAttribute("myBorrowList", bookService.myBorrow(borrowVO));
		
		return "content/my/my_borrow";
	}
	
	
// 	내 정보)) 도서 예약 관리
	@GetMapping("/myReserve")
	public String reservewManage(Model model, SubMenuVO subMenuVO, HttpSession session, ReserveVO reserveVO) {
		
		String memId = SecurityContextHolder.getContext().getAuthentication().getName();
		
	    reserveVO.setMemId(memId);
	    
	    model.addAttribute("myReserveList", bookService.myReserve(reserveVO));

	    
		return "content/my/my_reserve";
	}
	
	
	
}
