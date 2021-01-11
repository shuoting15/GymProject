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
	public List<ClanderBean> findTimeByCoachId(int coachId) {
		List<ClanderBean> list = null;
//		String hql = "SELECT ob.orderTitle as title,"
//				+ "CONVERT(varchar(100), orderStartTime, 20) as start,"
//				+ "CONVERT(varchar(100), orderEndTime, 20) as end FROM CoachOrderBean ob WHERE coachId = 8 ";
		String hql = "SELECT ob.orderTitle as title,"
			    + " ob.orderStartTime as start,"
			    + " ob.orderEndTime as end,"
			    + " ob.coachId as groupId  FROM CoachOrderBean ob WHERE coachId = :coachId";
		System.out.println("這是dao裡面的教練id ："+coachId);
		Session session = sessionFactory.getCurrentSession();
		list = 	session.createQuery(hql).setParameter("coachId", coachId).setResultTransformer(Transformers.aliasToBean(ClanderBean.class))
			.list();
		return list;
	}

}
