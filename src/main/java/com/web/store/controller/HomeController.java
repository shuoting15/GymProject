package com.web.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/welcome")
	public String welcome(Model model)
	{
		model.addAttribute("title","歡迎光臨軍亞網路商城");
		model.addAttribute("subtitle","網路上獨一無二的購物天堂");
		return "welcome";
	}
	
	@GetMapping("/")
	public String home() {
		return "index";	
	}
}
