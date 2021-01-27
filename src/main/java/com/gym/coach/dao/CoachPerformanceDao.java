package com.gym.coach.dao;

public interface CoachPerformanceDao {
	public int totalCoachRevenue(int coachId);
	public int monthCoachRevenue(int coachId);
	public int monthAllCoachRevenue();
	public int totalAllCoachRevenue();
}
