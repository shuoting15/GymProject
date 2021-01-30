package com.gym.mealSystem.meal.dao;

import java.util.List;

import com.gym.mealSystem.meal.model.MealCategoryBean;


public interface MealCategoryDao {
	List<MealCategoryBean> getAllMealCategorys();
	MealCategoryBean getMealCategoryBean(Integer id);
	MealCategoryBean getMealCategoryBeanName(String name);
}
