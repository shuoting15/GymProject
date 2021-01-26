package com.gym.coach.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.coach.dao.CoachCustomerManageDao;
@Repository
public class CoachCustomerManageImpl implements CoachCustomerManageDao {
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public int totalCaochConsumeByMemberId(String memberId,int coachId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT sum(cb.orderPrice)  FROM CoachOrderBean cb WHERE memberBean.member_id = :memberId and coachBean.coachId = :coachId ";
		
		
		Object result = session.createQuery(hql).setParameter("memberId", memberId).setParameter("coachId", coachId).getSingleResult();
		if (result!=null) {
			return (int) (long) result;
			
		}
		return 0;
	}

	@Override
	public int monthCaochConsumeByMemberId(String memberId,int coachId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT sum(cb.orderPrice)  FROM CoachOrderBean cb WHERE memberBean.member_id = :memberId and coachBean.coachId = :coachId  and datediff(mm,orderDate,getdate())=0  ";
		
		
		Object result = session.createQuery(hql).setParameter("memberId", memberId).setParameter("coachId", coachId).getSingleResult();
		if (result!=null) {
			return (int) (long) result;
			
		}
		return 0;
	}

}
