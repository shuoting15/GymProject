package com.gym.course.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "CourseCategory")
public class CourseCategoryBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer 	categoryId;
	private String 		categoryName;
	
	@Transient
	private Integer totalrevenue;
	@Transient
	private Integer monthrevenue;
	@Transient
	private Integer totalrevenuePercent;
	@Transient
	private Integer monthrevenuePercent;
	
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
