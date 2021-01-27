package com.gym.message.model;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name="report")
public class ReportBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reportId;
	private String reportContent;
	private Timestamp time;
	
	@Transient
	private String member_id;
	
	@Transient
	private Integer articleId;
	

	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="articleId")
	private MessageBean messageBean;
	
	
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="member_id")
	private MemberBean memberbean;

	
	public ReportBean() {
		
	}
	
	public ReportBean(String reportContent) {
		this.reportContent=reportContent;
	}
	
	public ReportBean(Integer articleId) {
		
	}
	
	public Integer getReportId() {
		return reportId;
	}


	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}


	public String getReportContent() {
		return reportContent;
	}


	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}


	public Timestamp getTime() {
		return time;
	}


	public void setTime(Timestamp time) {
		this.time = time;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public Integer getArticleId() {
		return articleId;
	}


	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}




	public MessageBean getMessageBean() {
		return messageBean;
	}


	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}


	


	public MemberBean getMemberbean() {
		return memberbean;
	}


	public void setMemberbean(MemberBean memberbean) {
		this.memberbean = memberbean;
	}
	
	
}

