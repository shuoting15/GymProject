package com.gym.news.model;

import java.io.Serializable;
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
@Entity
@Table(name="News")
public class NewsBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer 	newsId ;
	private String  	newsTitle;
	private Blob    	newsImage;	
	private String  	newsFileName;
	private String  	newsCategory;
	private String  	newsContent;
	private String  	newsUploadTime;
	@Transient
	private Integer  	authorId;
	private Integer  	newsViews;
	private String  	newsVideoName;
	
	@Transient
	private MultipartFile	newsproductImage;  	
	
	@Transient
	private MultipartFile	videofile;  
	
	public MultipartFile getNewsproductImage() {
		return newsproductImage;
	}

	public MultipartFile getVideofile() {
		return videofile;
	}

	public void setVideofile(MultipartFile videofile) {
		this.videofile = videofile;
	}

	public void setNewsproductImage(MultipartFile newsproductImage) {
		this.newsproductImage = newsproductImage;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="FK_AuthorBean_Id") 	
    private AuthorBean authorBean;
	
	public NewsBean(Integer newsId, String newsTitle, String newsFileName,
			Blob newsImage, Integer authorId, String newsCategory, Integer newsViews,String newsContent,String newsUploadTime, String newsVideoName) {
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsFileName = newsFileName;
		this.newsImage = newsImage;
		this.authorId = authorId;
		this.newsCategory = newsCategory;
		this.newsViews = 0;
		this.newsContent=newsContent;
		this.newsUploadTime=newsUploadTime;
		this.newsVideoName=newsVideoName;
	}
	public NewsBean(Integer newsId, String newsTitle, String newsFileName,
			Blob newsImage, Integer authorId, String newsCategory, Integer newsViews,AuthorBean authorBean,String newsContent,String newsUploadTime,String newsVideoName) {
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsFileName = newsFileName;
		this.newsImage = newsImage;
		this.authorId = authorId;
		this.newsCategory = newsCategory;
		this.newsViews = 0;
		this.authorBean=authorBean;
		this.newsContent=newsContent;
		this.newsUploadTime=newsUploadTime;
		this.newsVideoName=newsVideoName;
	}
//	public BookBean(Integer newsId, String title, String author, 
//			Double price, Double discount, String fileName, 
//			String bookNo, Blob coverImage, Integer stock, String category, CompanyBean companyBean) {
//		this.newsId = newsId;
//		this.title = title;
//		this.author = author;
//		this.price = price;
//		this.discount = discount;
//		this.fileName = fileName;
//		this.bookNo = bookNo;
//		this.coverImage = coverImage;
//		this.companyBean = companyBean;
//		this.category = category;
//		this.stock = stock;
//	}

	public NewsBean() {
	}

	public String getNewsVideoName() {
		return newsVideoName;
	}

	public void setNewsVideoName(String newsVideoName) {
		this.newsVideoName = newsVideoName;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public Blob getNewsImage() {
		return newsImage;
	}

	public void setNewsImage(Blob newsImage) {
		this.newsImage = newsImage;
	}

	public String getNewsFileName() {
		return newsFileName;
	}

	public void setNewsFileName(String newsFileName) {
		this.newsFileName = newsFileName;
	}

	public String getNewsCategory() {
		return newsCategory;
	}

	public void setNewsCategory(String newsCategory) {
		this.newsCategory = newsCategory;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getNewsViews() {
		return newsViews;
	}

	public void setNewsViews(Integer newsViews) {
		this.newsViews = newsViews;
	}

	public AuthorBean getAuthorBean() {
		return authorBean;
	}

	public void setAuthorBean(AuthorBean authorBean) {
		this.authorBean = authorBean;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public String getNewsUploadTime() {
		return newsUploadTime;
	}

	public void setNewsUploadTime(String newsUploadTime) {
		this.newsUploadTime = newsUploadTime;
	}
	
//	
//	public Integer getNewsId() {   // bookId
//		return newsId;
//	}
//	public void setNewsId(Integer newsId) {
//		this.newsId = newsId;
//	}
//	
//	public CompanyBean getCompanyBean() {
//		return companyBean;
//	}
//
//	public void setCompanyBean(CompanyBean companyBean) {
//		this.companyBean = companyBean;
//	}
//
//	public String getTitle() {
//		return title;
//	}
//	public void setTitle(String title) {
//		this.title = title;
//	}
//	public String getAuthor() {
//		return author;
//	}
//	public void setAuthor(String author) {
//		this.author = author;
//	}
//	
//	
//	public String getPriceStr() {
//		return priceStr;
//	}
//
//	public void setPriceStr(String priceStr) {
//		this.priceStr = priceStr;
//	}
//	public Double getPrice() {
//		return price;
//	}
//	public void setPrice(double price) {
//		this.price = price;
//		if (priceStr == null) {
//			priceStr = String.valueOf(price);
//		}
//	}
//	public Double getDiscount() {
//		return discount;
//	}
//	public void setDiscount(Double discount) {   //0.8, 0.75
//		if (discount  == null) {
//			this.discount = 1.0;
//			discountStr = "";
//			return;
//		}
//		this.discount = discount;
//		
//		if (discount == 1) {
//			discountStr = "";
//		} else {
//			int dnt = (int)(discount * 100);
//			if (dnt % 10 == 0) {
//				discountStr = (dnt / 10) + "折";
//			} else {
//				discountStr = " "  + dnt + "折";
//			} 
//			
//		}
//	}
//	public String getFileName() {
//		return fileName;
//	}
//	public void setFileName(String fileName) {
//		this.fileName = fileName;
//	}
//	public String getBookNo() {
//		return bookNo;
//	}
//	public void setBookNo(String bookNo) {
//		this.bookNo = bookNo;
//	}
//	
//	public String getDiscountStr() {
//		return discountStr;
//	}	
//	public Blob getCoverImage() {
//		return coverImage;
//	}
//	public void setCoverImage(Blob coverImage) {
//		this.coverImage = coverImage;
//	}
//
//	public Integer getStock() {
//		return stock;
//	}
////
//	public void setStock(Integer stock) {
//		this.stock = stock;
//	}
//	
//	public Integer getCompanyId() {
//		return companyId;
//	}
//
//	public void setCompanyId(Integer companyId) {
//		this.companyId = companyId;
//	}
//
//	public String getCategory() {
//		return category;
//	}
//
//	public void setCategory(String category) {
//		this.category = category;
//	}
//
//	public void setPrice(Double price) {
//		this.price = price;
//	}
}
