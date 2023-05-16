package com.study.bookspace.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {
	
	@GetMapping("/bookList")
	public String bookList() {
		return "content/book/book_list";
	}

}
