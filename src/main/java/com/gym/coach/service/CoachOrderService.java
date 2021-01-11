package com.gym.coach.service;

import java.util.List;

import com.gym.coach.model.CoachOrderBean;

public interface CoachOrderService {
	
	public List<CoachOrderBean> findTimeByCoachId();

}
