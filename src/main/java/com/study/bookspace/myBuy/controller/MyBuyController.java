package com.study.bookspace.myBuy.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.bookspace.myBuy.service.BuyService;
import com.study.bookspace.myBuy.vo.GoodsBuyDetailVO;
import com.study.bookspace.myBuy.vo.GoodsBuyVO;
import com.study.bookspace.util.DateUtil;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/mBuy")
public class MyBuyController {
	@Resource(name = "buyService")
	private BuyService buyService;
	
	
	@ResponseBody
	@PostMapping("/buysAjax")
	public void buyForm(@RequestBody HashMap<String, Object> mapData, GoodsBuyVO goodsBuyVO, Authentication authentication ) {
		System.out.println(mapData);
		
		String nextBuyCode = buyService.getNextBuyCode();
//		
		User user = (User)authentication.getPrincipal();
		String memId = user.getUsername();
		
		int buyPrice = Integer.parseInt(mapData.get("buyPrice").toString());
		
		goodsBuyVO.setBuyCode(nextBuyCode);
		goodsBuyVO.setMemId(memId);
		goodsBuyVO.setBuyPrice(buyPrice);
		
		ObjectMapper mapper = new ObjectMapper();
		GoodsBuyDetailVO[] buyDetailArr = mapper.convertValue(mapData.get("buyDetailList"), GoodsBuyDetailVO[].class);
		List<GoodsBuyDetailVO> buyDetailList = Arrays.asList(buyDetailArr);
		
		for ( GoodsBuyDetailVO e : buyDetailList) {
			e.setBuyCode(nextBuyCode);
		}
		
		goodsBuyVO.setBuyDetailList(buyDetailList);
		
		buyService.buyFromCart(goodsBuyVO);
		
	}
	
	

	@RequestMapping("/buyList")
	public String buyList(Authentication authentication, Model model, GoodsBuyVO goodsBuyVO) {
		
		String nowDate = DateUtil.getNowDateToString();
		
		String firstDate = DateUtil.getFirstDateOfThisMonth();
		
		User user = (User)authentication.getPrincipal();
		goodsBuyVO.setMemId(user.getUsername());
		
		List<GoodsBuyVO> buyList = buyService.getBuyList(goodsBuyVO);
		
		model.addAttribute("buyList", buyList);
		
		System.out.println("buyList~!~!!~!~!"+ buyList);
		
		if(goodsBuyVO.getFromDate() == null) {
			goodsBuyVO.setFromDate(firstDate);
		}
		if(goodsBuyVO.getToDate() == null) {
			goodsBuyVO.setToDate(nowDate);
		}
		
		
		
		return "content/buy/buy_list";
	}
	
	
	
	
	
	
	
	
}
