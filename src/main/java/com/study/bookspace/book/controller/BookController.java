package com.study.bookspace.book.controller;

import java.util.Arrays;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.bookspace.alram.vo.AlramVO;
import com.study.bookspace.book.service.BookService;
import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.BorrowVO;
import com.study.bookspace.book.vo.CategoryVO;
import com.study.bookspace.book.vo.ImgVO;
import com.study.bookspace.book.vo.ReserveVO;
import com.study.bookspace.book.vo.SearchBookVO;
import com.study.bookspace.menu.vo.SubMenuVO;
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
	@RequestMapping("/bookList")
	public String bookList(Model model, SubMenuVO subMenuVO,SearchBookVO searchBookVO, BookVO bookVO) {
		
		// 나중에 삭제 System.out.println(bookService.getBookListForUser());
		
		//전체 게시글 수 조회
		int totalDataCnt = bookService.getBookCnt();
		
		//전체 데이터 수 세팅
		bookVO.setTotalDataCnt(totalDataCnt);
		
		//페이징 정보 세팅
		bookVO.setPageInfo();
		
		List<BookVO> bookList = bookService.getBookListForUser(searchBookVO);
		model.addAttribute("bookList", bookList);
		
		
		return "content/book/book_list";
	}

	
//	신작도서
	@GetMapping("/newBook")
	public String newBook(Model model, SubMenuVO subMenuVO) {
		
		model.addAttribute("bookList", bookService.getNewBookList());
		
		return "content/book/new_book";
	}
	
	
	
//	베스트 셀러
	@GetMapping("/bestBook")
	public String bestBook(Model model, SubMenuVO subMenuVO) {
		
		model.addAttribute("bookList", bookService.getBestBookList());
		
		return "content/book/best_book";
	}
	
	
	
