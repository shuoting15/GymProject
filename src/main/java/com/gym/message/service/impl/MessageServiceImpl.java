package com.gym.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.gym.message.dao.MessageDao;
import com.gym.message.model.MessageBean;
import com.gym.message.service.MessageService;
@Service
@EnableTransactionManagement
public class MessageServiceImpl implements MessageService {

	@Autowired
	MessageDao mdao;
	
	
	@Transactional
	@Override
	public List<MessageBean> getAllMessage() {
	
		return mdao.getAllMessage();
	}

	@Transactional
	@Override
	public List<String> getAllKanbanName() {
		return mdao.getAllKanbanName();
	}

	@Transactional
	@Override
	public List<MessageBean> getMessageByKanbanName(String kanbanName) {
		return mdao.getMessageByKanbanName(kanbanName);
	}
	@Transactional
	@Override
	public MessageBean getMessageById(int articleId) {
		return mdao.getMessageById(articleId);
	}
	
	@Transactional
	@Override
	public void addMessage(MessageBean message) {
		mdao.addMessage(message);
		
	}
	@Transactional
	@Override
	public MessageBean getKanbannameById(int articleId) {
		// TODO Auto-generated method stub
		return mdao.getKanbannameById(articleId);
	}
	@Transactional
	@Override
	public List<MessageBean> getKanbannameList() {
		// TODO Auto-generated method stub
		return mdao.getKanbannameList();
	}
	
	@Transactional
	@Override
	public int deleteMessage(int no) {
		int n = 0;
//        Session session = factory.getCurrentSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
            n = mdao.deleteMessage(no);
            System.out.println("123123");
//            tx.commit();
//        } catch (Exception ex) {
//            if (tx != null)  
//            	tx.rollback();
//            ex.printStackTrace();
//            throw new RuntimeException(ex);
//        }
        return n;

	}
	
	@Transactional
	@Override
	public int updateMessage(MessageBean mbean, long sizeInBytes) {
		int n = 0;
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
//		try {
//		    tx = session.beginTransaction();
		    n = mdao.updateMessage(mbean, sizeInBytes);
//		    tx.commit();
//		} catch (Exception ex) {
//		    if (tx != null)  tx.rollback();
//		    ex.printStackTrace();
//		    throw new RuntimeException(ex);
//		}
		return n;

	}

	@Override
	public List<MessageBean> SearchmessageById(String keyword) {
		
		return mdao.SearchmessageById(keyword);
	}

	
	
}
