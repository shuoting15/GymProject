package com.gym.course.controller;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
		List<CourseBean> c1 = new LinkedList<CourseBean>();
		List<CourseBean> c2 = new LinkedList<CourseBean>();
		List<CourseBean> c3 = new LinkedList<CourseBean>();
		List<CourseBean> c4 = new LinkedList<CourseBean>();
		List<CourseBean> c5 = new LinkedList<CourseBean>();
		List<CourseBean> alist = null;
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
			alist = cpservice.getCoursesCountsByCategory(category.getCategoryName());
			List<CourseBean> blist = cpservice.getMonthCoursesCountsByCategory(category.getCategoryName());
//			System.out.println(alist.size());
			category.setTotalCoursesCounts(alist.size());
			category.setMonthCoursesCounts(blist.size());
		}
		
		
		model.addAttribute("category", list);
		model.addAttribute("allmonthrevenue", allmonthrevenue);
		model.addAttribute("alltotalrevenue", alltotalrevenue);
		return "course/coursesPerformance";
	}
	
	@RequestMapping("/coursesPerformanceapi")
	public String coursesPerformanceapi(@RequestParam("category") String category, Model model) throws UnsupportedEncodingException {
		category = java.net.URLDecoder.decode(category,"UTF-8");
		List<CourseBean> alist = cpservice.getCoursesCountsByCategory(category);
		model.addAttribute("alist", alist);
		return "course/courseslist";
	}
	
}
