package com.study.bookspace.goods.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bookspace.admin.vo.SubMenuVO;
import com.study.bookspace.goods.service.GoodsService;
import com.study.bookspace.goods.vo.GoodsCategoryVO;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Resource(name = "goodsService")
	private GoodsService goodsService;
	
	//굿즈 카테고리 관리 페이지
	@GetMapping("/goodsCateManage")
	public String goodsCateManage(SubMenuVO subMenuVO, Model model) {
		model.addAttribute("cateList", goodsService.selectGoodsCateList());
		
		return "content/admin/goods_cate_manage";
	}
	
	//카테고리 등록
	@ResponseBody
	@PostMapping("/regGoodsCategoryAjax")
	public void regGoodsCategoryAjax(String goodsCateName) {
		goodsService.insertGoodsCategory(goodsCateName);
	}
	
	//카테고리 등록 후 실행되는 카테고리 목록 조회
	@ResponseBody
	@PostMapping("/selectGoodsCateListAjax")
	public List<GoodsCategoryVO> selectGoodsCateListAjax(){
		
		return goodsService.selectGoodsCateList();
	}

	
	
	//카테고리 삭제
	@GetMapping("/deleteGoodsCategory")
	public String deleteGoodsCategory(String goodsCateCode) {
		goodsService.deleteGoodsCategory(goodsCateCode);
			
		return "redirect:/goods/goodsCateManage";
	}
	
	//카테고리명 중복 확인
	@ResponseBody
	@PostMapping("/checkCateNameAjax")
	public int checkCateName(String goodsCateName) {
		
		return goodsService.checkCateName(goodsCateName);
	}

	//카테고리 사용여부 변경
	@ResponseBody
	@PostMapping("/changeIsUseAjax")
	public int changeIsUse(String goodsCateCode) {
		return goodsService.changeIsUse(goodsCateCode);
	}
	
	//굿즈 조회(굿즈 관리 페이지)
	@RequestMapping("/goodsManage")
	public String goodsManage() {
		
		return "content/goods/goods_manage";
	}
	
	//굿즈 등록
	
	//굿즈 상세 조회
	
	//굿즈 삭제(굿즈 관리 페이지)
	
	//굿즈 정보 수정(굿즈 상세 조회 페이지)
	
	
}
