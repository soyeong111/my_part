package com.study.bookspace.goods.service;

import java.util.List;

import com.study.bookspace.goods.vo.GoodsCategoryVO;
import com.study.bookspace.goods.vo.GoodsVO;

public interface GoodsService {
	
	//굿즈 카테고리 목록 조회
	List<GoodsCategoryVO> selectGoodsCateList();
	
	//굿즈 카테고리 등록
	void insertGoodsCategory(String goodsCateName);

}
