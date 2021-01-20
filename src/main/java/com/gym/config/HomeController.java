package com.gym.config;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "template01";
	}

	@GetMapping("/template01")
	public String homeWithName() {
		return "template01";
	}

	@GetMapping("/memberarea")
	public String memberarea() {
		return "member/memberarea";
	}
}
