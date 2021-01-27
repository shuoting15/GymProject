package com.gym.mealSystem.meal.model;

import java.sql.Blob;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import org.springframework.web.multipart.MultipartFile;

//菜單目錄bean
@Entity
@Table(name = "MealList")
public class MealListBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mealId;					//餐點ID
	private String mealName;				//餐點名稱
	private String mealImgName;				//餐點照片名稱
	private Blob mealImage;					//餐點照片
	private Double mealPrice;				//餐點價格
	private String mealContent;				//餐點內容
	private Integer mealKcal;				//餐點卡路里
	@Transient
	private Integer categoryId;				//類別ID
	@Transient
	private MultipartFile productImage;		//圖片上傳
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "categoryId")
	private MealCategoryBean mealCategoryBean;
	
	public Integer getMealId() {
		return mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public String getMealImgName() {
		return mealImgName;
	}

	public void setMealImgName(String mealImgName) {
		this.mealImgName = mealImgName;
	}

	public Blob getMealImage() {
		return mealImage;
	}

	public void setMealImage(Blob mealImage) {
		this.mealImage = mealImage;
	}

	public Double getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(Double mealPrice) {
		this.mealPrice = mealPrice;
	}

	public String getMealContent() {
		return mealContent;
	}

	public void setMealContent(String mealContent) {
		this.mealContent = mealContent;
	}

	public Integer getMealKcal() {
		return mealKcal;
	}

	public void setMealKcal(Integer mealKcal) {
		this.mealKcal = mealKcal;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public MealCategoryBean getMealCategoryBean() {
		return mealCategoryBean;
	}

	public void setMealCategoryBean(MealCategoryBean mealCategoryBean) {
		this.mealCategoryBean = mealCategoryBean;
	}

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}



	@Override
	public String toString() {
		return "MealListBean [mealId=" + mealId + ", mealName=" + mealName + ", mealImgName=" + mealImgName
				+ ", mealImage=" + mealImage + ", mealPrice=" + mealPrice
				+ ", mealContent=" + mealContent + ", mealKcal=" + mealKcal
				+ ", categoryId=" + categoryId + ", mealCategoryBean="
				+ mealCategoryBean + ", productImage=" + productImage + "]";
	}
	
	

}
