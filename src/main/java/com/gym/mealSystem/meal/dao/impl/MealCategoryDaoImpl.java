package com.gym.mealSystem.meal.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.mealSystem.meal.dao.MealCategoryDao;
import com.gym.mealSystem.meal.model.MealCategoryBean;



@Repository
public class MealCategoryDaoImpl implements MealCategoryDao {

	@Autowired
	SessionFactory factory;
	
	public Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<MealCategoryBean> getAllMealCategorys() {
		String hql = "FROM MealCategoryBean";
		Session session = getSession();
		return session.createQuery(hql).getResultList();
	}
	
	@Override
	public MealCategoryBean getMealCategoryBeanName(String name) {
		Session session = getSession();
		return session.get(MealCategoryBean.class, name);
	}
	

	@Override
	public MealCategoryBean getMealCategoryBean(Integer id) {
		Session session = getSession();
		return session.get(MealCategoryBean.class, id);
	}
	
	
	
	

}
