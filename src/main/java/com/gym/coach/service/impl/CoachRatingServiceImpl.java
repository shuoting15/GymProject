package com.gym.coach.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.coach.dao.CoachRatingDao;
import com.gym.coach.model.CoachRatingBean;
import com.gym.coach.service.CoachRatingService;
@Service
public class CoachRatingServiceImpl implements CoachRatingService, CoachRatingDao {
	@Autowired
	CoachRatingDao dao;

	@Transactional
	@Override
	public void addRating(CoachRatingBean coachRatingBean) {
		dao.addRating(coachRatingBean);

	}
	@Transactional
	@Override
	public double countAvgRating(int coachid) {
		
		return dao.countAvgRating(coachid);
	}

}
