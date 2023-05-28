package com.study.bookspace.goods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.admin.vo.SubMenuVO;
import com.study.bookspace.goods.service.GoodsService;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Resource(name = "goodsService")
	private GoodsService goodsService;
	
	//굿즈 카테고리 관리 페이지
	@GetMapping("/goodsCateManage")
	public String goodsCateManage(SubMenuVO subMenuVO, Model model) {
		model.addAttribute("goodsCateList", goodsService.selectGoodsCateList());
		
		return "content/admin/goods_cate_manage";
	}
	
	//굿즈 카테고리 등록(관리자)
	@ResponseBody
	@PostMapping("/regGoodsCategoryAjax")
	public void regGoodsCategory(String goodsCateName) {
		
		goodsService.insertGoodsCategory(goodsCateName);
	}

}
