package com.study.bookspace.adminBuy.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("aBuyService")
public class aBuyServiceImpl implements aBuyService{
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<Map<String, Object>> getSaleStatueByCategory() {
		return sqlSession.selectList("buyMapper.getSaleStatueByCategory");
	}

}
