package com.study.bookspace.info.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.info.service.QnaService;
import com.study.bookspace.info.vo.QnaVO;
import com.study.bookspace.member.vo.MemberVO;
import com.study.bookspace.util.PageVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/info")
public class InfoController {
	@Resource(name = "qnaService")
	private QnaService qnaService;
	
	//도서관 소개글
	@GetMapping("/libraryIntro")
	public String libraryIntro() {
		
		return "content/info/library_intro";
	}
	

	//문의사항 리스트
	@GetMapping ("/qna")
	public String qnaList(Model model, PageVO pageVO) {
		//전체 게시글 수 조회
		int totalDataCnt = qnaService.selectQnaCnt();
		
		//전체 데이터 수 세팅
		pageVO.setTotalDataCnt(totalDataCnt);
		
		//페이징 정보 세팅
		pageVO.setPageInfo();
		
		//게시글 목록 조회
		model.addAttribute("qnaList", qnaService.selectQna(pageVO));
		
		return "content/info/qna_list";
	}
	
	
	//문의사항 목록 페이지에서 질문하기 클릭시(로그인 성공했다는 가정) 가는 질문 작성 페이지
	@GetMapping("/questionForm")
	public String questionForm() {
		
	
		return "content/info/question_form";
	}
	
	//질문 등록 후 문의사항 리스트로 간다
	@PostMapping("/sendQuestion")
	public String sendQuestion(QnaVO qnaVO) {
		
		qnaService.insertQna(qnaVO);
		
		return "redirect:/info/qna";
	}
	
	


}
