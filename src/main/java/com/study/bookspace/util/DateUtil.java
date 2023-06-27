package com.study.bookspace.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateUtil {
	
	//오늘 날짜를 문자열로 리턴
	public static String getNowDateToString() {
		Calendar cal = Calendar.getInstance();
		
		int year = cal.get(Calendar.YEAR); // 오늘 날짜의 년도 .year static으로 만들어진 상수.. 2023
		int month = cal.get(Calendar.MONTH) + 1; //월 3 으로 나옴 -1한값으로 나온다. 배열로 만들어져 0부터 시작.. + 1 해줘야한다.
		int date = cal.get(Calendar.DATE); //일 12 
		
		return year + "-" + String.format("%02d", month) + "-" + String.format("%02d", date); // 2023-06-10
	}
	
	//이번달의 첫 날짜
	public static String getFirstDateOfThisMonth() {
		//2023-06-12 -> 2023-06-01
		return getNowDateToString().substring(0, 8) + "01";
	}
	
	//이번달
	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH); 
	}
	
	// 올해 연도만 받기 2023
	public static int getNowYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}
	
	// 최근 5년 연도 리스트로 받기
	public static List<String> getFiveYears() {
		int nowYear = getNowYear();
		List<String> fiveYears = new ArrayList<>();
		fiveYears.add(nowYear + "");
		for (int i = 1; i < 5; i++) {
			fiveYears.add(nowYear - i + "");
		}
		return fiveYears;
	}
	
}
