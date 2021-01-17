package com.gym.coach.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.coach.dao.CoachDao;
import com.gym.coach.model.CoachBean;
@Repository
public class CoachDaoImpl implements CoachDao {
	@Autowired
	SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CoachBean> getAllCoachs() {
		String hql = "FROM CoachBean";
		Session session = sessionFactory.getCurrentSession();
		List<CoachBean> list = new ArrayList<CoachBean>();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public CoachBean getCoachById(int coachId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(CoachBean.class, coachId);
	}

	@Override
	public void addCoach(CoachBean coach) {
		Session session = sessionFactory.getCurrentSession();
		session.save(coach);

	}

	@Override
	public int updateCoach(CoachBean bean, long sizeInBytes) {
		int n = 0;
		if (sizeInBytes == -1) { // 不修改圖片
            n = updateCoach(bean);
            return n;
        }
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(bean);
        n++;

		return n;
	}
	public int updateCoach(CoachBean bean) {
		int n = 0;
		CoachBean b0 = null;
        Session session = sessionFactory.getCurrentSession();
        b0 = session.get(CoachBean.class, bean.getCoachId());
        bean.setCoachRating(b0.getCoachRating());
        bean.setCoachPhoto(b0.getCoachPhoto());
        bean.setFileName(b0.getFileName());
        session.evict(b0);
        session.saveOrUpdate(bean);
        n++;
        return n;
	}

	@Override
	public int deleteCoach(int coachId) {
		int n = 0;
        Session session = sessionFactory.getCurrentSession();
        CoachBean bb = new CoachBean();
        bb.setCoachId(coachId);
        session.delete(bb);
        n++;
        return n;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllExpertise() {
		String hql = "SELECT DISTINCT coachExpertise FROM CoachBean";
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}


	@Override
	public void updateCoachRating(CoachBean coachBean) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(coachBean);
	}
}
