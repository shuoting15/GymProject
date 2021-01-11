package com.gym.coach.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gym.coach.service.CoachOrderService;

@Controller
public class CoachOderController  {
	@Autowired
	CoachOrderService service;
	
	
	@GetMapping(value="/findall",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String findTimeByCoachId(){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		return gson.toJson(service.findTimeByCoachId());
		
	}
	
	
}
