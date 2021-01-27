package com.gym.mealSystem.order.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.mealSystem.order.dao.MealOrderDao;
import com.gym.mealSystem.order.model.MealOrderBean;
import com.gym.mealSystem.order.service.MealOrderService;

@Service
@Transactional
public class MealOrderServiceImpl implements MealOrderService {
	@Autowired
	private MealOrderDao orderDao;

	@Override
	public void deleteMealOrder(Integer orderNo) {
		orderDao.deleteMealOrder(orderNo);
	}

	@Override
	public void saveMealOrder(MealOrderBean ob) {
		orderDao.saveMealOrder(ob);
	}

	@Override
	public List<MealOrderBean> getAllOrders() {
		return orderDao.getAllOrders();
	}

	@Override
	public List<MealOrderBean> getMemberOrders(String member_Id) {
		return orderDao.getMemberOrders(member_Id);
	}

	@Override
	public MealOrderBean getOrder(Integer orderNo) {
		return orderDao.getOrder(orderNo);
	}

	@Override
	public void updateOrderStatus(Integer orderNo) {
		orderDao.updateOrderStatus(orderNo);
	}

}
