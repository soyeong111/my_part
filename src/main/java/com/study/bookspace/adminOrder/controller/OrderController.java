package com.study.bookspace.adminOrder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.adminOrder.service.OrderService;
import com.study.bookspace.adminOrder.vo.GoodsOrderVO;
import com.study.bookspace.myBuy.vo.GoodsBuyVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/aOrder")
public class OrderController {
	@Resource(name = "orderService")
	private OrderService orderService;

	//배송 정보 관리 
	@GetMapping("/goodsOrderManage")
	public String goodsOrderManage( Model model) {
		model.addAttribute("orderList", orderService.selectOrder());
		return"content/admin/goods_order_manage";
	}
	
	@GetMapping("/updateOrder")
	public String  updateOrder(String orderCode) {
		orderService.updateOrder(orderCode);
		
		return "redirect:/aOrder/goodsOrderManage";
	}
	
	

}
