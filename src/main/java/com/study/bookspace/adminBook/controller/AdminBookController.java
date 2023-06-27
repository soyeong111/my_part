package com.study.bookspace.adminBook.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.book.service.BookService;
import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.BorrowVO;
import com.study.bookspace.book.vo.ImgVO;
import com.study.bookspace.book.vo.ReserveVO;
import com.study.bookspace.book.vo.SearchBookVO;
import com.study.bookspace.menu.vo.SubMenuVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/aBook")
public class AdminBookController {
	@Resource(name="bookService")
	private BookService bookService;
	
	
//	도서 관리) 소장 도서 관리
	@RequestMapping("/bookManage")
	public String bookManage(Model model, BookVO bookVO, SubMenuVO subMenuVO, String bookCode) {
		
//		카테고리 목록 (전체)
		model.addAttribute("categoryList", bookService.getCateListForAdmin());
		
		System.out.println(bookVO + "!!!!!!!!!!!!!!!!!!!!");
	
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

	
//	도서 등록 페이지
	@GetMapping("/regBook")
	public String regBook(Model model, BookVO bookVO, SubMenuVO subMenuVO) {
		
		model.addAttribute("categoryList", bookService.getCateListInUse());
		
		return "content/book/book_reg";
		
	}
	
	
	
	
//	도서관리) 도서 표 수정
	@ResponseBody
	@PostMapping("/updateBookAjax")
	public void updateBook(BookVO bookVO) {
		System.out.println("zzzzzzzzzz" + bookVO);
		bookService.updateBook(bookVO);
		
		
	}
	
	
	
//	대여관리) 대여 관리
	@GetMapping("/borrowManage")
	public String borrowManage(Model model, SubMenuVO subMenuVO) {
		
		model.addAttribute("borrowList", bookService.borrowManage());
		
		model.addAttribute("top5List", bookService.getBorrowTop5());
		
		return "content/admin/borrow_manage";
	}
	
//	대여관리) 카테고리별 대여 그래프
	@ResponseBody
	@PostMapping("/cateBorrowAjax")
	public List<Map<String, Object>> cateBorrow() {
		
		
		return bookService.cateBorrow();
	}
	
	
	
	
//	도서관리) 대여 관리 연체로 변경
	@ResponseBody
	@PostMapping("/overDueAjax")
	public void overDue(BorrowVO borrowVO) {
		bookService.overDue(borrowVO);
	}
	
	
//	도서관리) 예약 관리
	@GetMapping("/reserveManage")
	public String reserveManage(Model model, SubMenuVO subMenuVO) {
		
		model.addAttribute("reserveList", bookService.reserveManage());
		
		return "content/admin/reserve_manage";
	}
	
	
//	도서관리) 예약 단일 삭제 
	@ResponseBody
	@PostMapping("/delReserveAjax")
	public void delReserveAjax(ReserveVO reserveVO) {
		bookService.cancelReserve(reserveVO);
	}
	

//	도서 상세 페이지
	@GetMapping("/bookDetail")
	public String bookDetail(Model model, String bookCode, SubMenuVO subMenuVO) {
		 
//		 도서 상세 정보
		 model.addAttribute("book", bookService.getBookDetail(bookCode));
		 
		return "content/book/book_detail";
		
	}
	
//	도서 목록 조회
	@RequestMapping("/bookList")
	public String bookList(Model model, SubMenuVO subMenuVO,SearchBookVO searchBookVO) {
		
		// 나중에 삭제 System.out.println(bookService.getBookListForUser());
	
		
		
		//전체 게시글 수 조회
		int totalDataCnt = bookService.getBookCnt(searchBookVO);
		
		System.out.println(totalDataCnt + "dfsfsdfdsfsfsd");
		//전체 데이터 수 세팅
		searchBookVO.setTotalDataCnt(totalDataCnt);
		
		//페이징 정보 세팅
		searchBookVO.setPageInfo();
		
		
		System.out.println(searchBookVO + "ddddddddddd");
		
		
		List<BookVO> bookList = bookService.getBookListForUser(searchBookVO);
		model.addAttribute("bookList", bookList);
		
		
		return "content/book/book_list";
		
	}

	
	
}
