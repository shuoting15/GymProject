package com.gym.coach.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.coach.dao.CoachDao;
import com.gym.coach.model.CoachBean;
@Repository
public class CoachDaoImpl implements CoachDao {
	@Autowired
	SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
	public List<CoachBean> getAllCoachs() {
		String hql = "FROM CoachBean";
		Session session = sessionFactory.getCurrentSession();
		List<CoachBean> list = new ArrayList<CoachBean>();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public CoachBean getCoachById(int coachId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(CoachBean.class, coachId);
	}

	@Override
	public void addCoach(CoachBean coach) {
		Session session = sessionFactory.getCurrentSession();
		session.save(coach);

	}

}
