package com.gym.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.gym.message.dao.MailboxDao;
import com.gym.message.model.MailboxBean;
import com.gym.message.service.MailboxService;

@Service
@EnableTransactionManagement
public class MailboxServiceImpl implements MailboxService {

	@Autowired
	MailboxDao mbdao;

	@Transactional
	@Override
	public List<MailboxBean> getAllMailboxContent() {

		return mbdao.getAllMailboxContent();
	}

	@Transactional
	@Override
	public MailboxBean getMailboxContentById(int mailboxId) {

		return mbdao.getMailboxContentById(mailboxId);
	}

	@Transactional
	@Override
	public void addMailbox(MailboxBean mailbox) {
		mbdao.addMailbox(mailbox);
	}

	@Transactional
	@Override
	public int deleteMailbox(int no) {
		int n = 0;
//      Session session = factory.getCurrentSession();
//      Transaction tx = null;
//      try {
//          tx = session.beginTransaction();
		n = mbdao.deleteMailbox(no);
//          tx.commit();
//      } catch (Exception ex) {
//          if (tx != null)  
//          	tx.rollback();
//          ex.printStackTrace();
//          throw new RuntimeException(ex);
//      }
		return n;
	}

	@Transactional
	@Override
	public int updateMailbox(MailboxBean mb) {
		int n = 0;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//		    tx = session.beginTransaction();
		    n = mbdao.updateMailbox(mb);
//		    tx.commit();
//		} catch (Exception ex) {
//		    if (tx != null)  tx.rollback();
//		    ex.printStackTrace();
//		    throw new RuntimeException(ex);
//		}
		return n;
	}
	@Transactional
	@Override
	public void addMailboxContent(String mb) {
		mbdao.addMailboxContent(mb);
	}
	
	@Transactional
	@Override
	public void UpdateArticleId(MailboxBean mb) {
		mbdao.UpdateArticleId(mb);

	}
	
	
}
