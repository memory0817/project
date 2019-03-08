package com.lkh.myapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visit")
public class VisitorController {
	private static final Logger logger = LoggerFactory.getLogger(VisitorController.class);

	@RequestMapping("/visitboard")
	public String vositboard() {
		
		return "/visitboard/visitboard";
	}
	
	// 채용페이지
	   @RequestMapping("/empl")
	   public String adminMain(Model model) {

	      return "/main/empl";
	   }
}
