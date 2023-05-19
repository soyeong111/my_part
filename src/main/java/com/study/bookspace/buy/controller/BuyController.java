package com.study.bookspace.buy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buy")
public class BuyController {
	
	
	
	@GetMapping("/buyForm")
	public String buyForm() {
		
		return "content/buy/buy_form";
	}
	
	

	@GetMapping("/buyDetail")
	public String buyDetail() {
		
		return "content/buy/buyDetail";
	}
	
	
	
}
