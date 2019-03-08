package com.lkh.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
@RequestMapping("/main")
public class Maincontroller {
	
	@RequestMapping("/business")
	public String business(Model model) {

		return "/main/Business";
	}
	

	@RequestMapping("/come")
	public String welcome(Model model) {

		return "/main/map";
	}

	@RequestMapping("/Q&A")
	public String QandA(Model model) {

		return "redirect:/gboard/list";
	}

	@RequestMapping("/recruit")
	public String adminMain(Model model) {

		return "/main/recruit";
	}
	
	@RequestMapping("/come2")
	public String welcome2(Model model) {

		return "/index2";
	}
	@RequestMapping("/popup")
	public String popup(Model model) {

		return "/main/popup";
	}

}
