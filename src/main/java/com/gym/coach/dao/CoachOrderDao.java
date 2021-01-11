package com.gym.coach.dao;

import java.util.List;

import com.gym.coach.model.CoachOrderBean;

public interface CoachOrderDao {
	public List<CoachOrderBean> findTimeByCoachId();
	
}
