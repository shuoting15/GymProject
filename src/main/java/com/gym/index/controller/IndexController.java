package com.gym.index.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.gym.coach.model.CoachBean;
import com.gym.coach.service.CoachService;
import com.gym.course.model.CourseBean;
import com.gym.course.service.CourseService;
import com.gym.mealSystem.meal.model.MealListBean;
import com.gym.mealSystem.meal.service.MealListService;
import com.gym.news.model.NewsBean;
import com.gym.news.service.NewsService;

@Controller
public class IndexController {
@Autowired
CoachService coachservice;

@Autowired
CourseService courseService;

@Autowired
NewsService newsservice;

@Autowired
MealListService mealListService;

	@GetMapping("/")
	public String home(Model model) {
		List<CoachBean> coachList = coachservice.getAllCoachs();
		model.addAttribute("coachList", coachList);
		
		
		List<CourseBean> courseList = courseService.getAllCourses();
		model.addAttribute("courseList", courseList);
		
		List<NewsBean> list = newsservice.getAllNews();
		model.addAttribute("news", list);
		
		List<MealListBean> mealList = mealListService.getAllMealList();
		model.addAttribute("mealLists", mealList);
		
		return "index";
	}

	@GetMapping("/template01")
	public String homeWithName() {
		return "template01";
	}

	@GetMapping("/memberarea")
	public String memberarea() {
		return "member/memberarea";
	}
	
		@GetMapping("/mealSystem")
	public String mealSystem() {
//		return "mealSystem/index";
		return "redirect:/showAllMealList";
	}
}
