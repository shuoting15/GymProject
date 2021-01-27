package com.gym.course.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CourseCoach")
public class CourseCoachBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id ;
	private String  name;

	@OneToMany(mappedBy="courseCoachBean")
	@JsonIgnore
	private Set<CourseBean> courses = new LinkedHashSet<>();
	
	public CourseCoachBean(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public CourseCoachBean() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Set<CourseBean> getCourses() {
		return courses;
	}
	public void setCourses(Set<CourseBean> courses) {
		this.courses = courses;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}