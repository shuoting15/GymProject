package com.gym.coach.service;

import com.gym.coach.model.CoachRatingBean;

public interface CoachRatingService {
	public void addRating(CoachRatingBean coachRatingBean);

	double countAvgRating(int coachid);
}
