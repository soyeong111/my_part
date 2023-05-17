package com.study.bookspace.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.book.service.BookService;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/book")
public class BookController {
	@Resource(name = "bookService")
	private BookService bookService;
	
//	도서 목록 조회
	@GetMapping("/bookList")
	public String bookList(Model model) {
		
		
		model.addAttribute("bookList", bookService.getBookListForUser());
		
		return "content/book/book_list";
	}

	
	
}
