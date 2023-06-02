package com.study.bookspace.goods.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.goods.vo.GoodsCategoryVO;
import com.study.bookspace.goods.vo.GoodsVO;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<GoodsCategoryVO> selectGoodsCateList() {
		return sqlSession.selectList("goodsMapper.selectGoodsCateList");
	}
	
	@Override
	public void insertGoodsCategory(String goodsCateName) {
		sqlSession.insert("goodsMapper.insertGoodsCategory", goodsCateName);
		
	}

	@Override
	public int checkCateName(String goodsCateName) {
		return sqlSession.selectOne("goodsMapper.checkGoodsCateName",goodsCateName);
	}

	@Override
	public void deleteGoodsCategory(String goodsCateCode) {
		sqlSession.delete("goodsMapper.deleteGoodsCategory", goodsCateCode);
		
	}

	@Override
	public int changeIsUse(String goodsCateCode) {
		return sqlSession.update("goodsMapper.updateIsUse",goodsCateCode);
	}


	
	

}
