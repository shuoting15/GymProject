package com.gym.coach.service;

import java.util.List;

import com.gym.coach.model.CoachBean;

public interface CoachService {
	List<CoachBean> getAllCoachs();

	CoachBean getCoachById(int coachId);

	void addCoach(CoachBean coach);
	
	int updateCoach(CoachBean bean, long sizeInBytes) ;
	
	int deleteCoach(int coachId);
	
	List<String> getAllExpertise();
	
	void updateCoachRating(CoachBean coachBean);
	
	List<CoachBean> getCoachsByExpertise(String expertise);
	
	List<CoachBean> getCoachsByFuzzySearch(String any);
}
