package com.study.bookspace.book.controller;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.bookspace.admin.vo.SubMenuVO;
import com.study.bookspace.book.service.BookService;
import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.BorrowVO;
import com.study.bookspace.book.vo.ImgVO;
import com.study.bookspace.util.UploadUtil;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/book")
public class BookController {
	@Resource(name = "bookService")
	private BookService bookService;
	
//	도서 목록 조회
	@GetMapping("/bookList")
	public String bookList(Model model, SubMenuVO subMenuVO) {
		
		// 나중에 삭제 System.out.println(bookService.getBookListForUser());
		
		model.addAttribute("bookList", bookService.getBookListForUser());
		
		return "content/book/book_list";
	}

	
//	도서 등록
	@PostMapping("/regBookProcess")
	public String regBook(BookVO bookVO ,MultipartFile mainImg, MultipartFile[] subImg) {
		
		//--- 파일 첨부 ---//
//		메인 이미지 업로드
		ImgVO attachedimgVO = UploadUtil.uploadFile(mainImg);
		
//		서브 이미지 업로드
	    List<ImgVO> attachedImgList = UploadUtil.multiFileUpload(subImg);

//	    등록될 도서코드 조회
	    String bookCode = bookService.getNextBookCode();
	    bookVO.setBookCode(bookCode);
	    
	    
		
//		------도서 이미지 DB 등록-------
//		도서 이미지 등록 쿼리 실행 시 모든 빈 값을 채워줄 데이터를 가진 List
	    List<ImgVO> imgList = attachedImgList;
	    imgList.add(attachedimgVO);
	    
//		BOOK_CODE 데이터 추가
		for(ImgVO img : imgList) {
			img.setBookCode(bookCode);
			
		}
	    
//		모든 이미지정보를 갖고 있는 imgList를 bookVO에 집어넣기
	    bookVO.setImgList(imgList);
	    
//	    도서 등록쿼리
	    bookService.regBook(bookVO);
		
		
	    return "redirect:/book/regBook";
		
	}
	
	
//	도서 등록 페이지
	@GetMapping("/regBook")
	public String regBook(Model model, BookVO bookVO) {
		
		model.addAttribute("categoryList", bookService.getCateListInUse());
		
		return "content/book/book_reg";
		
	}
	

//	도서 상세 페이지
	@GetMapping("/bookDetail")
	public String bookDetail(Model model, String bookCode, HttpServletRequest request, SubMenuVO subMenuVO) {
		
		 String data = request.getHeader("Referer");
		 System.out.println("@@@@@@@@" + data);
		 
//		 도서 상세 정보
		 model.addAttribute("book", bookService.getBookDetail(bookCode));
		 
		return "content/book/book_detail";
		
	}
	
	
//	도서 관리 페이지
//	@RequestMapping("/bookManage")
//	public String bookManage(Model model, SubMenuVO subMenuVO, BookVO bookVO) {
//		
//		model.addAttribute("cate)
//		
//	}
	
	
//	도서 대여
	@ResponseBody
	@PostMapping("/borrowAjax")
	public void borrowAjax(BorrowVO borrowVO, HttpSession session) {
		
		borrowVO.setMemId(SecurityContextHolder.getContext().getAuthentication().getName());
		
		bookService.borrowBook(borrowVO);
	}
	
	
	
//	신작도서
	@GetMapping("/newBook")
	public String newBook() {
		
		return "content/book/new_book";
	}
	
	
	
//	베스트 셀러
	@GetMapping("/bestBook")
	public String bestBook() {
		
		return "content/book/best_book";
	}
	
}
