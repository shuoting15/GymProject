package com.gym.shoppingcart.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="OrderItem")
public class OrderItemBean {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer seqno;
	private Integer productId;
	private Double unitPrice;
	private Integer quantity;
	private Double discount;
	private String productName;
	@ManyToOne
	@JoinColumn(name = "orderNo")
	@JsonIgnore
	OrderBean orderBean;

	public OrderItemBean() {}
	
	public OrderItemBean(Integer seqno, Integer productId,Integer quantity, 
			Double unitPrice, Double discount,String productName) {
		this.seqno = seqno;
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.productName= productName;
	}
	public OrderItemBean(Integer productId,Integer quantity, 
			Double unitPrice, Double discount,String productName) {
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.discount = discount;
		this.productName= productName;
	}
	
	public Integer getSeqno() {
		return seqno;
	}

	public void setSeqno(Integer seqno) {
		this.seqno = seqno;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public OrderBean getOrderBean() {
		return orderBean;
	}

	public void setOrderBean(OrderBean orderBean) {
		this.orderBean = orderBean;
	}
	

}
