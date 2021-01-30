package com.gym.course.dao;

import java.util.List;

import com.gym.course.model.CourseCategoryBean;

public interface CoursesPerformanceDao {
	public int totalCategoryRevenue(String category);
	public int monthCategoryRevenue(String category);
	public int totalAllCategoriesRevenue();
	public int monthAllCategoriesRevenue();
	public List<CourseCategoryBean> getAllCategories();
}
