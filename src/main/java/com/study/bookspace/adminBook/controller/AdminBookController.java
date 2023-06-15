package com.study.bookspace.adminBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.book.service.BookService;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/aBook")
public class AdminBookController {
	@Resource(name="bookService")
	private BookService bookService;
	


	
}
