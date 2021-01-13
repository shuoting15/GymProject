package com.gym.coach.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.coach.dao.CoachOrderDao;
import com.gym.coach.model.ClanderBean;
import com.gym.coach.model.CoachBean;
import com.gym.coach.model.CoachOrderBean;

@Repository
public class CoachOrderDaoImpl implements CoachOrderDao {
	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
//	 hh:mm:ss
	public List<ClanderBean> findTimeByCoachId(int coachId) {
		List<ClanderBean> list = null;
//		String hql = "SELECT ob.orderTitle as title,"
//				+ "CONVERT(varchar(100), orderStartTime, 20) as start,"
//				+ "CONVERT(varchar(100), orderEndTime, 20) as end FROM CoachOrderBean ob WHERE coachId = 8 ";
		String hql = "SELECT ob.orderTitle as title," + " ob.orderStartTime as start," + " ob.orderEndTime as end,"
				+ " coachBean.coachId as groupId," + " ob.orderId as id,ob.orderColor as color FROM CoachOrderBean ob WHERE coachBean.coachId = :coachId";
		Session session = sessionFactory.getCurrentSession();
		list = session.createQuery(hql).setParameter("coachId", coachId)
				.setResultTransformer(Transformers.aliasToBean(ClanderBean.class)).list();
		return list;
	}

	@Override
	public void addCoachTime(CoachOrderBean coachOrderBean) {
		Session session = sessionFactory.getCurrentSession();
		int coachid = coachOrderBean.getCoachId();
		Date OrderStartTime = coachOrderBean.getOrderStartTime();
		if (checkCoachTime(coachid, OrderStartTime).isEmpty()) {
			
			session.save(coachOrderBean);
		} else {
			throw new RuntimeException("時間已被新增過了");
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CoachOrderBean> checkCoachTime(int coachid, Date OrderStartTime) {
		String hql = "FROM CoachOrderBean ob WHERE coachBean.coachId = :coachid and ob.orderStartTime = :OrderStartTime ";
		Session session = sessionFactory.getCurrentSession();

		return session.createQuery(hql).setParameter("coachid", coachid)
				.setParameter("OrderStartTime", OrderStartTime).getResultList();
	}

	@Override
	public CoachOrderBean getCoachTimeById(int orderId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(CoachOrderBean.class, orderId);
	}

	@Override
	public void orderCoachTime(CoachOrderBean coachOrderBean) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(coachOrderBean);
		
	}

	@Override
	public void deleteCoachTime(int orderId) {
        Session session = sessionFactory.getCurrentSession();
        CoachOrderBean bb = new CoachOrderBean();
        bb.setOrderId(orderId);
        session.delete(bb);
		
	}

}
