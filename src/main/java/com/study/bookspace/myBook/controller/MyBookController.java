package com.study.bookspace.myBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.menu.vo.SubMenuVO;
import com.study.bookspace.myBook.service.MyBookService;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/mBook")
public class MyBookController {
	
	@Resource(name = "myBookService")
	private MyBookService myBookService;
	
	@GetMapping("/myReadingRecord")
	public String myReadingRecord(SubMenuVO subMenuVO) {
		return "content/my/my_reading_record";
	}

}
