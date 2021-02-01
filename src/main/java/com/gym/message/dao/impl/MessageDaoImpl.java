package com.gym.message.dao.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.message.dao.MessageDao;
import com.gym.message.exception.ProductNotFoundException;
import com.gym.message.model.MessageBean;

@Repository
public class MessageDaoImpl implements MessageDao {

	@Autowired
	SessionFactory factory;

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageBean> getAllMessage() {
		Session session = factory.getCurrentSession();
		String hql = "From MessageBean order by repliseCount desc";
		return session.createQuery(hql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllKanbanName() {
		String hql = "SELECT DISTINCT mb.kanbanName FROM MessageBean mb";
		Session session = factory.getCurrentSession();
		List<String> list = new ArrayList<>();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public MessageBean getMessageById(int articleId) {
		Session session = factory.getCurrentSession();
		MessageBean mb = session.get(MessageBean.class, articleId);
		if (mb == null) {
			throw new ProductNotFoundException(articleId + "找不到");
		}
		return mb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageBean> getMessageByKanbanName(String kanbanName) {
		String hql = "FROM MessageBean mb WHERE mb.kanbanName = :kanbanName";
		List<MessageBean> list = new ArrayList<>();
		Session session = factory.getCurrentSession();
		list = session.createQuery(hql).setParameter("kanbanName", kanbanName).getResultList();
		return list;
	}

	@Override
	public void addMessage(MessageBean message) {
		Session session = factory.getCurrentSession();
		session.save(message);
	}

	@Override
	public MessageBean getKanbannameById(int articleId) {
		Session session = factory.getCurrentSession();
		return session.get(MessageBean.class, articleId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageBean> getKanbannameList() {
		String hql = "FROM MessageBean";
		Session session = factory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@Override
	public int deleteMessage(int no) {
		int n = 0;
		Session session = factory.getCurrentSession();
		MessageBean mb = new MessageBean();
		mb.setArticleId(no);
		session.delete(mb);
		System.out.println("刪除囉!");
		n++;
		return n;
	}

	@Override
	public int updateMessage(MessageBean mbean, long sizeInBytes) {
		int n = 0;

		if (sizeInBytes == -1) { // 不修改圖片
			n = updateMessage(mbean);
			return n;
		}
		Session session = factory.getCurrentSession();
		session.update(mbean);
		n++;

		return n;
	}

	public int updateMessage(MessageBean mbean) {
		int n = 0;
		MessageBean b0 = null;
		Session session = factory.getCurrentSession();
		b0 = session.get(MessageBean.class, mbean.getArticleId());
//        bean.setStock(b0.getStock());
//        bean.setPriceStr(b0.getPriceStr());
		mbean.setImages(b0.getImages());
		mbean.setFileName(b0.getFileName());
		session.evict(b0);
		session.update(mbean);
		n++;
		return n;
	}
	@Transactional
	@Override
	public List<MessageBean> SearchmessageById(String keyword){
		String hql="from MessageBean mb where (1=1)";
		if(!keyword.isEmpty()) {
			hql +=" and mb.title like :title";
		}
		
		Session session=factory.getCurrentSession();
		Query q=session.createQuery(hql);
		if(!keyword.isEmpty()) {
			q.setParameter("title", "%"+keyword+"%");
		}
		
		List list=q.getResultList();
//		for(Object i: list) {
//			MessageBean m=(MessageBean)i;
//		}
		
		return list;
	}

	@Override
	public MessageBean getMemberidById(int articleId) {
		Session session = factory.getCurrentSession();
		MessageBean mb = session.get(MessageBean.class, articleId);
		if (mb == null) {
			throw new ProductNotFoundException(articleId + "找不到");
		}
		return mb;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MessageBean> getAllMessageByTime() {
		Session session = factory.getCurrentSession();
		String hql = "From MessageBean order by time desc";
		return session.createQuery(hql).getResultList();
		
	}

	
	
}
