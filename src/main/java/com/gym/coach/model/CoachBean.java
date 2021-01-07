package com.gym.coach.model;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;
// 本類別封裝單筆教練資料
@Entity
@Table(name="Coach")
public class CoachBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer 	coachID ;
	private String  	coachName;
	private String  	coachGender;
	private Double  	coachRating;
	private Blob    	coachPhoto;	
	private String  	fileName;
	@Transient
	private MultipartFile	coachImage;

	public CoachBean() {}

	public Integer getCoachID() {
		return coachID;
	}

	public void setCoachID(Integer coachID) {
		this.coachID = coachID;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public String getCoachGender() {
		return coachGender;
	}

	public void setCoachGender(String coachGender) {
		this.coachGender = coachGender;
	}

	public Double getCoachRating() {
		return coachRating;
	}

	public void setCoachRating(Double coachRating) {
		this.coachRating = coachRating;
	}

	public Blob getCoachPhoto() {
		return coachPhoto;
	}

	public void setCoachPhoto(Blob coachPhoto) {
		this.coachPhoto = coachPhoto;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MultipartFile getCoachImage() {
		return coachImage;
	}

	public void setCoachImage(MultipartFile coachImage) {
		this.coachImage = coachImage;
	}
	
}
