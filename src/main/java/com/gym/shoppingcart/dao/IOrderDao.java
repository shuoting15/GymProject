package com.gym.shoppingcart.dao;

import java.util.List;

import com.gym.shoppingcart.model.OrderBean;

public interface IOrderDao {

	List<OrderBean> getAllOrders();

	List<OrderBean> getMemberOrders(String memberId);

	void insertOrder(OrderBean ob);
	
	public OrderBean getOrder(int orderNo);
	public void updateOrderStatus(int orderNo,String status);

}