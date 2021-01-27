package com.gym.coach.model;

import java.io.Serializable;
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

import com.gym.member.model.MemberBean;

@Entity
@Table(name="CoachOrder")
public class CoachOrderBean  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderId;
	private String orderStatus;
	private String orderTitle;
	private String orderColor;
	private Date   orderDate;
	private Date   orderStartTime;
	private Date   orderEndTime;
	private int orderPrice;
	@Transient
	private String memberId;
	@Transient
	private int coachId;
	
	@ManyToOne(cascade=CascadeType.ALL)    // javax.persistence.CascadeType;
	@JoinColumn(name="coachId")  
	private CoachBean coachBean;
	
	@ManyToOne(cascade=CascadeType.ALL)    // javax.persistence.CascadeType;
	@JoinColumn(name="memberId")  
	private  MemberBean memberBean;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getOrderStartTime() {
		return orderStartTime;
	}

	public void setOrderStartTime(Date orderStartTime) {
		this.orderStartTime = orderStartTime;
	}

	public Date getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(Date orderEndTime) {
		this.orderEndTime = orderEndTime;
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

	public CoachBean getCoachBean() {
		return coachBean;
	}

	public void setCoachBean(CoachBean coachBean) {
		this.coachBean = coachBean;
	}

	public String getOrderColor() {
		return orderColor;
	}

	public void setOrderColor(String orderColor) {
		this.orderColor = orderColor;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	
	
}