//	도서 등록
	@PostMapping("/regBookProcess")
	public String regBook(BookVO bookVO ,MultipartFile mainImg, MultipartFile[] subImg, MultipartFile sideImg) {
		
		//--- 파일 첨부 ---//
//		메인 이미지 업로드 (앞)
		ImgVO attachedimgVO = UploadUtil.uploadFile(mainImg);
		
//		서브 이미지 업로드 (뒤)
	    List<ImgVO> attachedImgList = UploadUtil.multiFileUpload(subImg);
	    
// 		옆 이미지 업로드
	    ImgVO sideImgVO = UploadUtil.uploadSideImage(sideImg);
	    
	    
//	    등록될 도서코드 조회
	    String bookCode = bookService.getNextBookCode();
	    bookVO.setBookCode(bookCode);
	    
	    
		
//		------도서 이미지 DB 등록-------
//		도서 이미지 등록 쿼리 실행 시 모든 빈 값을 채워줄 데이터를 가진 List
	    List<ImgVO> imgList = attachedImgList;
	    imgList.add(attachedimgVO);
	    imgList.add(sideImgVO);
	    
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
	public int borrowAjax(BorrowVO borrowVO, HttpSession session, ReserveVO reserveVO, String bookCode) {
		
		borrowVO.setMemId(SecurityContextHolder.getContext().getAuthentication().getName());
		
//		중복 대여
		int checkBorrowStatus = bookService.checkBorrowStatus(borrowVO);
//			중복 대여 시
		if(checkBorrowStatus != 0) {
				return 1;
			}
		
//		대여 개수 제한 (5권 이상 대여 금지)
		int getBorrowLimit = bookService.getBorrowLimit(borrowVO);
			if(getBorrowLimit == 4) {
				return 4;
			}
			 
//		도서 대여
		 bookService.borrowBook(borrowVO);
		 
//		 예약자 ID
//		 bookService.delReserve(bookCode);
		 return 0;
		 
	}
	
	

	
	@ResponseBody
	@PostMapping("/reserveAjax")
	public int reserveAjax(HttpSession session, ReserveVO reserveVO) {
		reserveVO.setMemId(SecurityContextHolder.getContext().getAuthentication().getName());
		
		BorrowVO borrowVO = new BorrowVO();
		
//		중복 대여
		int checkBorrowStatus = bookService.checkBorrowStatus(borrowVO);
//			중복 대여 시
		if(checkBorrowStatus != 0) {
				return 1;
			}
		
//		 중복 예약
		 int checkReserveStatus = bookService.checkReserveStatus(reserveVO);
//		 	중복 예약 시
		 if(checkReserveStatus !=0) {
			 return 11;
		 }
		 
//		 예약 개수 제한 (2권 이상 예약 금지)
		 int getReserveLimit = bookService.getReserveLimit(reserveVO);
		 if(getReserveLimit == 2) {
			 return 2;
		 }
		 
//		 도서 예약 
		 bookService.reserveBook(reserveVO);
		 return 0;
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
	

	
//	도서 반납
	@ResponseBody
	@PostMapping("/returnBookAjax")
	public void returnBookAjax(BorrowVO borrowVO, HttpSession session, AlramVO alramVO, String bookCode) {
		borrowVO.setMemId(SecurityContextHolder.getContext().getAuthentication().getName());
		
//		도서 반납
		bookService.returnBook(borrowVO);
		
//		String reserveId = bookService.getReserveId(bookCode);
//		
//		alramVO.setMemId(reserveId);
//		alramVO.setAContent("예약하신 도서를 대여할 수 있습니다.");
//		alramVO.setSection(1);
		
	}
	
	
//	내 정보) 도서 반납 연장
	@ResponseBody
	@PostMapping("/extendAjax")
		public String extendAjax(HttpSession session, BorrowVO borrowVO) {
			borrowVO.setMemId(SecurityContextHolder.getContext().getAuthentication().getName());
			
//			반납 연장
			bookService.extendBorrow(borrowVO);
			
//			변경 된 반납기한
			return bookService.getReturnDuedate(borrowVO.getBorrowCode());
		}
	
	
//	내 정보) 다른 회원이 예약한 도서인지 확인
	
	@ResponseBody
	@PostMapping("/checkReserveBeforeExtendAjax")
	public int checkReserveBeforeExtendAjax(HttpSession session, ReserveVO reserveVO) {
		
		reserveVO.setMemId(SecurityContextHolder.getContext().getAuthentication().getName());
		
		
//		예약여부 확인
		int checkReserveBeforeExtend = bookService.checkReserveBeforeExtend(reserveVO);
//			예약 시
		if(checkReserveBeforeExtend >= 1) {
			return 1;
		}
		return 0;
	}
	

	
//	도서 관리) 소장 도서 관리
	@RequestMapping("/bookManage")
	public String bookManage(Model model, BookVO bookVO, SubMenuVO subMenuVO) {
		
//		카테고리 목록 (전체)
		model.addAttribute("categoryList", bookService.getCateListForAdmin());
		
		ImgVO imgVO = new ImgVO();
		String bookImgCode = imgVO.getBookImgCode();
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
	
	@ResponseBody
	@PostMapping("/categoryListAjax")
	public List<CategoryVO> categoryListAjax() {
		
//		카테고리 목록 (전체)
		return bookService.getCateListForAdmin();
	}
	
	
	@ResponseBody
	@PostMapping("/imgListAjax")
	public List<ImgVO> imgListAjax(String bookCode) {
			
//		이미지목록 (전체)
		return bookService.getImgListForBook(bookCode);
	}
	

//	도서 관리) 도서 삭제
	@GetMapping("/deleteBook")
	public String deleteBook(String[] bookCodes ,BookVO bookVO) {
		
//		배열을 리스트로
		List<String> bookCodeList = Arrays.asList(bookCodes);
		
		bookVO.setBookCodeList(bookCodeList);
		// 도서 삭제
		bookService.deleteBook(bookVO);
		
		return "redirect:/book/bookManage";
		
	}
	
//	도서 관리) 도서 메인 이미지 삭제
	@ResponseBody
	@PostMapping("/deleteMainImgAjax")
	public void deleteMainImgAjax(String bookImgCode) {
		bookService.deleteMainImg(bookImgCode);
	}
	
	
//	도서 관리) 도서 서브 이미지 삭제
	@ResponseBody
	@PostMapping("/deleteSubImgAjax")
	public void deleteSubImgAjax(String bookImgCode) {
		bookService.deleteSubImg(bookImgCode);
	}
	
	
//	도서 관리) 도서 이미지, 소개 수정
	@ResponseBody
	@PostMapping("/updateBookDetail")
	public void updateBookDetail(ImgVO imgVO) {
		bookService.updateBookDetail(imgVO);
		
	}
	
	
	
	
	
////	도서 대여 개수
//	@ResponseBody
//	@PostMapping("/getBorrowCntAjax")
//	public Map<String, Object> getBorrowCntAjax(BorrowVO borrowVO, HttpSession session) {
//		
//	    borrowVO.setMemId(SecurityContextHolder.getContext().getAuthentication().getName());
//	    Map<String, Object> response = bookService.getBorrowAndStockCnt(borrowVO.getBookCode());
//	    
//	    return response;
//	}
//
//
//
//	
	
	

	
}
