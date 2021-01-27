package com.gym.mealSystem.order.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.coach.model.CoachOrderBean;
import com.gym.mealSystem.order.dao.MealOrderDao;
import com.gym.mealSystem.order.model.MealOrderBean;

@Repository
public class MealOrderDaoImpl implements MealOrderDao {

	@Autowired
	SessionFactory factory;

	public Session getSession() {
		return factory.getCurrentSession();
	}

	// 刪除MealList
	@Override
	public void deleteMealOrder(Integer orderNo) {
		Session session = getSession();
		MealOrderBean ml = getOrder(orderNo);
		session.delete(ml);
	}

	// 新增Order
	@Override
	public void saveMealOrder(MealOrderBean ob) {
		Session session = factory.getCurrentSession();
		session.save(ob);
	}

	// 查詢所有Order
	@Override
	@SuppressWarnings("unchecked")
	public List<MealOrderBean> getAllOrders() {
		String hql = "FROM MealOrderBean";
		Session session = factory.getCurrentSession();
		List<MealOrderBean> list = session.createQuery(hql).getResultList();
		return list;
	}

	//更新訂單狀態
	@Override
	public void updateOrderStatus(Integer orderNo) {
		String hql = "UPDATE MealOrderBean b SET b.orderStatus = 1 WHERE b.orderNo = :orderNo";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("orderNo", orderNo).executeUpdate();
		System.out.println("ok");
	}

	// 從Orders搜尋member_Id
	@Override
	@SuppressWarnings("unchecked")
	public List<MealOrderBean> getMemberOrders(String member_Id) {
		String hql = "FROM MealOrderBean WHERE memberId = :mid ORDER BY orderNo DESC";
		List<MealOrderBean> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("mid", member_Id).getResultList();
		return list;
	}

	public MealOrderBean getOrder(Integer orderNo) {
		return factory.getCurrentSession().get(MealOrderBean.class, orderNo);

	}
}