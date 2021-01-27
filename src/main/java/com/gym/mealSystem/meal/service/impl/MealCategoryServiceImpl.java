package com.gym.mealSystem.meal.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mealSystem.meal.dao.MealCategoryDao;
import com.gym.mealSystem.meal.model.MealCategoryBean;
import com.gym.mealSystem.meal.service.MealCategoryService;



@Service
@Transactional
public class MealCategoryServiceImpl implements MealCategoryService {
	@Autowired
	MealCategoryDao mealCategoryDao;
	

	@Override
	public List<MealCategoryBean> getAllMealCategorys() {
		return mealCategoryDao.getAllMealCategorys();
	}


	@Override
	public MealCategoryBean getMealCategoryBean(Integer id) {
		return mealCategoryDao.getMealCategoryBean(id);
	}
	
	@Override
	public MealCategoryBean getMealCategoryBeanName(String name) {
		return mealCategoryDao.getMealCategoryBeanName(name);
	}

}
