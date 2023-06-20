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
	
	//굿즈 리스트 조회(관리자)
	List<GoodsVO> selectGoodsListAdmin(GoodsVO goodsVO);
	
	//굿즈 등록
	void insertGoods(GoodsVO goodsVO);
	
	//다음 굿즈 코드 조회
	String nextGoodsCode();
	
	//사용중인 카테고리 목록 조회
	List<GoodsCategoryVO> cateListInUse();
	
	//굿즈 상세 조회(관리자)
	GoodsVO selectGoodsDetailAdmin(String goodsCode);
	
	//굿즈 정보 수정
	void updateGoods(GoodsVO goodsVO);
	
	//굿즈 삭제
	void deleteGoods(String goodsCode);
	
	//굿즈 리스트 조회 (퍼블릭)
	List<GoodsVO> goodsListForPublic(GoodsVO goodsVO);
	
	//굿즈 상세 조회(퍼블릭)
	GoodsVO goodsDetailForPublic(String goodsCode);
	
	//베스트 굿즈
	List<GoodsVO> goodsListForBest(GoodsVO goodsVO);
	
	

}
