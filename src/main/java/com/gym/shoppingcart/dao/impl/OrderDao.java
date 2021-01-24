package com.gym.shoppingcart.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.shoppingcart.dao.IOrderDao;
import com.gym.shoppingcart.model.OrderBean;



//1.新增一筆訂單到orders表格
//2.查詢orders表格內的單筆訂單
//3.查詢orders表格內的所有訂單
@Repository
public class OrderDao implements IOrderDao {
	private String memberId = null;
	@Autowired
	private SessionFactory factory;
	int orderNo = 0;

	public OrderDao() {}
	
	@SuppressWarnings("unchecked")
	@Override 
	public List<OrderBean> getAllOrders() {
		List<OrderBean> list = null;
		String hql = "FROM OrderBean ORDER BY orderNo DESC ";
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).getResultList();
		return list;
	}
	@SuppressWarnings("unchecked")
	@Override  
	public List<OrderBean> getMemberOrders(String memberId) {
		List<OrderBean> list = null;
        Session session = factory.getCurrentSession();
        String hql = "FROM OrderBean ob WHERE ob.memberId = :mid ORDER BY ob.orderNo DESC";
        list = session.createQuery(hql)
        			  .setParameter("mid", memberId)
        			  .getResultList();
        return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override  
	public List<OrderBean> getOrderByNo(int orderNo) {
		List<OrderBean> list = null;
        Session session = factory.getCurrentSession();
        String hql = "FROM OrderBean ob WHERE ob.orderNo = :no";
        list = session.createQuery(hql)
        			  .setParameter("no", orderNo)
        			  .getResultList();
        return list;
	}
	
	@Override
	public void insertOrder(OrderBean ob) {
		Session session = factory.getCurrentSession();
        session.save(ob);
	}
	@Override
	public OrderBean getOrder(int orderNo) {
		OrderBean ob = null;
        Session session = factory.getCurrentSession();
        ob = session.get(OrderBean.class, orderNo);
        return ob;
	}
	@Override
	public void updateOrderStatus(int orderNo,String status) {
		Session session = factory.getCurrentSession();
		OrderBean ob=getOrder(orderNo);
		ob.setOrderStatus(status);
		session.saveOrUpdate(ob);
	}
	
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


}
