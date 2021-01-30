package com.gym.course.model;

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

import com.gym.member.model.MemberBean;

@Entity
@Table(name = "CourseInfo")
public class CourseInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	// 會員資料
	private String m_id;
	private String m_name;

	// 課程資料
	private Integer 	c_id;
	private String 		c_name;
	private String 		c_category;
	private String 		c_date;
	private Timestamp 	c_start;
	private Timestamp 	c_end;
	private String 		c_location;
	private Integer		c_price;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="FK_CourseBean_Id") 
	private CourseBean courseBean;

	@ManyToOne(cascade=CascadeType.ALL)    // javax.persistence.CascadeType;
	@JoinColumn(name="FK_MemberBean_Id")  
	private  MemberBean memberBean;
	
	public CourseBean getCourseBean() {
		return courseBean;
	}

	public void setCourseBean(CourseBean courseBean) {
		this.courseBean = courseBean;
	}

	public String getC_start() {
		String stm = c_start.toString();
		return stm.substring(11,16);
//		return c_start;
	}

	public void setC_start(Timestamp c_start) {
		this.c_start = c_start;
	}

	public String getC_end() {
		String etm = c_end.toString();
		return etm.substring(11,16);
//		return c_end;
	}

	public void setC_end(Timestamp c_end) {
		this.c_end = c_end;
	}

	// 狀態
	private boolean status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getM_name() {
		return m_name;
	}

	public void setM_name(String m_name) {
		this.m_name = m_name;
	}

	public Integer getC_id() {
		return c_id;
	}

	public void setC_id(Integer c_id) {
		this.c_id = c_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_category() {
		return c_category;
	}

	public void setC_category(String c_category) {
		this.c_category = c_category;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getC_location() {
		return c_location;
	}

	public void setC_location(String c_location) {
		this.c_location = c_location;
	}

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}

	public MemberBean getMemberBean() {
		return memberBean;
	}

	public void setMemberBean(MemberBean memberBean) {
		this.memberBean = memberBean;
	}

	public Integer getC_price() {
		return c_price;
	}

	public void setC_price(Integer c_price) {
		this.c_price = c_price;
	}


}
