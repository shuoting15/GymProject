package com.gym.coach.dao;

public interface CoachCustomerManageDao {
	int totalCaochConsumeByMemberId(String memberId,int coachId);
	int monthCaochConsumeByMemberId(String memberId,int coachId);
}
