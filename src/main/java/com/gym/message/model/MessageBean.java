package com.gym.message.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gym.member.model.MemberBean;
@Entity
@Table(name="messaget1")
public class MessageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer articleId;
	private String title;
	private String content;
	@JsonIgnore
	private Blob images;
	private String kanbanName;
	private Integer repliseCount;
	private Timestamp time;
	private String  fileName;
	private Integer reportText;
	
	

	@Transient
	private String member_id;
	
	@Transient
	private MultipartFile productImage;
	@OneToMany(mappedBy="messageBean",fetch=FetchType.EAGER)
	@JsonIgnore
	private List<MailboxBean> mailbox;
	
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="member_id")
	private MemberBean memberbean;
	
	
	

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public MemberBean getMemberbean() {
		return memberbean;
	}

	public void setMemberbean(MemberBean memberbean) {
		this.memberbean = memberbean;
	}

	public MessageBean(Integer articleId,String title,String content,Blob images,
			String kanbanName,Integer repliseCount,Timestamp time) {
			this.articleId=articleId;
			this.title=title;
			this.content=content;
			this.images=images;
			this.kanbanName=kanbanName;
			this.repliseCount=0;
			this.time=time;
	}
	
	public MessageBean(String title,String content,Blob images,
			String kanbanName,Integer repliseCount,Timestamp time) {
			this.title=title;
			this.content=content;
			this.images=images;
			this.kanbanName=kanbanName;
			this.repliseCount=0;
			this.time=time;
	}
	
	public MessageBean() {
	}
	
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId( Integer articleId) {
		this.articleId = articleId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Blob getImages() {
		return images;
	}
	public void setImages(Blob images) {
		this.images = images;
	}
	public String getKanbanName() {
		return kanbanName;
	}
	public void setKanbanName(String kanbanName) {
		this.kanbanName = kanbanName;
	}
	public Integer getRepliseCount() {
		return repliseCount;
	}
	public void setRepliseCount(Integer repliseCount) {
		this.repliseCount = repliseCount;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
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

	public List<MailboxBean> getMailbox() {
		return mailbox;
	}

	public void setMailbox(List<MailboxBean> mailbox) {
		this.mailbox = mailbox;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getReportText() {
		return reportText;
	}

	public void setReportText(Integer reportText) {
		this.reportText = reportText;
	}
	
}
