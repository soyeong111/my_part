package com.study.bookspace.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.info.service.QnaService;

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
	
	//문의사항(가장 최근 질문 나옴)
	@GetMapping("/qna")
	public String qna() {
		
	
		return"content/info/q_n_a";
	}
	
	//문의사항 목록 페이지에서 질문하기 클릭시(로그인 성공했다는 가정) 가는 질문 작성 페이지
	@GetMapping("/questionForm")
	public String questionForm() {
		
		return "content/info/question_form";
	}
	
	//질문 등록 후 문의사항 목록으로 간다
	@PostMapping("/sendQuestion")
	public String sendQuestion() {
		
		qnaService.insertQna();
		
		return "redirect:/info/qna";
	}

	
}
