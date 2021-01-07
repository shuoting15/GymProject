package com.gym.coach.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.coach.dao.CoachDao;
import com.gym.coach.model.CoachBean;
@Service
public class CoachService implements com.gym.coach.service.CoachService {
	@Autowired
	CoachDao dao;
	@Override
	public List<CoachBean> getAllCoachs() {
		// TODO Auto-generated method stub
		return dao.getAllCoachs();
	}

	@Override
	public CoachBean getCoachById(int coachId) {
		// TODO Auto-generated method stub
		return dao.getCoachById(coachId);
	}

	@Override
	public void addCoach(CoachBean coach) {
		dao.addCoach(coach);

	}

}
