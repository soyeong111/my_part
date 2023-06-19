package com.study.bookspace.adminOrder.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.adminOrder.vo.GoodsOrderVO;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
@Autowired
private SqlSessionTemplate sqlSession;

@Override
public void insertOrder(GoodsOrderVO GoodsOrderVO) {
	sqlSession.insert("orderMapper.insertOrder",GoodsOrderVO);
}

@Override
public List<GoodsOrderVO> selectOrder() {
	return sqlSession.selectList("orderMapper.selectOrder");
}

@Override
public int updateOrder(String orderCode) {
	return sqlSession.update("orderMapper.updateOrder",orderCode);
}
	
}
