package com.gym.message.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.message.dao.MailboxDao;
import com.gym.message.exception.ProductNotFoundException;
import com.gym.message.model.MailboxBean;

@Repository
public class MailboxDaoImpl implements MailboxDao {

	@Autowired
	SessionFactory factory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MailboxBean> getAllMailboxContent() {
		Session session = factory.getCurrentSession();
		String hql = "From MailboxBean";
		return session.createQuery(hql).getResultList();
	}

	@Override
	public MailboxBean getMailboxContentById(int mailboxId) {
		Session session = factory.getCurrentSession();
		MailboxBean mb = session.get(MailboxBean.class, mailboxId);
		if (mb == null) {
			throw new ProductNotFoundException(mailboxId + "找不到");
		}
		return mb;
	}

	@Override
	public void addMailbox(MailboxBean mailbox) {
		

		Session session = factory.getCurrentSession();
		session.save(mailbox);
	}

	@Override
	public int deleteMailbox(int no) {
		int n = 0;
		Session session = factory.getCurrentSession();
		MailboxBean mb = new MailboxBean();
		mb.setMailboxId(no);
		session.delete(mb);
		n++;
		return n;
	}

	@Override
	public int updateMailbox(MailboxBean mb) {
		int n = 0;
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(mb);
		n++;

		return n;
	}

	
	public int updateMailbox1(MailboxBean mbean) {
		int n = 0;
		MailboxBean b0 = null;
		Session session = factory.getCurrentSession();
		b0 = session.get(MailboxBean.class, mbean.getMailboxId());
//        bean.setStock(b0.getStock());
//        bean.setPriceStr(b0.getPriceStr());
		mbean.setImages(b0.getImages());
		mbean.setFileName(b0.getFileName());
		session.evict(b0);
		session.saveOrUpdate(mbean);
		n++;
		return n;
	}
	
	@Override
	public void UpdateArticleId(MailboxBean mb) {
		Session session=factory.getCurrentSession();
		session.update(mb);
	}
	
	@Override
	public void addMailboxContent(String mb) {
		Session session = factory.getCurrentSession();
		
		MailboxBean mb2 = new MailboxBean(mb);
		
		session.save(mb2);
	}

	


	
	
}
