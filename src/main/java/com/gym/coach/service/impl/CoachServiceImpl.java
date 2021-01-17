package com.gym.coach.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.coach.dao.CoachDao;
import com.gym.coach.model.CoachBean;
@Service
public class CoachServiceImpl implements com.gym.coach.service.CoachService {
	@Autowired
	CoachDao dao;
	@Transactional
	@Override
	public List<CoachBean> getAllCoachs() {
		// TODO Auto-generated method stub
		return dao.getAllCoachs();
	}
	@Transactional
	@Override
	public CoachBean getCoachById(int coachId) {
		// TODO Auto-generated method stub
		return dao.getCoachById(coachId);
	}
	@Transactional
	@Override
	public void addCoach(CoachBean coach) {
		dao.addCoach(coach);

	}
	@Transactional
	@Override
	public int updateCoach(CoachBean bean, long sizeInBytes) {
		int n = 0;
		    n = dao.updateCoach(bean, sizeInBytes);
		return n;
	}
	@Transactional
	@Override
	public int deleteCoach(int coachId) {
		int n = 0;
          n = dao.deleteCoach(coachId);
      return n;
	}
	@Transactional
	@Override
	public List<String> getAllExpertise() {

		return dao.getAllExpertise();
	}
	@Transactional
	@Override
	public void updateCoachRating(CoachBean coachBean) {
		dao.updateCoachRating(coachBean);
		
	}

}
