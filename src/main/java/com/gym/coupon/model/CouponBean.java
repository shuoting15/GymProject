package com.gym.coupon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Coupons")
public class CouponBean {
	@Id
	@Column(name="discountid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer couponId;
	@Column(name="code")
	String code;
	@Column(name="amount")
	String amount;
	@Column(name="condition")
	String condition;

	public CouponBean() {

	}

	public CouponBean(String code, String amount, String condition) {
		this.code = code;
		this.amount = amount;
		this.condition = condition;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
