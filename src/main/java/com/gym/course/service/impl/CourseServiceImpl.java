package com.gym.course.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.gym.course.dao.CourseDao;
import com.gym.course.dao.CourseInfoDao;
import com.gym.course.model.CourseCoachBean;
import com.gym.course.model.CourseBean;
import com.gym.course.model.CourseInfoBean;
import com.gym.course.service.CourseService;
import com.gym.member.model.MemberBean;

@Service
@EnableTransactionManagement
@Transactional
public class CourseServiceImpl implements CourseService{

	@Autowired
	CourseDao dao;
	
	@Autowired
	CourseInfoDao cdao;
	
	@Override
	public List<CourseBean> getAllCourses() {
		return dao.getAllCourses();
	}

	@Override
	public void addCourse(CourseBean course) {
		dao.addCourse(course);
	}

	@Override
	public CourseCoachBean getCoachById(Integer coachId) {
		return dao.getCoachById(coachId);
	}

	@Override
	public List<CourseCoachBean> getCoachList() {
		return dao.getCoachList();
	}

	@Override
	public CourseBean getCourseById(int courseId) {
		return dao.getCourseById(courseId);
	}


	@Override
	public int selectCourse(MemberBean memberBean, Integer id) {
		CourseBean course = dao.getCourseById(id);
		
		if(course.getSelected() < course.getMax()) {
			List result = cdao.check(memberBean.getMember_id(), id);
			if(result.isEmpty()) {
				CourseInfoBean ci = new CourseInfoBean();
				ci.setC_id(course.getCourseId());
				ci.setC_name(course.getTitle());
				ci.setC_category(course.getCategory());
				ci.setC_date(course.getDate());
				//拿時間存成timestamp
				String newStartTime = course.getDate() + " " + course.getStarttime() + ":00";
				String newEndTime = course.getDate() + " " + course.getEndtime() + ":00";
				Timestamp chStart = Timestamp.valueOf(newStartTime);
				Timestamp chEnd = Timestamp.valueOf(newEndTime);
				ci.setC_start(chStart);
				ci.setC_end(chEnd);
				ci.setCourseBean(course);
				
				//拿會員
				ci.setM_id(memberBean.getMember_id());
				ci.setM_name(memberBean.getUsername() );
				ci.setStatus(true);
				ci.setC_location(course.getLocation());
				ci.setMemberBean(memberBean);
				cdao.insert(ci);
				
				course.setSelected(course.getSelected() + 1);
				dao.update(course);
				return 0;
			}else {
				return 1; //已選過 result 有值
			}
			
		}else {
			return 2; //已選滿 course.getSelected() < course.getMax() 不符條件
		}
	}

	@Override
	public List<CourseInfoBean> getMyCourses(String userid) {
		return dao.getMyCourses(userid);
	}

	@Override
	public CourseInfoBean getCourseInfoById(int courseInfoId) {
		return cdao.getCourseInfoById(courseInfoId);
	}

	@Override
	public void unbookingCourse(CourseInfoBean courseInfo) {
		cdao.update(courseInfo);
		CourseBean course = dao.getCourseById(courseInfo.getC_id());
		course.setSelected(course.getSelected() - 1);
		dao.update(course);
	}

	@Override
	public List<CourseBean> getAllCoursesByDate(Date date) {
		return dao.getAllCoursesByDate(date);
	}

	@Override
	public void updateCourse(CourseBean course, long sizeInBytes) {
		dao.updateCourse(course, sizeInBytes);
	}

	@Override
	public List<CourseBean> courseSearchByKC(String keyword, String cate, String cdate) {
		return dao.courseSearchByKC(keyword, cate, cdate);
	}

	@Override
	public void changeAllCoursesStatus() {
		dao.changeAllCoursesStatus();
		
	}

	@Override
	public List<CourseInfoBean> getMyNowCourses(String userid) {
		return dao.getMyNowCourses(userid);
	}
	
	public List<CourseInfoBean> getMyFinishedCourses(String userid){
		return dao.getMyFinishedCourses(userid);
	}

	@Override
	public List<CourseBean> getSelect() {
		return dao.getSelect();
	}

	@Override
	public void updateSelected(CourseBean course) {
		cdao.updateSelected(course);
	}

	@Override
	public List getCourseInfoBycid(int cid) {
		return cdao.getCourseInfoBycid(cid);
	}

	@Override
	public void updateCourseStatus(CourseBean course) {
		dao.updateCourseStatus(course);
	}

	@Override
	public List<CourseBean> getAllCoursesAdmin() {
		return dao.getAllCoursesAdmin();
	}

	@Override
	public List<CourseBean> courseSearchByKCAdmin(String keyword, String cate, String cdate) {
		return dao.courseSearchByKCAdmin(keyword, cate, cdate);
	}

	@Override
	public List<CourseBean> getSelectAdmin() {
		return dao.getSelectAdmin();
	}

	@Override
	public List<String> checkRoomTime(String date,Timestamp chStart, Timestamp chEnd) {
		return dao.checkRoomTime(date, chStart, chEnd);
	}
	

}
