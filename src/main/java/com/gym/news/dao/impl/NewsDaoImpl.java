package com.gym.news.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.news.dao.NewsDao;
import com.gym.news.model.AuthorBean;
import com.gym.news.model.NewsBean;

//import com.web.store.exception.ProductNotFoundException;


@Repository
public class NewsDaoImpl implements NewsDao {
	@Autowired
	SessionFactory factory;
	
		
	@Override
	@SuppressWarnings("unchecked")
	public List<NewsBean> getAllNews() {
		Session session=factory.getCurrentSession();
		String hql="FROM NewsBean";
		return session.createQuery(hql).getResultList();
	}
	


	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllNewsCategories() {
		  String hql = "SELECT DISTINCT b.newsCategory FROM NewsBean b";
		    Session session = factory.getCurrentSession();
		    List<String> list = new ArrayList<>();
		    list = session.createQuery(hql).getResultList();
		    return list;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<NewsBean> getNewsByCategory(String newsCategory) {
		String hql = "FROM NewsBean bb WHERE bb.newsCategory = :newsCategory";
	    List<NewsBean> list = new ArrayList<>();
	    Session session = factory.getCurrentSession();
	    list = session.createQuery(hql).setParameter("newsCategory", newsCategory).getResultList();
	    return list;
	}


	@Override
	public NewsBean getNewsById(int newsproductId) {
		Session session = factory.getCurrentSession();
		NewsBean bb = session.get(NewsBean.class, newsproductId);
//		if (bb == null) {
//			throw new ProductNotFoundException("產品編號：" + newsproductId + "找不到");
//		}
		return bb;
	}


	@Override
	public void addNewsone(NewsBean newsone) {
	    Session session = factory.getCurrentSession();
	    AuthorBean cb = getAuthorById(newsone.getAuthorId());
	    newsone.setAuthorBean(cb);
	    session.save(newsone);
	}
	
	
	
	@Override
	public AuthorBean getAuthorById(int authorId) {
	    AuthorBean cb = null;
	    Session session = factory.getCurrentSession();
	    cb = session.get(AuthorBean.class, authorId);
	    return cb;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<AuthorBean> getAuthorList() {
	    String hql = "FROM AuthorBean";
	    Session session = factory.getCurrentSession();
	    List<AuthorBean> list = session.createQuery(hql).getResultList();
	    return list;
	}
	
//	@Override
//	public void updateCustomer(CustomerBean bean) {
//		Session session = factory.getCurrentSession();
//		session.update(bean);
//		
//	}
//
	
	
	@Override
	public void deleteNewsById(int newsId) {
		Session session = factory.getCurrentSession();
		NewsBean bb = new NewsBean();
		bb.setNewsId(newsId);
		session.delete(bb);
	}	
//	@Override
//	public void deleteCustomerByPrimaryKey(int key) {
//		Session session = factory.getCurrentSession();
//		CustomerBean customer = new CustomerBean();
//		customer.setCustomerId(key);
//		session.delete(customer);
//	}
	
}
