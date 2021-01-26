package com.gym.coach.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.coach.dao.CoachPerformanceDao;
import com.gym.coach.service.CoachsPerFormanceService;
@Service
public class CoachsPerFormanceServiceImpl implements CoachsPerFormanceService {
	@Autowired
	CoachPerformanceDao dao;
	@Override
	@Transactional
	public int totalCoachRevenue(int coachId) {
		
		return dao.totalCoachRevenue(coachId);
	}
	@Transactional
	@Override
	public int monthCoachRevenue(int coachId) {
		
		return dao.monthCoachRevenue(coachId);
	}
	@Transactional
	@Override
	public int monthAllCoachRevenue() {
		// TODO Auto-generated method stub
		return dao.monthAllCoachRevenue();
	}
	@Transactional
	@Override
	public int totalAllCoachRevenue() {
		// TODO Auto-generated method stub
		return dao.totalAllCoachRevenue();
	}

}
