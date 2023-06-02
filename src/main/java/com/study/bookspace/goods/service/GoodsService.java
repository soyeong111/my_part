package com.study.bookspace.goods.service;

import java.util.List;

import com.study.bookspace.goods.vo.GoodsCategoryVO;
import com.study.bookspace.goods.vo.GoodsVO;

public interface GoodsService {
	
	//굿즈 카테고리 목록 조회
	List<GoodsCategoryVO> selectGoodsCateList();
	
	//굿즈 카테고리 등록
	void insertGoodsCategory(String goodsCateName);
	
	//카테고리명 중복 체크
	int checkCateName(String goodsCateName);
	
	//카테고리 삭제
	void deleteGoodsCategory(String goodsCateCode);
	
	//카테고리 사용여부 변경
	int changeIsUse(String goodsCateCode);

}
