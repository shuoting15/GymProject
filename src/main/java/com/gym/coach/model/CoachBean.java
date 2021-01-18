package com.gym.coach.model;

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
	private Integer 	coachId ;
	private String  	coachName;
	private String  	coachExpertise;
	private String  	coachGender;
	private Double  	coachRating;
	@JsonIgnore
	private Blob    	coachPhoto;
	private String      coachIntroduction;
	private String  	coachExpertiseOne;
	private String  	coachExpertiseTwo;
	private String  	coachExpertiseThree;
	private Integer 	coachHeight ;
	private Integer 	coachWeight ;
	private Integer		coachPrice;
	
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

	private String  	fileName;
	@Transient
	private MultipartFile	coachImage;

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
	
}
