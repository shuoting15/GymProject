package com.gym.mealSystem.order.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gym.mealSystem.meal.model.MealListBean;
import com.gym.member.model.MemberBean;


//訂單資料bean
@Entity
@Table(name="MealOrder")
public class MealOrderBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderNo;			//訂單編號
	private Date   orderStartTime;		//訂餐時間
	private Date   orderFinishTime;		//取餐時間
	private Double	totalAmount;		//訂單金額
	private Integer orderStatus;		//訂單狀態
	@Transient
	private String memberId;			//會員ID
	@Transient
	private Integer mealId;					//餐點ID
	
	@ManyToOne(cascade=CascadeType.ALL)    // javax.persistence.CascadeType;
	@JoinColumn(name="memberId")  
	private MemberBean memberBean;
	@ManyToOne(cascade=CascadeType.ALL)    // javax.persistence.CascadeType;
	@JoinColumn(name="mealId")  
	private  MealListBean mealListBean;
	
	public MealOrderBean() {
	}
	
	public MealOrderBean(Integer orderNo, Date orderStartTime, Date orderFinishTime, Double totalAmount,
			Integer orderStatus, String memberId, Integer mealId) {
		super();
		this.orderNo = orderNo;
		this.orderStartTime = orderStartTime;
		this.orderFinishTime = orderFinishTime;
		this.totalAmount = totalAmount;
		this.orderStatus = orderStatus;
		this.memberId = memberId;
		this.mealId = mealId;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(Date orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public Date getOrderFinishTime() {
		return orderFinishTime;
	}

	public void setOrderFinishTime(Date orderFinishTime) {
		this.orderFinishTime = orderFinishTime;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getMealId() {
		return mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public MealListBean getMealListBean() {
		return mealListBean;
	}

	public void setMealListBean(MealListBean mealListBean) {
		this.mealListBean = mealListBean;
	}

	
	@Override
	public String toString() {
		return "OrderBean [orderNo=" + orderNo + ", orderStartTime=" + orderStartTime + ", orderFinishTime=" + orderFinishTime + ", totalAmount="
				+ totalAmount + ", orderStatus=" + orderStatus + ", memberId=" + memberId + ", mealId=" + mealId
				+ ", memberBean=" + memberBean + ", mealListBean=" + mealListBean + "]";
	}		
	
}
