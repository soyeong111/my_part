package com.study.bookspace.adminBuy.service;

import java.util.List;
import java.util.Map;

public interface aBuyService {
	
	//카테고리별 판매 추이
	List<Map<String, Object>> getSaleStatueByCategory();
}
