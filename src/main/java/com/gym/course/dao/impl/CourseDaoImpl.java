package com.gym.course.dao.impl;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.course.dao.CourseCoachDao;
import com.gym.course.dao.CourseDao;
import com.gym.course.dao.CourseInfoDao;
import com.gym.course.model.CourseCoachBean;
import com.gym.course.model.CourseBean;
import com.gym.course.model.CourseInfoBean;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	SessionFactory factory;
	
	@Autowired
	CourseCoachDao dao;
	
	@Autowired
	CourseInfoDao cidao;
	
	@Override
	public List<CourseBean> getAllCourses() {
		String hql = "from CourseBean c where c.status = true order by c.starttime";
		Session session = factory.getCurrentSession();
		List list = session.createQuery(hql).getResultList();
		return list;
	}
	

	@Override
	public void addCourse(CourseBean course) {
		Session session = factory.getCurrentSession();
		session.save(course);
	}
	
//	public void checkCourseexist(CourseBean course) {
//		String hql = "from CourseBean c where c.location=:location";
//		Session session = factory.getCurrentSession();
//		session.createQuery(hql).setParameter("location", course.getLocation()).getResultList();
//		
//	}

	@Override
	public CourseCoachBean getCoachById(Integer coachId) {
		Session session = factory.getCurrentSession();
		return session.get(CourseCoachBean.class, coachId);
	}

	@Override
	public List<CourseCoachBean> getCoachList() {
		String hql = "from CourseCoachBean";
		Session session = factory.getCurrentSession();
		List list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public CourseBean getCourseById(int courseId) {
		Session session = factory.getCurrentSession();
		CourseBean bean = session.get(CourseBean.class, courseId);
		return bean; 
	}

	@Override
	public void update(CourseBean course) {
		String hql = "update CourseBean set selected=:selected where courseId=:id";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("selected", course.getSelected()).setParameter("id", course.getCourseId()).executeUpdate();
	}

	@Override
	public List<CourseInfoBean> getMyCourses(String userid) {
		String hql = "from CourseInfoBean where m_id=:mid and c_start > :now order by c_start";
		Session session = factory.getCurrentSession();
		Timestamp ts = new Timestamp(System.currentTimeMillis()); 
		List list = session.createQuery(hql).setParameter("mid", userid).setParameter("now", ts).getResultList();
		return list;
	}
	

	@Override
	public List<CourseBean> getAllCoursesByDate(Date date) {
		String hql = "from CourseBean where date=:date order by time desc";
		Session session = factory.getCurrentSession();
		List list = session.createQuery(hql).setParameter("date", date).getResultList();
		return list;
	}

	@Override
	public void updateCourse(CourseBean course, long sizeInBytes) {
//		cidao.getCourseInfoById(course.get)
		if(course.getCourseCoachBean() == null) {
			CourseCoachBean coachbean = dao.getCoachById(course.getCoachId());
			course.setCourseCoachBean(coachbean);
		}
		if(sizeInBytes == -1) { //不修改圖片
			updateCourse(course);
		}
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(course);
	}
	
	public void updateCourse(CourseBean course) {
		Session session = factory.getCurrentSession();
		System.out.println(course.getCourseId());
		CourseBean cb = session.get(CourseBean.class, course.getCourseId());
		course.setCoverImage(cb.getCoverImage());
		course.setFileName(cb.getFileName());
		course.setProductImage(cb.getProductImage());
		
        session.evict(cb);
        session.saveOrUpdate(course);
        
	}

	@Override
	public List<CourseBean> findCourseByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CourseBean> courseSearchByKC(String keyword, String cate, String cdate) {
		String hql = "from CourseBean c where c.status=true ";
		if(!keyword.isEmpty()) {
			hql += " and c.title like :title";
		}
		if(!cate.equals("不限種類")) {
			hql += " and c.category= :category";
		}
//		Date date = null;
		if(!cdate.equals("請選擇")) {
			hql+=" and c.date= :date";
//			
//			try {
//			    date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(selectDate);
//			} catch (Exception e) {
//			    
//			}
		}
		Session session = factory.getCurrentSession();
		Query q = session.createQuery(hql + " order by c.starttime");
		if(!keyword.isEmpty()) {
			q.setParameter("title", "%" + keyword + "%");
		}
		if(!cate.equals("不限種類")) {
			q.setParameter("category", cate);
		}
		
		if(!cdate.equals("請選擇")) {
			q.setParameter("date", cdate);
		}

		List list = q.getResultList();
//		for(Object i : list) {
//			CourseBean a = (CourseBean)i;
//			a.setCoverImage(null);
//		}
		return list;
	}


	@Override
	public void changeAllCoursesStatus() {
		String hql = "update CourseBean c set c.status=false where c.starttime < :start";
		Session session = factory.getCurrentSession();
		Timestamp ts = new Timestamp(System.currentTimeMillis()); 
//		System.out.println(ts);
		session.createQuery(hql).setParameter("start", ts).executeUpdate();
	}


	@Override
	public List<CourseInfoBean> getMyNowCourses(String userid) {
		String hql = "from CourseInfoBean ci where ci.m_id=:mid and ci.c_start <= :now and :now <= ci.c_end and status=true order by c_start";
		Session session = factory.getCurrentSession();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		return session.createQuery(hql).setParameter("mid", userid).setParameter("now", ts).getResultList();
	}


	@Override
	public List<CourseInfoBean> getMyFinishedCourses(String userid) {
		String hql = "from CourseInfoBean ci where ci.m_id=:mid and ci.c_end < :now and status=true order by c_start";
		Session session = factory.getCurrentSession();
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		return session.createQuery(hql).setParameter("mid", userid).setParameter("now", ts).getResultList();
	}


	@Override
	public List<CourseBean> getSelect() {
		String hql = "select distinct date from CourseBean where status=true";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}


	@Override
	public void updateCourseStatus(CourseBean course) {
		String hql = "update CourseBean c set c.status =:status where c.courseId=:id";
		Session session = factory.getCurrentSession();
		session.createQuery(hql).setParameter("status", course.getStatus())
								.setParameter("id", course.getCourseId()).executeUpdate();
	}


	@Override
	public List<CourseBean> getAllCoursesAdmin() {
		String hql = "from CourseBean c order by c.starttime";
		Session session = factory.getCurrentSession();
		List list = session.createQuery(hql).getResultList();
		return list;
	}


	@Override
	public List<CourseBean> courseSearchByKCAdmin(String keyword, String cate, String cdate) {
		String hql = "from CourseBean c where (1=1) ";
		if(!keyword.isEmpty()) {
			hql += " and c.title like :title";
		}
		if(!cate.equals("不限種類")) {
			hql += " and c.category= :category";
		}
//		Date date = null;
		if(!cdate.equals("請選擇")) {
			hql+=" and c.date= :date";
//			
//			try {
//			    date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(selectDate);
//			} catch (Exception e) {
//			    
//			}
		}
		Session session = factory.getCurrentSession();
		Query q = session.createQuery(hql + " order by c.starttime");
		if(!keyword.isEmpty()) {
			q.setParameter("title", "%" + keyword + "%");
		}
		if(!cate.equals("不限種類")) {
			q.setParameter("category", cate);
		}
		
		if(!cdate.equals("請選擇")) {
			q.setParameter("date", cdate);
		}

		List list = q.getResultList();
//		for(Object i : list) {
//			CourseBean a = (CourseBean)i;
//			a.setCoverImage(null);
//		}
		return list;
	}


	@Override
	public List<CourseBean> getSelectAdmin() {
		String hql = "select distinct date from CourseBean";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String> checkRoomTime(String date,Timestamp chStart, Timestamp chEnd) {
		String hql = "select distinct location from CourseBean c where c.date=:date and c.starttime < :et and c.endtime > :st";
		Session session = factory.getCurrentSession();
		List<String> list = session.createQuery(hql).setParameter("st", chStart)
								.setParameter("et", chEnd)
								.setParameter("date", date)
//								.setParameter("location", course.getLocation())
								.getResultList();
		List<String> rlist = new LinkedList<String>();
		rlist.add("B001");
		rlist.add("B002");
		rlist.add("B003");
		rlist.add("B004");
		rlist.add("B005");
//		String[] clist = (String[]) list.toArray();	
//		String[] roomlist = {"B001", "B002", "B003", "B004", "B005"};
		List<String> alist = new LinkedList<String>();
		for(String i : list) {
			alist.add(i);
		}
		rlist.removeAll(alist);
		System.out.println(rlist);
		
		return rlist;
	}




	

}
