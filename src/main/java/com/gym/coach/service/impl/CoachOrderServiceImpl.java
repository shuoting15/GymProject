package com.gym.coach.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.coach.dao.CoachOrderDao;
import com.gym.coach.model.ClanderBean;
import com.gym.coach.model.CoachOrderBean;
import com.gym.coach.service.CoachOrderService;
@Service
public class CoachOrderServiceImpl implements CoachOrderService {
	@Autowired
	CoachOrderDao dao;
	
	
	@Transactional
	@Override
	public List<ClanderBean> findTimeByCoachId(int coachId) {
		// TODO Auto-generated method stub
		return dao.findTimeByCoachId(coachId);
	}

}
