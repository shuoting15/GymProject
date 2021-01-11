package com.gym.coach.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.coach.dao.CoachOrderDao;
import com.gym.coach.model.ClanderBean;
import com.gym.coach.model.CoachOrderBean;
@Repository
public class CoachOrderDaoImpl implements CoachOrderDao {
	@Autowired
	SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")
	@Override
//	 hh:mm:ss
	public List<CoachOrderBean> findTimeByCoachId() {
		List<CoachOrderBean> list = null;
//		String hql = "SELECT ob.orderTitle as title,"
//				+ "CONVERT(varchar(100), orderStartTime, 20) as start,"
//				+ "CONVERT(varchar(100), orderEndTime, 20) as end FROM CoachOrderBean ob WHERE coachId = 8 ";
		String hql = "SELECT ob.orderTitle as title,"
			    + " ob.orderStartTime as start,"
			    + " ob.orderEndTime as end FROM CoachOrderBean ob WHERE coachId = 8 ";
		Session session = sessionFactory.getCurrentSession();
		list = 	session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(ClanderBean.class))
			.list();
		return list;
	}

}
