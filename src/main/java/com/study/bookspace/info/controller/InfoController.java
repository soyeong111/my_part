package com.study.bookspace.info.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.admin.vo.SubMenuVO;
import com.study.bookspace.info.service.AnswerService;
import com.study.bookspace.info.service.QnaService;
import com.study.bookspace.info.vo.AnswerVO;
import com.study.bookspace.info.vo.QnaVO;
import com.study.bookspace.member.vo.MemberVO;
import com.study.bookspace.util.PageVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/info")
public class InfoController {
	@Resource(name = "qnaService")
	private QnaService qnaService;
	@Resource(name = "answerService")
	private AnswerService answerService;
	
	//도서관 소개글
	@GetMapping("/libraryIntro")
	public String libraryIntro(SubMenuVO subMenuVO) {
		
		return "content/info/library_intro";
	}
	

	//문의사항 리스트
	@GetMapping ("/qna")
	public String qnaList(Model model, PageVO pageVO,SubMenuVO subMenuVO) {
		//전체 게시글 수 조회
		//int totalDataCnt = qnaService.selectQnaCnt();
		
		//전체 데이터 수 세팅
		pageVO.setTotalDataCnt(qnaService.selectQnaCnt());
		
		//현재 페이지 설정
		pageVO.setNowPageNum(pageVO.getNowPageNum());
		
		//페이징 정보 세팅
		pageVO.setPageInfo();
		
		//게시글 목록 조회
		model.addAttribute("qnaList", qnaService.selectQna(pageVO));
		
		return "content/info/qna_list";
	}
	
	
	//문의사항 목록 페이지에서 질문하기 클릭시(로그인 성공했다는 가정) 가는 질문 작성 페이지
	@GetMapping("/questionForm")
	public String questionForm(SubMenuVO subMenuVO) {
		
	
		return "content/info/question_form";
	}
	
	//질문 등록 후 문의사항 리스트로 간다
	@PostMapping("/sendQuestion")
	public String sendQuestion(QnaVO qnaVO, AnswerVO answerVO,SubMenuVO subMenuVO) {
		qnaService.insertQna(qnaVO);
		
		return "redirect:/info/qna";
	}
	
	@GetMapping("/qnaDetail")
	public String qnaDetail(String qnaCode, Model model,SubMenuVO subMenuVO, AnswerVO answerVO) {
		qnaService.updateQnaViewCnt(qnaCode);
		model.addAttribute("qna",qnaService.qnaDetail(qnaCode));
		
		model.addAttribute("answer", answerService.selectAnswer(qnaCode));
		
		return "content/info/qna_detail";
	}
	
	@PostMapping("/updateQna")
	public String updateQna(QnaVO qnaVO, Authentication authentication) {
		  User user = (User)authentication.getPrincipal(); 
		  qnaVO.setMemId(user.getUsername());
		  
		  //관리자가 답글을 달면 글 수정 못하게
		  
		  qnaService.updateQna(qnaVO);
		  return "redirect:/info/qnaDetail?qnaCode=" + qnaVO.getQnaCode();
		
	}
	
	
	
  
  @GetMapping("/deleteQna")
  public String deleteQna(String qnaCode, QnaVO qnaVO, Authentication authentication) {
	  User user = (User)authentication.getPrincipal(); 
	  qnaVO.setMemId(user.getUsername());
  
	  answerService.deleteAnswer(qnaCode);
	  qnaService.deleteQna(qnaCode);
	  return "redirect:/info/qna";
  }

	
	
	  @PostMapping("/sendAnswer") 
	  public String qnaAnswerForm(AnswerVO answerVO, Authentication authentication,SubMenuVO subMenuVO, String qnaCode) { 
		  User user = (User)authentication.getPrincipal(); 
		  answerVO.setMemId(user.getUsername());
	  
		  answerService.insertAnswer(answerVO); 
		  qnaService.updateIsAdminAnswer(qnaCode);
		  
	  return "redirect:/info/qnaDetail?qnaCode=" + answerVO.getQnaCode();

	  }
	
	  @PostMapping("/updateAnswer")
		public String updateAnswer(AnswerVO answerVO) {
		  System.out.println(111);
			answerService.updateAnswer(answerVO);
		return "redirect:/info/qnaDetail?qnaCode=" + answerVO.getQnaCode();
		}
	  
	  @GetMapping("/deleteAnswer")
	  public String deleteAnswer(String answerCode, AnswerVO answerVO) {
		  answerService.deleteAnswer(answerCode);
		  return "redirect:/info/qnaDetail?qnaCode=" + answerVO.getQnaCode();
	  }
	
	
	
	@GetMapping("/wayToLibrary")
	public String wayToLibrary(SubMenuVO subMenuVO) {
		
		return "content/info/way_to_library";
	}
	@GetMapping("/notice")
	public String notice(SubMenuVO subMenuVO) {
		
		return "content/info/notice";
	}
	
	
	@PostMapping("/qnaAnswer")
	public String qnaAnswer(AnswerVO answerVO) {
		qnaService.insertQnaAnswer(answerVO);
		
		return "redirect:/info/qnaDetail";
	}
	
	

}
