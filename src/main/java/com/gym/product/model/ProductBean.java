package com.gym.product.model;

import java.sql.Blob;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity @Table(name="product")
public class ProductBean {
	@Id @Column @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer productNo;
	private String productName;
	private String productCategory;
	private Double productPrice;
	private Double discount;
	private Integer productInStock;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date productDate;
	private String productDescription;
	@JsonIgnore
	private Blob coverImage;
	private String fileName;
	
	@Transient
	MultipartFile productImage;
	
	public ProductBean() {}
	public ProductBean(String productName,String productCategory,Double productPrice,Double discount,Integer productInStock,Date productDate,String productDescription,Blob coverImage,String fileName) {
		this.productName=productName;
//		this.productNo=productNo;
		this.productCategory=productCategory;
		this.productPrice=productPrice;
		this.discount=discount;
		this.productInStock=productInStock;
		this.productDate=productDate;
		this.productDescription=productDescription;
		this.setCoverImage(coverImage);
		this.fileName=fileName;
	}
	public ProductBean(Integer productId,String productName,String productCategory,Double productPrice,Double discount,Integer productInStock,Date productDate,String productDescription,Blob coverImage,String fileName) {
		this.productName=productName;
		this.productId=productId;
//		this.productNo=productNo;
		this.productCategory=productCategory;
		this.productPrice=productPrice;
		this.discount=discount;
		this.productInStock=productInStock;
		this.productDate=productDate;
		this.productDescription=productDescription;
		this.setCoverImage(coverImage);
		this.fileName=fileName;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
//	public String getProductNo2() {
//		return productNo;
//	}
//	public Integer getProductNo() {
////		SimpleDateFormat df = new SimpleDateFormat("YYYYMMdd");
////		String tempproductNo = df.format(new java.util.Date()) + (int)(Math.random()*100);
////		productNo = tempproductNo;
//		return productNo;
//	}
//	public void setProductNo(Integer productNo) {
//		this.productNo = productNo;
//	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public Integer getProductInStock() {
		return productInStock;
	}
	public void setProductInStock(int productInStock) {
		this.productInStock = productInStock;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
    
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Blob getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(Blob coverImage) {
		this.coverImage = coverImage;
	}

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	

}
