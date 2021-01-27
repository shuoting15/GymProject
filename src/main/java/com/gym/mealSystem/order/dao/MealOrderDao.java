package com.gym.mealSystem.order.dao;

import java.util.List;

import com.gym.mealSystem.order.model.MealOrderBean;

public interface MealOrderDao {
	void deleteMealOrder(Integer orderNo);
	void saveMealOrder(MealOrderBean ob);
	List<MealOrderBean> getAllOrders();
	List<MealOrderBean> getMemberOrders(String member_Id);
	MealOrderBean getOrder(Integer orderNo);
	void updateOrderStatus(Integer orderNo);
}