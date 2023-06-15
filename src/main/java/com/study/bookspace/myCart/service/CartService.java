package com.study.bookspace.myCart.service;

import java.util.List;

import com.study.bookspace.myCart.vo.GoodsCartVO;

public interface CartService {
	
	//장바구니에 굿즈 넣음
	void regGoodsCart(GoodsCartVO goodsCartVO);
	
	//장바구니 조회
	List<GoodsCartVO> cartList(String memId);
	
	//장바구니 굿즈 수량 변경
	void updateCartRegCnt(GoodsCartVO goodsCartVO);
	
	//굿즈 삭제
	void deleteCart(String cartCode);
	
	//선택 삭제
	void deleteCarts(GoodsCartVO goodsCartVO);

}
