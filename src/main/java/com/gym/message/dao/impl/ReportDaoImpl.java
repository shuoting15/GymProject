package com.gym.message.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.message.dao.ReportDao;
import com.gym.message.model.MailboxBean;
import com.gym.message.model.ReportBean;
@Repository
public class ReportDaoImpl implements ReportDao {

	@Autowired
	SessionFactory factory;
	
	@Override
	public void addReportContent(String rb) {
		Session session = factory.getCurrentSession();
		ReportBean rb1 = new ReportBean(rb);
		session.save(rb1);
	}

	@Override
	public int deleteMessageReport(int no) {
		int n = 0;
		Session session = factory.getCurrentSession();
		MailboxBean mb = new MailboxBean();
		mb.setMailboxId(no);
		session.delete(mb);
		n++;
		return n;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<ReportBean> getAllReport() {
		Session session = factory.getCurrentSession();
		String hql = "From ReportBean";
		return session.createQuery(hql).getResultList();
	}

}
