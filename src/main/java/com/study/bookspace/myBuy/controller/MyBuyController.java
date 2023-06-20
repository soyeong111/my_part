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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.bookspace.adminOrder.service.OrderService;
import com.study.bookspace.adminOrder.vo.GoodsOrderVO;
import com.study.bookspace.myBuy.service.BuyService;
import com.study.bookspace.myBuy.vo.GoodsBuyDetailVO;
import com.study.bookspace.myBuy.vo.GoodsBuyVO;
import com.study.bookspace.myCart.service.CartService;
import com.study.bookspace.myCart.vo.GoodsCartVO;
import com.study.bookspace.util.DateUtil;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/mBuy")
public class MyBuyController {
	@Resource(name = "buyService")
	private BuyService buyService;
	
	@Resource(name = "cartService")
	private CartService cartService;
	
	@Resource(name = "orderService")
	private OrderService orderService;
	
	
	@PostMapping("/buyDetail")
	public String buyDetail(Model model, Authentication authentication, GoodsBuyVO goodsBuyVO,@RequestParam int finalPrice) {
		User user = (User)authentication.getPrincipal();

		String memId = user.getUsername();
		 goodsBuyVO.setMemId(memId);
		 goodsBuyVO.setBuyPrice(finalPrice);
		 
		 System.out.println(goodsBuyVO);
			
		buyService.buyFromCart(goodsBuyVO);
//		model.addAttribute("buyList", buyService.getBuyList(goodsBuyVO)) ;
		System.out.println(buyService.getBuyList(goodsBuyVO));
		return "content/buy/buy_detail";
	}
	
	
	
	 @ResponseBody
	  @PostMapping("/buysAjax") 
	 public void buysAjax(@RequestBody HashMap<String, Object> mapData, GoodsBuyVO goodsBuyVO, Authentication authentication, GoodsOrderVO goodsOrderVO ) {
	  
	 System.out.println(mapData);
	  
	  String nextBuyCode = buyService.getNextBuyCode();
	  
	 User user = (User)authentication.getPrincipal(); 
	 String memId = user.getUsername();
	  
	  int buyPrice = Integer.parseInt(mapData.get("final_price").toString());
	  
	  System.out.println(buyPrice);
	  
	  goodsBuyVO.setBuyCode(nextBuyCode); 
	  goodsBuyVO.setMemId(memId);
	  goodsBuyVO.setBuyPrice(buyPrice);
	  
	  System.out.println(goodsBuyVO);
	  
	  ObjectMapper mapper = new ObjectMapper(); 
	  GoodsBuyDetailVO[] buyDetailArr = mapper.convertValue(mapData.get("detail_info_arr"), GoodsBuyDetailVO[].class);
	  List<GoodsBuyDetailVO> buyDetailList = Arrays.asList(buyDetailArr);
	  
	  for ( GoodsBuyDetailVO e : buyDetailList) { 
		  e.setBuyCode(nextBuyCode); 
	  }
	  
	  goodsBuyVO.setBuyDetailList(buyDetailList);
	  
	  System.out.println(goodsBuyVO);
	 
	  buyService.buyFromCart(goodsBuyVO); 
	  
	  goodsOrderVO.setMemId(memId);
	  goodsOrderVO.setBuyPrice(buyPrice);
	  
	  orderService.insertOrder(goodsOrderVO);
	}
	

	

	@RequestMapping("/buyList")
	public String buyList(Authentication authentication, Model model, GoodsBuyVO goodsBuyVO) {
	
		String nowDate = DateUtil.getNowDateToString();
		
		String firstDate = DateUtil.getFirstDateOfThisMonth();
		
		User user = (User)authentication.getPrincipal();
		goodsBuyVO.setMemId(user.getUsername());
		
		List<GoodsBuyVO> buyList = buyService.getBuyList(goodsBuyVO);
		
		model.addAttribute("buyList", buyList);
		
		if(goodsBuyVO.getFromDate() == null) {
			goodsBuyVO.setFromDate(firstDate);
		}
		if(goodsBuyVO.getToDate() == null) {
			goodsBuyVO.setToDate(nowDate);
		}
		
		System.out.println("111"+buyList);
		
		return "content/buy/buy_list";
	}
	
	
	public String extractNumbers(String input) {
	    StringBuilder numbers = new StringBuilder();

	    for (int i = 0; i < input.length(); i++) {
	        char c = input.charAt(i);
	        if (Character.isDigit(c)) {
	            numbers.append(c);
	        }
	    }

	    return numbers.toString();
	}
	
	
	
	
	
}
