﻿package com.gym.coach.model;

import java.io.Serializable;
import java.sql.Blob;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;
// 本類別封裝單筆教練資料
@Entity
@Table(name="Coach")
public class CoachBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private Integer 	coachId ;
	@Expose
	private String  	coachName;
	@Expose
	private String  	coachExpertise;
	@Expose
	private String  	coachGender;
	@Expose
	private Double  	coachRating;
	@JsonIgnore
	private Blob    	coachPhoto;
	private String      coachIntroduction;
	private String  	coachExpertiseOne;
	private String  	coachExpertiseTwo;
	private String  	coachExpertiseThree;
	private Integer 	coachHeight ;
	private Integer 	coachWeight ;
	@Expose
	private Integer		coachPrice;
	private String  	fileName;
	@Transient
	private MultipartFile	coachImage;
	
	@Transient
	private Integer totalrevenue;
	@Transient
	private Integer monthrevenue;
	
	@Transient
	private Integer totalrevenuePercent;
	@Transient
	private Integer monthrevenuePercent;
	
	@JsonIgnore
	@OneToMany(mappedBy="coachBean",fetch = FetchType.EAGER)
	private  transient Set<CoachOrderBean> coachOrder = new LinkedHashSet<>();
	
	public String getCoachExpertiseOne() {
		return coachExpertiseOne;
	}

	public void setCoachExpertiseOne(String coachExpertiseOne) {
		this.coachExpertiseOne = coachExpertiseOne;
	}

	public String getCoachExpertiseTwo() {
		return coachExpertiseTwo;
	}

	public void setCoachExpertiseTwo(String coachExpertiseTwo) {
		this.coachExpertiseTwo = coachExpertiseTwo;
	}

	public String getCoachExpertiseThree() {
		return coachExpertiseThree;
	}

	public void setCoachExpertiseThree(String coachExpertiseThree) {
		this.coachExpertiseThree = coachExpertiseThree;
	}

	public Integer getCoachHeight() {
		return coachHeight;
	}

	public void setCoachHeight(Integer coachHeight) {
		this.coachHeight = coachHeight;
	}

	public Integer getCoachWeight() {
		return coachWeight;
	}

	public void setCoachWeight(Integer coachWeight) {
		this.coachWeight = coachWeight;
	}

	public String getCoachIntroduction() {
		return coachIntroduction;
	}

	public void setCoachIntroduction(String coachIntroduction) {
		this.coachIntroduction = coachIntroduction;
	}

	public String getCoachExpertise() {
		return coachExpertise;
	}

	public void setCoachExpertise(String coachExpertise) {
		this.coachExpertise = coachExpertise;
	}



	public CoachBean() {}

	public Integer getCoachId() {
		return coachId;
	}

	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
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

	public Set<CoachOrderBean> getCoachOrder() {
		return coachOrder;
	}

	public void setCoachOrder(Set<CoachOrderBean> coachOrder) {
		this.coachOrder = coachOrder;
	}

	public Integer getCoachPrice() {
		return coachPrice;
	}

	public void setCoachPrice(Integer coachPrice) {
		this.coachPrice = coachPrice;
	}

	public Integer getTotalrevenue() {
		return totalrevenue;
	}

	public void setTotalrevenue(Integer totalrevenue) {
		this.totalrevenue = totalrevenue;
	}

	public Integer getMonthrevenue() {
		return monthrevenue;
	}

	public void setMonthrevenue(Integer monthrevenue) {
		this.monthrevenue = monthrevenue;
	}

	public Integer getTotalrevenuePercent() {
		return totalrevenuePercent;
	}

	public void setTotalrevenuePercent(Integer totalrevenuePercent) {
		this.totalrevenuePercent = totalrevenuePercent;
	}

	public Integer getMonthrevenuePercent() {
		return monthrevenuePercent;
	}

	public void setMonthrevenuePercent(Integer monthrevenuePercent) {
		this.monthrevenuePercent = monthrevenuePercent;
	}
	
}
