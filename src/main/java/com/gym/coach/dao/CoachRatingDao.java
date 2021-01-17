package com.gym.coach.dao;

import com.gym.coach.model.CoachRatingBean;

public interface CoachRatingDao {
public void addRating(CoachRatingBean coachRatingBean);
double  countAvgRating(int coachid);
}
