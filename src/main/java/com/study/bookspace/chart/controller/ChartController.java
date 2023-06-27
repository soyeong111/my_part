package com.study.bookspace.chart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chart")
public class ChartController {
	
	@GetMapping("/pythonChart")
	public String pythonChart() {
		return "content/chart/python_chart";
	}
	
}
