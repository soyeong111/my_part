package com.study.bookspace.goods.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.goods.vo.GoodsVO;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<GoodsVO> selectGoods() {
		return sqlSession.selectList("goodsMapper.selectGoods");
	}
	
	

}
