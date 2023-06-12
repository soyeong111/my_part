package com.study.bookspace.goods.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public List<GoodsVO> selectGoodsListAdmin(GoodsVO goodsVO) {
		return sqlSession.selectList("goodsMapper.selectGoodsListAdmin", goodsVO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertGoods(GoodsVO goodsVO) {
		sqlSession.insert("goodsMapper.insertGoods", goodsVO);
		sqlSession.insert("goodsMapper.insertImgs", goodsVO);
	}
	
	@Override
	public String nextGoodsCode() {
		return sqlSession.selectOne("goodsMapper.nextGoodsCode");
	}

	@Override
	public List<GoodsCategoryVO> cateListInUse() {
		return sqlSession.selectList("goodsMapper.cateListInUse");
	}

	@Override
	public GoodsVO selectGoodsDetailAdmin(String goodsCode) {
		return sqlSession.selectOne("goodsMapper.selectGoodsDetailAdmin", goodsCode);
	}

	@Override
	public void updateGoods(GoodsVO goodsVO) {
		sqlSession.update("goodsMapper.updateGoods", goodsVO);
		
	}

	@Override
	public void deleteGoods(String goodsCode) {
		sqlSession.delete("goodsMapper.deleteGoods",goodsCode);
	}

	@Override
	public List<GoodsVO> goodsListForPublic(GoodsVO goodsVO) {
		return sqlSession.selectList("goodsMapper.goodsListForPublic",goodsVO);
	}

	@Override
	public GoodsVO goodsDetailForPublic(String goodsCode) {
		return sqlSession.selectOne("goodsMapper.goodsDetailForPublic",goodsCode);
	}



	
	

}
