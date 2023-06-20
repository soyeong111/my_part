package com.study.bookspace.adminOrder.service;

import java.util.List;

import com.study.bookspace.adminOrder.vo.GoodsOrderVO;

public interface OrderService {
	
	//주문 정보 등록
	void insertOrder(GoodsOrderVO GoodsOrderVO);
	
	//주문 리스트 조회
	List<GoodsOrderVO> selectOrder();
	
	//주문 상태 변경
	int updateOrder(String orderCode);
	

}
