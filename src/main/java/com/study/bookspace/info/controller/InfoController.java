package com.study.bookspace.info.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/info")
public class InfoController {
	
	@GetMapping("/libraryIntro")
	public String libraryIntro() {
		return "content/info/library_intro";
	}
	
	@GetMapping("/qna")
	public String qna() {
		return"content/info/q_n_a";
	}

}
