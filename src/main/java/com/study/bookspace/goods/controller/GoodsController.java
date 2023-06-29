package com.study.bookspace.goods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.study.bookspace.goods.service.GoodsService;
import com.study.bookspace.goods.vo.GoodsVO;
import com.study.bookspace.menu.vo.SubMenuVO;
import com.study.bookspace.myBuy.service.BuyService;
import jakarta.annotation.Resource;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Resource(name = "goodsService")
	private GoodsService goodsService;
	
	@Resource(name = "buyService")
	private BuyService buyService;
	

	//퍼블릭
	@GetMapping("/goodsList")
	public String goodsListForPublic(GoodsVO goodsVO, Model model, SubMenuVO subMenuVO) {
		model.addAttribute("goodsList", goodsService.goodsListForPublic(goodsVO));
		return "content/goods/goods_list";
	}
	
	@GetMapping("/goodsDetail")
	public String goodsDetail(String goodsCode, Model model, SubMenuVO subMenuVO) {
		model.addAttribute("goods", goodsService.goodsDetailForPublic(goodsCode)) ;
		return "content/goods/goods_detail";
	}
	
	@GetMapping("/newGoods")
	public String newGoods(GoodsVO goodsVO, Model model, SubMenuVO subMenuVO) {
		model.addAttribute("newGoodsList",goodsService.selectNewGoods(goodsVO));
		return "content/goods/new_goods";
	}
	
	@GetMapping("/bestGoods")
	public String bestGoods(GoodsVO goodsVO, Model model, SubMenuVO subMenuVO) {
		model.addAttribute("bestGoodsList", goodsService.goodsListForBest(goodsVO));
		return "content/goods/best_goods";
	}
	
}
