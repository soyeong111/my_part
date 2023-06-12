package com.study.bookspace.cart.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.cart.vo.GoodsCartVO;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@ResponseBody
	@PostMapping("/regCartAjax")
	public void regCartAjax(GoodsCartVO cartVO, Authentication authentication) {
		
		User user = (User)authentication.getPrincipal();
		cartVO.setMemId(user.getUsername());
		
	}
		
	

}
