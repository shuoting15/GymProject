package com.gym.coach.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.gym.coach.service.CoachService;

@Controller
public class CoachController {
	@Autowired
	CoachService service;
	
	@Autowired
	ServletContext context;
	
	
	
	
	
	
}
