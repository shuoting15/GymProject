package com.gym.course.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.gym.course.model.CourseCoachBean;
import com.gym.course.model.CourseBean;
import com.gym.course.model.CourseInfoBean;
import com.gym.member.model.MemberBean;

public interface CourseService {

	List<CourseBean> getAllCourses();
	
	void addCourse(CourseBean course);

	CourseCoachBean getCoachById(Integer coachId);
	
	List<CourseCoachBean> getCoachList();
	
	public CourseBean getCourseById(int courseId);
	
	public void updateCourse(CourseBean course, long sizeInBytes);

	int selectCourse(MemberBean memberBean, Integer id);
	
	List<CourseInfoBean> getMyCourses(String userid);
	
	List<CourseInfoBean> getMyNowCourses(String userid);
	
	List<CourseInfoBean> getMyFinishedCourses(String userid);
	
	public CourseInfoBean getCourseInfoById(int courseInfoId);
	
	public void unbookingCourse(CourseInfoBean courseInfo);
	
	List<CourseBean> getAllCoursesByDate(Date date);

	List<CourseBean> courseSearchByKC(String keyword, String cate, String cdate);
	
	void changeAllCoursesStatus();

	List<CourseBean> getSelect();
	
	public void updateSelected(CourseBean course);
	
	public List getCourseInfoBycid(int cid);

	void updateCourseStatus(CourseBean course);

	List<CourseBean> getAllCoursesAdmin();

	List<CourseBean> courseSearchByKCAdmin(String keyword, String cate, String cdate);

	List<CourseBean> getSelectAdmin();

	List<String> checkRoomTime(String date,Timestamp chStart, Timestamp chEnd);
}
