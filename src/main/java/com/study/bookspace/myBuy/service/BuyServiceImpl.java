package com.study.bookspace.myBuy.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.bookspace.myBuy.vo.GoodsBuyVO;

@Service("buyService")
public class BuyServiceImpl implements BuyService {
	@Autowired
	 private SqlSessionTemplate sqlSession;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void buyFromCart(GoodsBuyVO goodsBuyVO) {
		sqlSession.insert("buyMapper.buy", goodsBuyVO);
		sqlSession.insert("buyMapper.buyDetails", goodsBuyVO);
		sqlSession.insert("buyMapper.insertOrderStatus", goodsBuyVO);
	}

	@Override
	public String getNextBuyCode() {
		return sqlSession.selectOne("buyMapper.getNextBuyCode");
	}

	@Override
	public List<GoodsBuyVO> getBuyList(GoodsBuyVO goodsBuyVO) {
		return sqlSession.selectList("buyMapper.getBuyList", goodsBuyVO);
	}
	
}
