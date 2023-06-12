package com.study.bookspace;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.study.bookspace.book.vo.SearchBookVO;
import com.study.bookspace.menu.vo.SubMenuVO;

@Controller
public class indexController {
	
	@GetMapping("/")
	public String index(SubMenuVO subMenuVO, SearchBookVO searchBookVO) {
		return "content/main";
	}
	
	// 접근 제한 화면으로
	@GetMapping("/accessDeny")
	public String accessDeny() {
		return "content/access_deny";
	}

}
