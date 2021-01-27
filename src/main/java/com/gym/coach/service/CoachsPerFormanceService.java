package com.gym.coach.service;

public interface CoachsPerFormanceService {
	public int totalCoachRevenue(int coachId);
	public int monthCoachRevenue(int coachId);
	public int monthAllCoachRevenue();
	public int totalAllCoachRevenue();
}
