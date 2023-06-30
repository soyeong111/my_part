package com.study.bookspace.myBuy.service;

import java.util.List;

import com.study.bookspace.myBuy.vo.GoodsBuyVO;
import com.study.bookspace.myCart.vo.GoodsCartVO;

public interface BuyService {
	
	//굿즈 구매
	void buyFromCart(GoodsBuyVO goodsBuyVO, GoodsCartVO goodsCartVO);
	
	//다음 buyCode 조회
	String getNextBuyCode();
	
	//구매 목록 조회
	List<GoodsBuyVO> getBuyList(GoodsBuyVO goodsBuyVO);
	
	//order no 업데이트
	int updateBuyOrder(String buyCode);
	

}
