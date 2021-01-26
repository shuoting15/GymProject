package com.gym.course.dao.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.course.dao.CourseInfoDao;
import com.gym.course.model.CourseCoachBean;
import com.gym.course.model.CourseBean;
import com.gym.course.model.CourseInfoBean;

@Repository
public class CourseInfoDaoImpl implements CourseInfoDao {
	
	@Autowired
	SessionFactory factory;
	
	
	@Override
	public List check(String mId, Integer cId) {
		String hql = "from CourseInfoBean where m_id=:mId and c_id=:cId and status=true";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).setParameter("mId", mId).setParameter("cId", cId).getResultList();
	}

	@Override
	public void insert(CourseInfoBean courseInfo) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(courseInfo);
	}

	@Override
	public CourseInfoBean getCourseInfoById(int courseInfoId) {
		Session session = factory.getCurrentSession();
		return session.get(CourseInfoBean.class, courseInfoId);
	}

	@Override
	public void update(CourseInfoBean courseInfo) {
//		String hql = "update CourseInfoBean set status=:status where id=:id";
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(courseInfo);
//		session.createQuery(hql).setParameter("status", courseInfo.getStatus()).setParameter("id", courseInfo.getId()).executeUpdate();
	}

	@Override
	public void updateSelected(CourseBean course) {
		String hql = "update CourseInfoBean ci set ci.c_start=:start, ci.c_end=:end, ci.c_category=:category, ci.c_date=:date, ci.c_location=:location, ci.c_name=:name where ci.c_id=:cid";
		Session session = factory.getCurrentSession();
		String newStartTime = course.getDate() + " " + course.getSt();
		String newEndTime = course.getDate() + " " + course.getEt();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
//		ts = Timestamp.valueOf(course.getStarttime());
//		Timestamp chStart = Timestamp.valueOf(newStartTime);
//		Timestamp chEnd = Timestamp.valueOf(newEndTime);
//		course.setStarttime(chStart);
//		course.setEndtime(chEnd);
		session.createQuery(hql).setParameter("start", Timestamp.valueOf(newStartTime))
								.setParameter("end", Timestamp.valueOf(newEndTime))
								.setParameter("category", course.getCategory())
								.setParameter("date", course.getDate())
								.setParameter("location", course.getLocation())
								.setParameter("name", course.getTitle())
								.setParameter("cid", course.getCourseId()).executeUpdate();
		
	}

	@Override
	public List getCourseInfoBycid(int cid) {
		String hql = "from CourseInfoBean ci where ci.c_id=:cid";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).setParameter("cid", cid).getResultList();
	}

//	@Override
//	public CourseInfoBean getCourseInfo() {
//		String hql = "from CourseInfoBean";
//		Session session = factory.getCurrentSession();
//		
//		return null;
//	}
	
	
	
	

}
