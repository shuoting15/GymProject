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
@Table(name="NewsPlaylist")
public class NewsPlaylistBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private String  member_id ;
	private int  FK_NewsBean_newsId;

		
	public NewsPlaylistBean(Integer FK_NewsBean_newsId,String  member_id) {
		this.FK_NewsBean_newsId=FK_NewsBean_newsId;
		this.member_id=member_id;
	}
	public NewsPlaylistBean() {
		
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public int getFK_NewsBean_newsId() {
		return FK_NewsBean_newsId;
	}
	public void setFK_NewsBean_newsId(int fK_NewsBean_newsId) {
		FK_NewsBean_newsId = fK_NewsBean_newsId;
	}



	
}
