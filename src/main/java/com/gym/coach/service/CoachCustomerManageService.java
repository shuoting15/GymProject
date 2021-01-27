package com.gym.coach.service;

public interface CoachCustomerManageService {
	int totalCaochConsumeByMemberId(String memberId,int coachId);
	int monthCaochConsumeByMemberId(String memberId,int coachId);
}
