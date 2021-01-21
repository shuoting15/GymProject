package com.gym.coach.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CoachRating")
public class CoachRatingBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int coachRatingId;
	int rating;
	String memberId;
	int coachId;
	String comment;

	public int getCoachRatingId() {
		return coachRatingId;
	}

	public void setCoachRatingId(int coachRatingId) {
		this.coachRatingId = coachRatingId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getCoachId() {
		return coachId;
	}

	public void setCoachId(int coachId) {
		this.coachId = coachId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
