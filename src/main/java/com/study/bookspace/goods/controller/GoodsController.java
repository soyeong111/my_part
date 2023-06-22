package com.study.bookspace.goods.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.study.bookspace.book.vo.ImgVO;
import com.study.bookspace.goods.service.GoodsService;
import com.study.bookspace.goods.vo.GoodsCategoryVO;
import com.study.bookspace.goods.vo.GoodsImgVO;
import com.study.bookspace.goods.vo.GoodsVO;
import com.study.bookspace.menu.vo.SubMenuVO;
import com.study.bookspace.myBuy.service.BuyService;
import com.study.bookspace.myBuy.vo.GoodsBuyVO;
import com.study.bookspace.util.UploadUtil;

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
		System.out.println(goodsService.goodsListForPublic(goodsVO));
		
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
