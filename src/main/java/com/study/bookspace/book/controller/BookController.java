package com.study.bookspace.book.controller;

import java.io.File;
import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
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

import com.study.bookspace.alram.service.AlramService;
import com.study.bookspace.alram.vo.AlramVO;
import com.study.bookspace.book.service.BookService;
import com.study.bookspace.book.vo.BookVO;
import com.study.bookspace.book.vo.BorrowVO;
import com.study.bookspace.book.vo.CategoryVO;
import com.study.bookspace.book.vo.ImgVO;
import com.study.bookspace.book.vo.ReserveVO;
import com.study.bookspace.book.vo.SearchBookVO;
import com.study.bookspace.menu.vo.SubMenuVO;
import com.study.bookspace.util.ConstVariable;
import com.study.bookspace.util.UploadUtil;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/book")
public class BookController {
	@Resource(name = "bookService")
	private BookService bookService;
	
	@Resource(name = "alramService")
	private AlramService alramService;
	
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
	public String regBook(BookVO bookVO ,MultipartFile mainImg, MultipartFile subImg, MultipartFile sideImg) {
		
		//--- 파일 첨부 ---//
//		메인 이미지 업로드 (앞)
		ImgVO mainImgVO = UploadUtil.uploadFile(mainImg);
		
//		서브 이미지 업로드 (뒤)
//		ImgVO subImgVO = UploadUtil.uploadFile(subImg);
	    
// 		옆 이미지 업로드
	    ImgVO subImgVO = UploadUtil.uploadFile(sideImg);
	    
	    
//	    등록될 도서코드 조회
	    String bookCode = bookService.getNextBookCode();
	    bookVO.setBookCode(bookCode);
	    
//	    subImgVO.setIsMainImg("N");
	    subImgVO.setIsMainImg("N");
		
//		------도서 이미지 DB 등록-------
//		도서 이미지 등록 쿼리 실행 시 모든 빈 값을 채워줄 데이터를 가진 List
	    List<ImgVO> imgList = new ArrayList<>();
	    imgList.add(mainImgVO);
	    imgList.add(subImgVO);
//	    imgList.add(sideImgVO);
	    
//		BOOK_CODE 데이터 추가
		for(ImgVO img : imgList) {
			img.setBookCode(bookCode);
			
		}
	    
//		모든 이미지정보를 갖고 있는 imgList를 bookVO에 집어넣기
	    bookVO.setImgList(imgList);
	    
//	    도서 등록쿼리
	    bookService.regBook(bookVO);
		
		
	    return "redirect:/aBook/regBook";
		
	}
	
	
//	도서 등록 페이지
	@GetMapping("/regBook")
	public String regBook(Model model, BookVO bookVO) {
		
		model.addAttribute("categoryList", bookService.getCateListInUse());
		
		return "content/book/book_reg";
		
	}
	

//	도서 상세 페이지
	@GetMapping("/bookDetail")
	public String bookDetail(Model model, String bookCode, SubMenuVO subMenuVO) {
		 
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
	public int borrowAjax(BorrowVO borrowVO) {
		
		borrowVO.setMemId(SecurityContextHolder.getContext().getAuthentication().getName());
		
		
//		중복 대여
		int checkBorrowStatus = bookService.checkBorrowStatus(borrowVO);
//		중복 대여 시
		if (checkBorrowStatus != 0) {
			return 1;
		}
		
//		대여 개수 제한 (5권 이상 대여 금지)
		int getBorrowLimit = bookService.getBorrowLimit(borrowVO);
			if(getBorrowLimit == 4) {
				return 4;
			}
			
		
//		그냥 대여 or 예약한 사람이 대여인지 확인하는 변수
		int checkMem = 0;
		
//		예약 없이 대여 가능한 책의 개수 (모든사람)
		int ableBookCnt = bookService.getAbleBookCnt(borrowVO.getBookCode());
		
		if(ableBookCnt < 1) {
			
	//		현재 보유 개수 (예약한 사람이 대여할 수 있는 개수)
				int nowStockCnt = bookService.getNowStockCnt(borrowVO.getBookCode());
				System.out.println(nowStockCnt);
				if (nowStockCnt < 1) {
					return 300;
				}
				
				
				Map<String, Object> searchMap = new HashMap<>();
				searchMap.put("bookCode", borrowVO.getBookCode());
				searchMap.put("memId", borrowVO.getMemId());
				searchMap.put("nowStockCnt", nowStockCnt);
				
				
	//		대여 가능한 회원인지의 여부
				int ableBorrowMem = bookService.getAbleBorrowMem(searchMap);
				if(ableBorrowMem == 0) {
					return 100;
				}
				checkMem = 1 ;
		} 
			
		
//	도서 대여
	 bookService.borrowBook(borrowVO, checkMem);	


		 return 532;
		 
	}
	
	

//	도서 예약
	@ResponseBody
	@PostMapping("/reserveAjax")
	public int reserveAjax(HttpSession session, ReserveVO reserveVO, String bookCode, BorrowVO borrowVO) {
		reserveVO.setMemId(SecurityContextHolder.getContext().getAuthentication().getName());
		
		
//		예약 없이 대여 가능한 책의 개수 (모든사람)
		int ableBookCnt = bookService.getAbleBookCnt(borrowVO.getBookCode());
		if(ableBookCnt > 0) {
			return 100;
			
		}
		
		
//		예약하기 버튼 클릭 시, 대여한 회원인지 아닌지 확인 여부
		int checkBorrowCnt = bookService.getCheckBorrow(borrowVO);
		if(checkBorrowCnt != 0) {
			return 200;
		}
		
	
		System.out.println(borrowVO + "DDDDDDDDDDDDDDDDDDDDD");
		

		
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
	
	
// 	내 정보)) 도서 예약 관리
	@GetMapping("/myReserve")
	public String reservewManage(Model model, SubMenuVO subMenuVO, HttpSession session, ReserveVO reserveVO) {
		
		String memId = SecurityContextHolder.getContext().getAuthentication().getName();
		
	    reserveVO.setMemId(memId);
	    
	    model.addAttribute("myReserveList", bookService.myReserve(reserveVO));

	    
		return "content/my/my_reserve";
	}
	
	
//	내 정보) 도서 예약 취소
	@ResponseBody
	@PostMapping("/cancelReserveAjax")
		public void cancelReserveAjax(HttpSession session, ReserveVO reserveVO) {
		
		reserveVO.setMemId(SecurityContextHolder.getContext().getAuthentication().getName());
			
//			예약취소
			bookService.cancelReserve(reserveVO);
			
		}
	

	
//	도서 반납
	@ResponseBody
	@PostMapping("/returnBookAjax")
	public void returnBookAjax(BorrowVO borrowVO, HttpSession session, AlramVO alramVO) {
		borrowVO.setMemId(SecurityContextHolder.getContext().getAuthentication().getName());
	
//		도서 반납
		String alramId = bookService.returnBook(borrowVO);
		
		if(alramId != null) {
//		알람보내기
			alramVO.setMemId(alramId);
			alramVO.setAContent("예약하신 도서를 대여할 수 있습니다.");
			alramVO.setSection(1);
			
			alramService.insertAlram(alramVO);
		}
		
		
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
	public String bookManage(Model model, BookVO bookVO, SubMenuVO subMenuVO, String bookCode) {
		System.out.println(bookVO + "!!!!!!!!!!!!!!!!!!!!");
		
//		카테고리 목록 (전체)
		model.addAttribute("categoryList", bookService.getCateListForAdmin());
		
		
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
	public BookVO imgListAjax(String bookCode) {
		
			
//		이미지목록 (전체)
		return bookService.getImgListForBook(bookCode);
		
	}
	

//	도서 관리) 도서 삭제
	@ResponseBody
	@PostMapping("/deleteBookAjax")
	public String deleteBookAjax(String[] bookCodes ,BookVO bookVO) {
		
//		배열을 리스트로
		List<String> bookCodeList = Arrays.asList(bookCodes);
		
		bookVO.setBookCodeList(bookCodeList);
		// 도서 삭제
		bookService.deleteBook(bookVO);
		
		return "redirect:/book/bookManage";
		
	}
	

//	도서 관리) 도서 이미지 삭제
	@ResponseBody
	@PostMapping("/deleteImgAjax")
	public boolean deleteImgAjax(String bookImgCode, String attachedFileName) {
		System.out.println("삭제~~~~~~~~~~~~~~~~~~~~");
		boolean result = bookService.deleteImg(bookImgCode) == 1;
		
		if(result) {
			File file = new File(ConstVariable.BOOK_UPLOAD_PATH + attachedFileName);
		      file.delete();   
			
		}
		
		return result;
	}
	
	
//	도서 관리) 도서 이미지, 소개 수정
	@ResponseBody
	@PostMapping("/updateBookDetailAjax")
	public boolean updateBookDetailAjax(BookVO bookVO ,MultipartFile mainImg, MultipartFile subImg) {
		List<ImgVO> imgList = new ArrayList<>();

		//--- 파일 첨부 ---//
//		메인 이미지 업로드 (앞)
		if (mainImg != null) {
			ImgVO mainImgVO = UploadUtil.uploadFile(mainImg);
			if (mainImgVO != null) {
				imgList.add(mainImgVO);
			}
		}
		
//		서브 이미지 업로드 (뒤)
		  // 서브 이미지 업로드 (뒤)
	    if (subImg != null) {
	        ImgVO subImgVO = UploadUtil.uploadFile(subImg);
	        if(subImgVO != null) {
	        	subImgVO.setIsMainImg("N");
	        	imgList.add(subImgVO);
	        }
	    }
	    

	    System.out.println(444444);
	    
	    
		
//		------도서 이미지 DB 등록-------
//		도서 이미지 등록 쿼리 실행 시 모든 빈 값을 채워줄 데이터를 가진 List
	    
//		BOOK_CODE 데이터 추가
		for(ImgVO img : imgList) {
				img.setBookCode(bookVO.getBookCode());
			
		}
	    
//		모든 이미지정보를 갖고 있는 imgList를 bookVO에 집어넣기
	    bookVO.setImgList(imgList);
	    
	    System.out.println(bookVO);
	    
//		업데이트 코드
	    bookService.updateBookDetail(bookVO);
	    
	    
	    return true;
	    
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
