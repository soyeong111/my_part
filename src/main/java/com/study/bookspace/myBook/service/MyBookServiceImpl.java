package com.study.bookspace.myBook.service;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.bookspace.myBook.vo.BookRecordVO;

@Service("myBookService")
public class MyBookServiceImpl implements MyBookService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;

	// 최근 3개월 대여 도서 제목 목록
	@Override
	public List<Map<String, String>> getBookTitleListThreeMonths(String memId) {
		return sqlSession.selectList("recordMapper.getBookTitleListThreeMonths", memId);
	}

	// 독서 기록 등록
	@Override
	public void regBookRecord(BookRecordVO bookRecordVO) {
		sqlSession.insert("recordMapper.regBookRecord", bookRecordVO);
	}

}
