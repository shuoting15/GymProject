package com.gym.course.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gym.course.model.CourseBean;
import com.gym.course.model.CourseCategoryBean;
import com.gym.course.service.CoursesPerFormanceService;

@Controller
@SessionAttributes({"LoginOK"}) 
public class CoursesPerformanceController {
	@Autowired
	CoursesPerFormanceService cpservice;

	@Autowired
	ServletContext context;
	
	// 課程業績
	@GetMapping("/coursesPerformance")
	public String coursesPerformance(Model model) {
		List<CourseCategoryBean> list = cpservice.getAllCategories();
		int totalrevenue;
		int monthrevenue;
		int alltotalrevenue = cpservice.totalAllCategoriesRevenue();
		int allmonthrevenue = cpservice.monthAllCategoriesRevenue();
		for(CourseCategoryBean category : list) {
			//業績
			totalrevenue = cpservice.totalCategoryRevenue(category.getCategoryName());
			monthrevenue = cpservice.monthCategoryRevenue(category.getCategoryName());
			
			category.setTotalrevenue(totalrevenue);
			
			if(alltotalrevenue == 0) {
				category.setTotalrevenuePercent(0);
			}else{
				category.setTotalrevenuePercent(totalrevenue*100/alltotalrevenue);
			}
			category.setMonthrevenue(monthrevenue);
			if(allmonthrevenue == 0) {
				category.setMonthrevenuePercent(0);
			}else{
				category.setMonthrevenuePercent(monthrevenue*100/allmonthrevenue);
			}
			
			//開課數量
//			List<CourseBean> clist = cpservice.getCoursesCountsByCategory(category.getCategoryName());
		}
		
		
		model.addAttribute("category", list);
		model.addAttribute("allmonthrevenue", allmonthrevenue);
		model.addAttribute("alltotalrevenue", alltotalrevenue);
		return "course/coursesPerformance";
	}
	
}
