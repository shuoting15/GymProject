package com.gym.mealSystem.meal.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//菜單類別Bean
@Entity
@Table(name = "MealCategory")
public class MealCategoryBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;		//類別ID
	private String categoryName;	//類別名稱
	@OneToMany(mappedBy="mealCategoryBean")
	private Set<MealListBean> foods = new LinkedHashSet<>();

	public MealCategoryBean() {
	}

	public MealCategoryBean(String categoryName) {
		this.categoryName = categoryName;
	}

	public MealCategoryBean(Integer categoryId, String categoryName) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}

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

	
	
	public Set<MealListBean> getFoods() {
		return foods;
	}

	public void setFoods(Set<MealListBean> foods) {
		this.foods = foods;
	}
	
}
