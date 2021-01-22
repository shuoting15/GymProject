package com.gym.shoppingcart.service;

import java.util.List;

import com.gym.shoppingcart.model.OrderBean;



public interface IOrderService {
	List<OrderBean> getAllOrders();

	List<OrderBean> getMemberOrders(String memberId);

	void insertOrder(OrderBean ob);
	
	void persistOrder(OrderBean ob);
	
	public OrderBean getOrder(int orderNo);
	public void updateOrderStatus(int orderNo,String status);

}
