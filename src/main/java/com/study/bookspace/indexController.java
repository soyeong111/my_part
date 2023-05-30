package com.study.bookspace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.study.bookspace.admin.vo.SubMenuVO;
import com.study.bookspace.book.vo.SearchBookVO;

@Controller
public class indexController {
	
	@GetMapping("/")
	public String index(SubMenuVO subMenuVO, SearchBookVO searchBookVO) {
		return "content/main";
	}

}
