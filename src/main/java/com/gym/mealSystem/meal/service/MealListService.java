package com.gym.mealSystem.meal.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.gym.mealSystem.meal.model.MealListBean;
import com.gym.mealSystem.meal.model.MealCategoryBean;

@Repository
public interface MealListService {
	// 計算餐點總共有幾頁
	int getTotalPages();

	// 搜尋當頁Meallist(取6筆)
	Map<Integer, MealListBean> getPageMealLists(int pageNo);

	// 搜尋MealListBean有幾筆資料
	long getRecordCounts();

	// 搜尋MealList不重複menuGroupId
	List<String> getCategory();

	// 更新MealList
	void updateMealList(MealListBean ml);

	// 刪除MealList
	void deleteMealList(Integer id);

	// 新增MealList
	void saveMealList(MealListBean ml);

	// 查詢所有MealList
	List<MealListBean> getAllMealList();

	// 從MealList搜尋CategoryId
	List<MealListBean> getCategoryNameByCategoryId(MealCategoryBean id);

	MealListBean getMealList(Integer id);
}
