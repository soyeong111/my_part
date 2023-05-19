package com.study.bookspace.buy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuyController {
	
	
	
	@GetMapping
	public String buyForm() {
		
		return "content/buy/buy_form";
	}
	
	

	@GetMapping
	public String buyDetail() {
		
		return "content/buy/buyDetail";
	}
	
	
	
}
