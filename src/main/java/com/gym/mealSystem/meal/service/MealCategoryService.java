package com.gym.mealSystem.meal.service;

import java.util.List;

import com.gym.mealSystem.meal.model.MealCategoryBean;



public interface MealCategoryService {
	List<MealCategoryBean> getAllMealCategorys();
	MealCategoryBean getMealCategoryBean(Integer id);
	MealCategoryBean getMealCategoryBeanName(String name);
}
