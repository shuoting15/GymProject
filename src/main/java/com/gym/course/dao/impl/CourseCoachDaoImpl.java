package com.gym.course.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.course.dao.CourseCoachDao;
import com.gym.course.model.CourseCoachBean;

@Repository
public class CourseCoachDaoImpl implements CourseCoachDao {

	@Autowired
	SessionFactory factory;
	
	@Override
	public CourseCoachBean getCoachById(int id) {
		Session session = factory.getCurrentSession();
		return session.get(CourseCoachBean.class, id);
	}

	
}
