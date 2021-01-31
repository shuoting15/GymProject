package com.gym.course.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.course.model.CourseBean;
import com.gym.course.model.CourseInfoBean;
import com.gym.course.service.CourseService;
import com.gym.member.model.MemberBean;

@Controller
public class CourseSearchController {

	@Autowired
	CourseService service;
	
	@RequestMapping("CourseSearchByKC")
	private @ResponseBody List<CourseBean> courseSearchByKC(@RequestParam("Keyword") String keyword,
			@RequestParam("Cate") String cate, @RequestParam("Cdate") String cdate){
		List<CourseBean> list = service.courseSearchByKC(keyword, cate, cdate);
		return list;
	}
	
	@RequestMapping("CourseSearchByKCAdmin")
	private @ResponseBody List<CourseBean> courseSearchByKCAdmin(@RequestParam("Keyword") String keyword,
			@RequestParam("Cate") String cate, @RequestParam("Cdate") String cdate){
		List<CourseBean> list = service.courseSearchByKCAdmin(keyword, cate, cdate);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("CourseSearchShowDate")
	private @ResponseBody List<CourseBean> CourseSearchShowDate(Model model) {
			// Map<Integer, String> map = new HashMap<>();
			List coursedate = new ArrayList<>();
			
			List datelist = service.getSelect();
			for( Object i : datelist) {
				coursedate.add(i);
			}
			return coursedate;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("CourseSearchShowDateAdmin")
	private @ResponseBody List<CourseBean> CourseSearchShowDateAdmin(Model model) {
			// Map<Integer, String> map = new HashMap<>();
			List coursedate = new ArrayList<>();
			
			List datelist = service.getSelectAdmin();
			for( Object i : datelist) {
				coursedate.add(i);
			}
			return coursedate;
	}
	
	@RequestMapping("showRoomCheck")
	private @ResponseBody List<String> showRoomCheck(@RequestParam("Date") String date,
			@RequestParam("St") String st, @RequestParam("Et") String et) {
		
		String newStartTime = date + " " + st;
		String newEndTime = date + " " + et;
		Timestamp chStart = Timestamp.valueOf(newStartTime);
		Timestamp chEnd = Timestamp.valueOf(newEndTime);
//		System.out.println(chStart);
//		System.out.println(chEnd);
		
		List<String> use = service.checkRoomTime(date, chStart, chEnd);
//			List datelist = service.getSelect();
//			for( Object i : datelist) {
//				coursedate.add(i);
//			}
		return use;
	}
	
	@RequestMapping("MyBookingCourseSearch")
	private @ResponseBody List<CourseInfoBean> myBookingCourseSearch(@RequestParam("ID") String id,Model model) {
//		MemberBean memberBean =   (MemberBean) model.getAttribute("LoginOK");
//		String userid = memberBean.getMember_id();
		List<CourseInfoBean> mycourses = service.getMyCourses(id);
		List<CourseInfoBean> list = new LinkedList<CourseInfoBean>();
		for(CourseInfoBean mycourse : mycourses) {
			if(mycourse.getStatus() == true) {
				list.add(mycourse);
			}
		}
		return list;
	}
}
