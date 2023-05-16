package com.study.bookspace.goods;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.bookspace.goods.service.GoodsService;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Resource(name = "goodsService")
	private GoodsService goodsService;
	
	@GetMapping("/goodsList")
	public String goodsList(Model model) {
		
		model.addAttribute("goodsList", goodsService.selectGoods());
		System.out.println(goodsService.selectGoods());
		return "content/goods/goods_list";
	}

}
