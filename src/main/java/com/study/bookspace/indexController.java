package com.study.bookspace;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.study.bookspace.book.service.BookService;
import com.study.bookspace.book.vo.SearchBookVO;
import com.study.bookspace.club.service.ClubService;
import com.study.bookspace.info.service.AnswerService;
import com.study.bookspace.info.vo.NoticeVO;
import com.study.bookspace.menu.vo.SubMenuVO;
import com.study.bookspace.myBook.service.MyBookService;

import jakarta.annotation.Resource;

@Controller
public class indexController {
	@Resource(name = "clubService")
	private ClubService clubService;
	
	@Resource(name = "bookService")
	private BookService bookService;
	
	@Resource(name = "answerService")
	private AnswerService answerService;
	
	@Resource(name = "myBookService")
	private MyBookService myBookService;
	
	
	@GetMapping("/")
	public String index(SubMenuVO subMenuVO, SearchBookVO searchBookVO, Model model, NoticeVO noticeVO) {
		System.out.println(subMenuVO);
		model.addAttribute("clubList", clubService.getClubList());
		model.addAttribute("noticeList", answerService.noticeForPublic(noticeVO));
		model.addAttribute("bookList", bookService.getNewBookList());
		model.addAttribute("recordList", myBookService.getBookRecordList());
		
		return "content/main";
	}
	
	// 접근 제한 화면으로
	@GetMapping("/accessDeny")
	public String accessDeny() {
		return "content/access_deny";
	}

}
