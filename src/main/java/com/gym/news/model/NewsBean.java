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

import com.gym.coach.model.CoachBean;
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
	private String  	newsVideoPath;
	@Transient
	private int  FK_CoachBean_coachId;
//	@ManyToOne(cascade=CascadeType.REFRESH)
	@ManyToOne
	@JoinColumn(name="FK_CoachBean_coachId") 	
    private CoachBean coachBean;
	
	
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


	
	public NewsBean(Integer newsId, String newsTitle, String newsFileName,
			Blob newsImage, Integer authorId, String newsCategory, Integer newsViews,String newsContent,String newsUploadTime, String newsVideoPath) {
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsFileName = newsFileName;
		this.newsImage = newsImage;
		this.authorId = authorId;
		this.newsCategory = newsCategory;
		this.newsViews = 0;
		this.newsContent=newsContent;
		this.newsUploadTime=newsUploadTime;
		this.newsVideoPath=newsVideoPath;
	}
	public NewsBean(Integer newsId, String newsTitle, String newsFileName,
			Blob newsImage, Integer authorId, String newsCategory, Integer newsViews,CoachBean coachBean,String newsContent,String newsUploadTime,String newsVideoPath) {
		this.newsId = newsId;
		this.newsTitle = newsTitle;
		this.newsFileName = newsFileName;
		this.newsImage = newsImage;
		this.authorId = authorId;
		this.newsCategory = newsCategory;
		this.newsViews = 0;
		this.coachBean=coachBean;
		this.newsContent=newsContent;
		this.newsUploadTime=newsUploadTime;
		this.newsVideoPath=newsVideoPath;
	}


	public NewsBean() {
	}

	public String getNewsVideoPath() {
		return newsVideoPath;
	}

	public void setNewsVideoPath(String newsVideoPath) {
		this.newsVideoPath = newsVideoPath;
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

	public CoachBean getCoachBean() {
		return coachBean;
	}

	public void setCoachBean(CoachBean coachBean) {
		this.coachBean = coachBean;
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

	public int getFK_CoachBean_coachId() {
		return FK_CoachBean_coachId;
	}

	public void setFK_CoachBean_coachId(int fK_CoachBean_coachId) {
		FK_CoachBean_coachId = fK_CoachBean_coachId;
	}
	
}
