package com.gym.coach.service;

import java.util.List;

import com.gym.coach.model.ClanderBean;

public interface CoachOrderService {
	
	public List<ClanderBean> findTimeByCoachId(int coachId);

}
