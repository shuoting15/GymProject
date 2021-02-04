package com.gym.coach.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.coach.dao.CoachPerformanceDao;
@Repository
public class CoachPerformanceDaoImpl implements CoachPerformanceDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public int totalCoachRevenue(int coachId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT sum(cb.orderPrice)  FROM CoachOrderBean cb WHERE coachBean.coachId = :coachId ";
		
		
		Object result = session.createQuery(hql).setParameter("coachId", coachId).getSingleResult();
		if (result!=null) {
			return (int) (long) result;
			
		}
		return 0;
	}

	@Override
	public int monthCoachRevenue(int coachId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT sum(cb.orderPrice)  FROM CoachOrderBean cb WHERE coachBean.coachId = :coachId and datediff(mm,orderDate,getdate())=0  ";
		
		
		Object result = session.createQuery(hql).setParameter("coachId", coachId).getSingleResult();
		if (result!=null) {
			return (int) (long) result;
			
		}
		return 0;
	}

	@Override
	public int monthAllCoachRevenue() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT sum(cb.orderPrice)  FROM CoachOrderBean cb WHERE datediff(mm,orderDate,getdate())=0  ";
		
		
		Object result = session.createQuery(hql).getSingleResult();
		if (result!=null) {
			return (int) (long) result;
			
		}
		return 0;
	}

	@Override
	public int totalAllCoachRevenue() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT sum(cb.orderPrice) FROM CoachOrderBean cb ";
		
		
		Object result = session.createQuery(hql).getSingleResult();
		if (result!=null) {
			return (int) (long) result;
			
		}
		return 0;
	}

}
