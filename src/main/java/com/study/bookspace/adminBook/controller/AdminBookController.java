package com.study.bookspace.adminBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.book.service.BookService;
import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.ImgVO;
import com.study.bookspace.menu.vo.SubMenuVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/aBook")
public class AdminBookController {
	@Resource(name="bookService")
	private BookService bookService;
	
	
//	도서 관리) 소장 도서 관리
	@RequestMapping("/bookManage")
	public String bookManage(Model model, BookVO bookVO, SubMenuVO subMenuVO, String bookCode, ImgVO imgVO) {
		
//		카테고리 목록 (전체)
		model.addAttribute("categoryList", bookService.getCateListForAdmin());
		
		
		String bookImgCode = imgVO.getBookImgCode();
		String originFileName = imgVO.getOriginFileName();
		String bookIntro = imgVO.getBookIntro();
		String attachedFileName = imgVO.getAttachedFileName();
		String isMainImg = imgVO.getIsMainImg();
		
		System.out.println("@@@@@@@@@@@@@" + bookIntro);
		System.out.println("@@@@@@@@@@@@@" + attachedFileName);
		//전체 게시글 수 조회
		//int totalDataCnt = bookService.getBoardCnt(bookVO.getBookCode());
		
		//전체 데이터 수 세팅
		//bookVO.setTotalDataCnt(totalDataCnt);
		
		//페이징 정보 세팅
		//bookVO.setPageInfo();
		
//		도서 목록 조회
		model.addAttribute("bookList", bookService.getBookListForAdminManage(bookVO));
		
		return "content/admin/book_manage";
	}

	
}
