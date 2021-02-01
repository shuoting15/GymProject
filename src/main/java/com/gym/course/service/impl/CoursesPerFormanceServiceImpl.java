package com.gym.course.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.course.dao.CoursesPerformanceDao;
import com.gym.course.model.CourseBean;
import com.gym.course.service.CoursesPerFormanceService;
@Service
@Transactional
public class CoursesPerFormanceServiceImpl implements CoursesPerFormanceService {
	@Autowired
	CoursesPerformanceDao dao;

	@Override
	public List getAllCategories() {
		return dao.getAllCategories();
	}

	@Override
	public int totalCategoryRevenue(String category) {
		return dao.totalCategoryRevenue(category);
	}

	@Override
	public int monthCategoryRevenue(String category) {
		return dao.monthCategoryRevenue(category);
	}

	@Override
	public int totalAllCategoriesRevenue() {
		return dao.totalAllCategoriesRevenue();
	}

	@Override
	public int monthAllCategoriesRevenue() {
		return dao.monthAllCategoriesRevenue();
	}

	@Override
	public List<CourseBean> getCoursesCountsByCategory(String category) {
		return dao.getCoursesCountsByCategory(category);
	}

	@Override
	public List<CourseBean> getMonthCoursesCountsByCategory(String category) {
		return dao.getMonthCoursesCountsByCategory(category);
	}

}
