package com.study.bookspace.adminBuy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.adminBuy.service.aBuyService;

import jakarta.annotation.Resource;



@Controller
@RequestMapping("/aBuy")
public class aBuyController {
	@Resource(name="aBuyService")
	private aBuyService aBuyService;
	
	
	
	
	
	@GetMapping("/buySales")
	public String buySales (){
		
		return "content/admin/buy_sales";
	}
	
	@ResponseBody
	@PostMapping("/getSaleStatusByCategoryAjax")
	public List<Map<String, Object>> getSaleStatusByCategoryAjax() {
		List<Map<String, Object>> mapList = aBuyService.getSaleStatueByCategory();
		
		return mapList;
		
	
}
	
	
	
}
