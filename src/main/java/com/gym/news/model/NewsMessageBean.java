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
import com.gym.member.model.MemberBean;
@Entity
@Table(name="NewsMessage")
public class NewsMessageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer 	newsMessageId ;
	private String  	newsMessage;
	private String  	newsMessageTime;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="FK_NewsBean_newsTitle") 	
    private NewsBean newsBean;  
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="FK_MemberBean_username") 	
    private MemberBean memberBean;

	public Integer getNewsMessageId() {
		return newsMessageId;
	}
	public NewsMessageBean(Integer newsMessageId,String newsMessage,NewsBean newsBean,MemberBean memberBean,String newsMessageTime) {
		this.newsMessageId=newsMessageId;
		this.newsMessage=newsMessage;
		this.newsBean=newsBean;
		this.memberBean=memberBean;
		this.newsMessageTime=newsMessageTime;
	}
	public void setNewsMessageId(Integer newsMessageId) {
		this.newsMessageId = newsMessageId;
	}

	public String getNewsMessage() {
		return newsMessage;
	}
	
	public NewsMessageBean() {
		
	}
	public void setNewsMessage(String newsMessage) {
		this.newsMessage = newsMessage;
	}

	public NewsBean getNewsBean() {
		return newsBean;
	}

	public void setNewsBean(NewsBean newsBean) {
		this.newsBean = newsBean;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}
	public String getNewsMessageTime() {
		return newsMessageTime;
	}
	public void setNewsMessageTime(String newsMessageTime) {
		this.newsMessageTime = newsMessageTime;
	}  
	
}
