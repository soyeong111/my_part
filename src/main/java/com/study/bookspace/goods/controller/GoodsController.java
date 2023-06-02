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

import com.study.bookspace.admin.vo.SubMenuVO;
import com.study.bookspace.book.vo.ImgVO;
import com.study.bookspace.goods.service.GoodsService;
import com.study.bookspace.goods.vo.GoodsCategoryVO;
import com.study.bookspace.goods.vo.GoodsImgVO;
import com.study.bookspace.goods.vo.GoodsVO;
import com.study.bookspace.util.UploadUtil;

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
	public void regGoodsCategoryAjax(String goodsCateName, SubMenuVO subMenuVO) {
		goodsService.insertGoodsCategory(goodsCateName);
	}
	
	//카테고리 등록 후 실행되는 카테고리 목록 조회
	@ResponseBody
	@PostMapping("/selectGoodsCateListAjax")
	public List<GoodsCategoryVO> selectGoodsCateListAjax(SubMenuVO subMenuVO){
		
		return goodsService.selectGoodsCateList();
	}

	
	
	//카테고리 삭제
	@GetMapping("/deleteGoodsCategory")
	public String deleteGoodsCategory(String goodsCateCode,SubMenuVO subMenuVO) {
		goodsService.deleteGoodsCategory(goodsCateCode);
			
		return "redirect:/goods/goodsCateManage";
	}
	
	//카테고리명 중복 확인
	@ResponseBody
	@PostMapping("/checkCateNameAjax")
	public int checkCateName(String goodsCateName,SubMenuVO subMenuVO) {
		
		return goodsService.checkCateName(goodsCateName);
	}

	//카테고리 사용여부 변경
	@ResponseBody
	@PostMapping("/changeIsUseAjax")
	public int changeIsUse(String goodsCateCode,SubMenuVO subMenuVO) {
		return goodsService.changeIsUse(goodsCateCode);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//굿즈 조회(굿즈 관리 페이지)
	@RequestMapping("/goodsManage")
	public String goodsManage(SubMenuVO subMenuVO, Model model, GoodsVO goodsVO) {
		
		model.addAttribute("goodsCateList", goodsService.selectGoodsCateList());
		model.addAttribute("goodsList", goodsService.selectGoodsListAdmin(goodsVO));
		
		return "content/admin/goods_manage";
	}
	
	//굿즈 등록 페이지
	@GetMapping("/regGoods")
	public String regGoods(Model model) {
		model.addAttribute("goodsCategoryList", goodsService.selectGoodsCateList());
		return "content/admin/reg_goods";
	}
	
	//굿즈 등록 프로세스
	@PostMapping("/regGoodsProcess")
	public String regGoodsProcess(GoodsVO goodsVO, MultipartFile mainImg, MultipartFile[] subImg) {
		
		GoodsImgVO attachedImgVO = UploadUtil.goodsUploadFile(mainImg);
		List<GoodsImgVO> attachedImgList = UploadUtil.goodsMultiFileUpload(subImg);
		
		//등록될 상품 코드 조회
		String goodsCode = goodsService.nextGoodsCode();
		goodsVO.setGoodsCode(goodsCode);
		
		List<GoodsImgVO> goodsImgList = attachedImgList;
		goodsImgList.add(attachedImgVO);
		
		for(GoodsImgVO img : goodsImgList) {
			img.setGoodsCode(goodsCode);
		}
		goodsVO.setGoodsImgList(goodsImgList);
		
		//굿즈 등록
		goodsService.insertGoods(goodsVO);
		
		return "redirect:/goods/regGoods";
	}
	
	//굿즈 상세 조회
	@ResponseBody
	@PostMapping("goodsDetailAjax")
	public Map<String, Object> goodsDetailAjax(String goodsCode){
		
		List<GoodsCategoryVO> cateList = goodsService.cateListInUse();
		
		GoodsVO goods = goodsService.selectGoodsDetailAdmin(goodsCode);
		
		Map<String, Object> mapData = new HashMap<>();
		mapData.put("goods", goods);
		mapData.put("cateList", cateList);
		
		return mapData;
	}
	
	
	//굿즈 정보 수정(굿즈 상세 조회 페이지)
	@PostMapping("/updateGoods")
	public String updateGoods(GoodsVO goodsVO) {
		goodsService.updateGoods(goodsVO);
		
		return "redirect:/goods/goodsManage";
	}
	

	//굿즈 삭제(굿즈 관리 페이지)
	@GetMapping("/deleteGoods")
	public String deleteGoods(String goodsCode) {
		System.out.println(111);
		goodsService.deleteGoods(goodsCode);
		
		return "redirect:/goods/goodsManage";
	}
	
	
	
	
	
}
