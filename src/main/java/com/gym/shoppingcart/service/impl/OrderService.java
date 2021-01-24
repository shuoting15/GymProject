package com.gym.shoppingcart.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.shoppingcart.dao.impl.OrderDao;
import com.gym.shoppingcart.dao.impl.OrderItemDao;
import com.gym.shoppingcart.model.OrderBean;
import com.gym.shoppingcart.model.OrderItemBean;
import com.gym.shoppingcart.service.IOrderService;



@Service
public class OrderService implements IOrderService {

	@Autowired
	private OrderItemDao oidao;
	@Autowired
	private OrderDao odao;
//	@Autowired
//	private MemberDao mdao;

	public OrderDao getOdao() {
		return odao;
	}
	public void setOdao(OrderDao odao) {
		this.odao = odao;
	}
	public OrderService() {
	}

	@Override
	@Transactional
	public List<OrderBean> getAllOrders() {
		return odao.getAllOrders();
	}

	@Override
	@Transactional
	public List<OrderBean> getMemberOrders(String memberId) {
		return odao.getMemberOrders(memberId);
	}

	@Override
	@Transactional
	public void insertOrder(OrderBean ob) {
		odao.insertOrder(ob);
	}

	@Transactional
	public void persistOrder(OrderBean ob) {
		// 檢查並更新會員的未付款餘額
		//mdao.updateUnpaidOrderAmount(ob);
		
		checkStock(ob);		
		odao.insertOrder(ob);
	}

	public void checkStock(OrderBean ob) {
		Set<OrderItemBean> items = ob.getItems();
		for (OrderItemBean oib : items) {		
			oidao.updateProductStock(oib);
		}
	}
	
	@Override
	@Transactional
	public OrderBean getOrder(int orderNo) {
		return odao.getOrder(orderNo);
	}
	@Override @Transactional
	public void updateOrderStatus(int orderNo, String status) {
		odao.updateOrderStatus(orderNo, status);
		
	}
	@Override @Transactional
	public List<OrderBean> getOrderByNo(int orderNo) {
		return odao.getOrderByNo(orderNo);
	}

}
