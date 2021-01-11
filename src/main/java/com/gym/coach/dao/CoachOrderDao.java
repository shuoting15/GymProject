package com.gym.coach.dao;

import java.util.List;

import com.gym.coach.model.ClanderBean;

public interface CoachOrderDao {
	public List<ClanderBean> findTimeByCoachId(int coachId);
	
}
