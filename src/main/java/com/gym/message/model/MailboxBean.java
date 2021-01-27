package com.gym.message.model;

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

import org.springframework.web.multipart.MultipartFile;

import com.gym.member.model.MemberBean;

@Entity
@Table(name="mailbox")
public class MailboxBean {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer mailboxId;
	private String mailboxContent;
	private Timestamp time;
	private Integer repliesCount;
	
	@Transient
	private Integer articleId;
	
	@Transient
	private String member_id;
	
	@Transient
	private MultipartFile productImage;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="articleId")
	private MessageBean messageBean;
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="member_id")
	private MemberBean memberbean;
	
public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
public MailboxBean(String mailboxContent, MessageBean messageBean) {
		
		this.mailboxContent = mailboxContent;
		this.messageBean = messageBean;
	}
	public Integer getMailboxId() {
		return mailboxId;
	}

	public void setMailboxId(Integer mailboxId) {
		this.mailboxId = mailboxId;
	}

	public String getMailboxContent() {
		return mailboxContent;
	}

	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public void setMailboxContent(String mailboxContent) {
		this.mailboxContent = mailboxContent;
	}

	public MessageBean getMessageBean() {
		return messageBean;
	}

	public void setMessageBean(MessageBean messageBean) {
		this.messageBean = messageBean;
	}

	

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Integer getRepliesCount() {
		return repliesCount;
	}

	public void setRepliesCount(Integer repliesCount) {
		this.repliesCount = repliesCount;
	}

	public MailboxBean(String mailboxContent) {
		
		this.mailboxContent = mailboxContent;
	}

	public MailboxBean() {
		
	}

	public MailboxBean(Integer articleId) {
	
	}
	public MemberBean getMemberbean() {
		return memberbean;
	}
	public void setMemberbean(MemberBean memberbean) {
		this.memberbean = memberbean;
	}
	
	
}
