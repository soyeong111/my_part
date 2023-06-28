package com.study.bookspace.chart.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/chart")
public class ChartController {
	
	@GetMapping("/pythonChart")
	public String pythonChart() {
		return "content/chart/python_chart";
	}
	
	@ResponseBody
	@PostMapping("/getChartDataAjax")
	public Map<String, Object> getChartDataAjax() {
		System.out.println("!!!!!!!!!");
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:5000/getLibraryData";
		Map<String, Object> chartData = restTemplate.getForObject(url, Map.class);
		System.out.println(chartData);
		return chartData;
	}
	
}
