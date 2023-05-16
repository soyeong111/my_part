package com.study.bookspace.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageVO {
	
	private int nowPageNum; // 현재 페이지 번호
	
	private int displayPageCnt; // 보여질 페이지 개수
	private int totalPageCnt; // 전체 페이지 개수
	
	private int startPageNum; // 보여질 첫번째 페이지 번호
	private int endPageNum; // 보여질 마지막 페이지 번호
	
	private int displayDataCnt; // 보여질 데이터 개수
	private int totalDataCnt; // 전체 데이터 개수
	
	private int startDataNum; // 보여질 첫번째 데이터 번호
	private int endDataNum; // 보여질 마지막 데이터 번호
	
	private boolean prevPageFlag; // 이전 페이지 유무
	private boolean nextPageFlag; // 다음 페이지 유무
	
	public PageVO() {
		
		nowPageNum = 1;
		displayPageCnt = 3;
		displayDataCnt = 5;
		
	}
	
	public void setPageInfo() {
		
		// 보여질 마지막 페이지 번호 = (올림) 보여질 페이지 개수 * (현재 페이지 번호 / 보여질 페이지 개수)
		endPageNum = displayPageCnt * (int)Math.ceil(nowPageNum / (double)displayPageCnt);
		
		// 보여질 첫번째 페이지 번호 = 보여질 마지막 페이지 번호 - 보여질 페이지 개수 + 1
		startPageNum = endPageNum - displayPageCnt + 1;
		
		// 전체 페이지 개수 = (올림) 전체 데이터 개수 / 보여질 데이터 개수
		totalPageCnt = (int)Math.ceil(totalDataCnt / (double)displayDataCnt);
		
		// 다음 페이지 유무 = 보여질 마지막 페이지 번호 < 전체 페이지 개수
		nextPageFlag = endPageNum < totalPageCnt;
		
		if (totalPageCnt == 0) {
			//  전체 페이지 개수 == 0 이면
			// 보여질 마지막 페이지 번호 = 1
			endPageNum = 1;
		} else if (endPageNum > totalPageCnt) {
			// 만약에 보여질 마지막 페이지 번호 > 전체 페이지 개수 이면
			// 보여질 마지막 페이지 번호 = 전체 페이지 개수
			endPageNum = totalPageCnt;
		}
		
		// 이전 페이지 유무 = 보여질 첫번째 페이지 번호 > 1
		prevPageFlag = startPageNum > 1;
		
		// 보여질 첫번째 데이터 번호 = (현재 페이지 번호 - 1) * 보여질 데이터 개수 + 1
		startDataNum = (nowPageNum - 1) * displayDataCnt + 1;
		
		// 보여질 마지막 데이터 번호 = 현재 페이지 번호 * 보여질 데이터 개수
		endDataNum = nowPageNum * displayDataCnt;
		
	}

}
