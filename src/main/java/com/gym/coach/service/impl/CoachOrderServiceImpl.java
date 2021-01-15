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
	@Transactional
	@Override
	public void addCoachTime(CoachOrderBean coachOrderBean) {

		dao.addCoachTime(coachOrderBean);

	}
	@Transactional
	@Override
	public CoachOrderBean getCoachTimeById(int orderId) {
		// TODO Auto-generated method stub
		return dao.getCoachTimeById(orderId);
	}
	@Transactional
	@Override
	public void orderCoachTime(CoachOrderBean coachOrderBean) {
		dao.orderCoachTime(coachOrderBean);
		
	}
	@Transactional
	@Override
	public void deleteCoachTime(int coachId) {
		dao.deleteCoachTime(coachId);
		
	}
	@Transactional
	@Override
	public List<CoachOrderBean> findBookingByMemberId(String memberId) {
		
		return dao.findBookingByMemberId(memberId);
	}

}
