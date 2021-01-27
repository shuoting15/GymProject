package com.gym.coach.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.coach.dao.CoachCustomerManageDao;
import com.gym.coach.service.CoachCustomerManageService;
@Service
public class CoachCustomerManageServiceImpl implements CoachCustomerManageService {
	@Autowired
	CoachCustomerManageDao dao;
    @Transactional
	@Override
	public int totalCaochConsumeByMemberId(String memberId,int coachId) {
		// TODO Auto-generated method stub
		return dao.totalCaochConsumeByMemberId(memberId,coachId);
	}

    @Transactional
    @Override
	public int monthCaochConsumeByMemberId(String memberId,int coachId) {
		// TODO Auto-generated method stub
		return dao.monthCaochConsumeByMemberId(memberId,coachId);
	}

}
