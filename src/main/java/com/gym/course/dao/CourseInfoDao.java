package com.gym.course.dao;

import java.util.List;

import com.gym.course.model.CourseCoachBean;
import com.gym.course.model.CourseBean;
import com.gym.course.model.CourseInfoBean;

public interface CourseInfoDao {

	public List check(String mId, Integer cId);
	
	public void insert(CourseInfoBean courseInfo);
	
	public CourseInfoBean getCourseInfoById(int courseInfoId);
	
	public void update(CourseInfoBean courseInfo);
	
	public void updateSelected(CourseBean course);
	
	public List getCourseInfoBycid(int cid);
	
//	public CourseInfoBean getCourseInfo();
}
