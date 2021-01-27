package com.gym.mealSystem.meal.service.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mealSystem.meal.dao.MealListDao;
import com.gym.mealSystem.meal.model.MealListBean;
import com.gym.mealSystem.meal.model.MealCategoryBean;
import com.gym.mealSystem.meal.service.MealListService;



@Service
@Transactional
public class MealListServiceImpl implements MealListService{
	@Autowired
	MealListDao mealListDao;

	@Override
	public int getTotalPages() {
		return mealListDao.getTotalPages();
	}

	@Override
	public Map<Integer, MealListBean> getPageMealLists(int pageNo) {
		return mealListDao.getPageMealLists(pageNo);
	}

	@Override
	public long getRecordCounts() {
		return mealListDao.getRecordCounts();
	}

	@Override
	public List<String> getCategory() {
		return mealListDao.getCategory();
	}

	@Override
	public void updateMealList(MealListBean ml) {
		mealListDao.updateMealList(ml);
	}

	@Override
	public void deleteMealList(Integer id) {
		mealListDao.deleteMealList(id);
	}

	@Override
	public void saveMealList(MealListBean ml) {
		mealListDao.saveMealList(ml);
	}

	@Override
	public List<MealListBean> getAllMealList() {
		return mealListDao.getAllMealList();
	}

	@Override
	public List<MealListBean> getCategoryNameByCategoryId(MealCategoryBean id) {
		return mealListDao.getCategoryNameByCategoryId(id);
	}

	@Override
	public MealListBean getMealList(Integer id) {
		return mealListDao.getMealList(id);
	}
	
	
	
	
}
