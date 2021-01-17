package com.gym.coach.dao.impl;

import java.text.DecimalFormat;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.coach.dao.CoachRatingDao;
import com.gym.coach.model.CoachRatingBean;
@Repository
public class CoachRatingDaoImpl implements CoachRatingDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addRating(CoachRatingBean coachRatingBean) {
		Session session = sessionFactory.getCurrentSession();
		session.save(coachRatingBean);

	}

	@Override
	public double countAvgRating(int coachid) {
		Session session = sessionFactory.getCurrentSession();
		DecimalFormat df=new DecimalFormat("0.00");	
		String hql="SELECT avg(rating * 1.0) FROM CoachRatingBean Where coachId =:coachid ";
		Double  avgRating = (double) session.createQuery(hql).setParameter("coachid", coachid).getSingleResult();
		return  Double.valueOf(df.format(avgRating));
	}

}
