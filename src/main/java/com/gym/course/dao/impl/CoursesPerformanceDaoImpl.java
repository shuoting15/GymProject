package com.gym.course.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.course.dao.CoursesPerformanceDao;

@Repository
public class CoursesPerformanceDaoImpl implements CoursesPerformanceDao {

	@Autowired
	SessionFactory factory;

	@Override
	public List getAllCategories() {
		String hql = "from CourseCategoryBean";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@Override
	public int totalCategoryRevenue(String category) {
		String hql = "select sum(ci.c_price) from CourseInfoBean ci where ci.c_category = :category and ci.c_end < getdate()";
		Session session = factory.getCurrentSession();
		Object result = session.createQuery(hql).setParameter("category", category).getSingleResult();
		if(result != null) {
			return (int)(long)result;
		}
		return 0;
	}

	@Override
	public int monthCategoryRevenue(String category) {
		String hql = "select sum(ci.c_price) from CourseInfoBean ci where ci.c_category = :category and ci.c_end < getdate() and datediff(mm,c_date,getdate())=0";
		Session session = factory.getCurrentSession();
		Object result = session.createQuery(hql).setParameter("category", category).getSingleResult();
		if(result != null) {
			return (int)(long)result;
		}
		return 0;
	}

	@Override
	public int totalAllCategoriesRevenue() {
		String hql = "select sum(ci.c_price) from CourseInfoBean ci where ci.c_end < getdate()";
		Session session = factory.getCurrentSession();
		Object result = session.createQuery(hql).getSingleResult();
		if(result != null) {
			return (int)(long)result;
		}
		return 0;
	}

	@Override
	public int monthAllCategoriesRevenue() {
		String hql = "select sum(ci.c_price) from CourseInfoBean ci where ci.c_end < getdate() and datediff(mm,c_date,getdate())=0";
		Session session = factory.getCurrentSession();
		Object result = session.createQuery(hql).getSingleResult();
		if(result != null) {
			return (int)(long)result;
		}
		return 0;
	}

}
