package com.gym.course.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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


@Entity
@Table(name = "Course")
public class CourseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer 	courseId;
	private String  	title;
	private String  	date;
	private String  	location;
	private Integer  	price;
	private String		description;
	@Transient
	private String  	priceStr = null;
	private Double  	discount;
	@JsonIgnore
	private Blob    	coverImage;	
	private String  	fileName;
//	private String  	bookNo;
	@Transient
	private String  	discountStr;
	private String  	category;
	private Integer  	max;
	private Integer		selected;
	private Timestamp	starttime;
	private Timestamp	endtime;
	@Transient
	private String		st;
	@Transient
	private String		et;
	@Transient
	private Integer  	coachId;
	private Boolean		status;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="FK_CoachBean_Id") 
    private CourseCoachBean courseCoachBean;
	
	@OneToMany(mappedBy = "courseBean")
	@JsonIgnore
	private List<CourseInfoBean> infos = new LinkedList();
	
	@Transient 
	private MultipartFile productImage;
	

	public CourseBean() {
		
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public List<CourseInfoBean> getInfos() {
		return infos;
	}


	public void setInfos(List<CourseInfoBean> infos) {
		this.infos = infos;
	}


	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPriceStr() {
		return priceStr;
	}

	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
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

	public String getDiscountStr() {
		return discountStr;
	}

	public void setDiscountStr(String discountStr) {
		this.discountStr = discountStr;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public Integer getCoachId() {
		return coachId;
	}

	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}

	public CourseCoachBean getCourseCoachBean() {
		return courseCoachBean;
	}


	public void setCourseCoachBean(CourseCoachBean courseCoachBean) {
		this.courseCoachBean = courseCoachBean;
	}


	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	public String getStarttime() {
		String stm = starttime.toString();
		return stm.substring(11,16);
		
//		return starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		String etm = endtime.toString();
		return etm.substring(11,16);
		
//		return endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getEt() {
		return et;
	}

	public void setEt(String et) {
		this.et = et;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
