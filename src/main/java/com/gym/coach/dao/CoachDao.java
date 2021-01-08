package com.gym.coach.dao;

import java.util.List;

import com.gym.coach.model.CoachBean;

public interface CoachDao {
	List<CoachBean> getAllCoachs();
	CoachBean getCoachById(int coachId);
	void addCoach(CoachBean coach);
	public int updateCoach(CoachBean bean, long sizeInBytes);
	int deleteCoach(int coachId);
}
