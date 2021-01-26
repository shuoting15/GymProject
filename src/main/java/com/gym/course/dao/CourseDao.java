package com.gym.course.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.gym.course.model.CourseCoachBean;
import com.gym.course.model.CourseBean;
import com.gym.course.model.CourseInfoBean;


public interface CourseDao {

	List<CourseBean> getAllCourses();
	
	void changeAllCoursesStatus();
	
	void addCourse(CourseBean course);
	
	CourseCoachBean getCoachById(Integer coachId);
	
	List<CourseCoachBean> getCoachList();
	
	public CourseBean getCourseById(int courseId);

	void update(CourseBean course);
	
	List<CourseInfoBean> getMyCourses(String userid);
	
	List<CourseInfoBean> getMyNowCourses(String userid);
	
	List<CourseInfoBean> getMyFinishedCourses(String userid);
	
	List<CourseBean> getAllCoursesByDate(Date date);
	
	public void updateCourse(CourseBean course, long sizeInBytes);
	
	List<CourseBean> findCourseByName(String name);

	List<CourseBean> courseSearchByKC(String keyword, String cate, String cdate);

	List<CourseBean> getSelect();
	
	void updateCourseStatus(CourseBean course);

	List<CourseBean> getAllCoursesAdmin();

	List<CourseBean> courseSearchByKCAdmin(String keyword, String cate, String cdate);

	List<CourseBean> getSelectAdmin();

	List<String> checkRoomTime(String date,Timestamp chStart, Timestamp chEnd);
}
