package com.study.bookspace.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/buy")
public class CartController {
	
	@GetMapping("/cartForm")
	public String cartForm() {
		
		return "content/buy/buy_form";
	}

}
