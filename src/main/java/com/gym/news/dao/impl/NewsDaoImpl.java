package com.gym.news.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gym.coach.model.CoachBean;
import com.gym.member.model.MemberBean;
import com.gym.message.model.MessageBean;
import com.gym.news.dao.NewsDao;
import com.gym.news.model.NewsBean;
import com.gym.news.model.NewsPlaylistBean;

//import com.web.store.exception.ProductNotFoundException;


@Repository
public class NewsDaoImpl implements NewsDao {
	@Autowired
	SessionFactory factory;
	
		
	@Override
	@SuppressWarnings("unchecked")
	public List<NewsBean> getAllNews() {
		Session session=factory.getCurrentSession();
		String hql="FROM NewsBean order by NewsUploadTime desc";
	
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
		String hql = "FROM NewsBean bb WHERE bb.newsCategory = :newsCategory  order by NewsUploadTime desc";
	    List<NewsBean> list = new ArrayList<>();
	    Session session = factory.getCurrentSession();
	    list = session.createQuery(hql).setParameter("newsCategory", newsCategory).getResultList();
	    return list;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<NewsBean> getNewsByViews() {
		String hql = "FROM NewsBean order by newsViews desc";
	    List<NewsBean> list = new ArrayList<>();
	    Session session = factory.getCurrentSession();
	    list = session.createQuery(hql).getResultList();
	    return list;
	}
	
	@Override
	public NewsBean getNewsById(int newsproductId) {
		Session session = factory.getCurrentSession();
		NewsBean bb = session.get(NewsBean.class, newsproductId);
//		if (bb == null) {
//			throw new ProductNotFoundException("產品編號：" + newsproductId + "找不到");
//		}
		
//		int nv=bb.getNewsViews();
//		nv++;
//		bb.setNewsViews(nv);
//		System.out.println(bb.getNewsViews() +"&&&"+nv);
//		session.update(bb);
//		
		return bb;
	}


	@Override
	public void addNewsone(NewsBean newsone) {
	    Session session = factory.getCurrentSession();
	    CoachBean cb = getAuthorById(newsone.getAuthorId());
	    newsone.setCoachBean(cb);
	    session.save(newsone);
	}
	
	
	
	@Override
	public CoachBean getAuthorById(int authorId) {
	    CoachBean cb = null;
	    Session session = factory.getCurrentSession();
	    cb = session.get(CoachBean.class, authorId);
	    return cb;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CoachBean> getAuthorList() {
	    String hql = "FROM CoachBean";
	    Session session = factory.getCurrentSession();
	    List<CoachBean> list = session.createQuery(hql).getResultList();
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
		NewsBean bb = session.get(NewsBean.class, newsId);
		session.delete(bb);
	}	
//	@Override
//	public void deleteCustomerByPrimaryKey(int key) {
//		Session session = factory.getCurrentSession();
//		CustomerBean customer = new CustomerBean();
//		customer.setCustomerId(key);
//		session.delete(customer);
//	}

	@Override
	public void update(NewsBean newsbean) {
		Session session = factory.getCurrentSession();
		session.update(newsbean);
	}
	



	@Override
	public List<NewsBean> getSearchNews(String newskw) {
		String hql="from NewsBean nb where (1=1)";
		if(!newskw.isEmpty()) {
			hql +=" and nb.newsTitle like :newsTitle";
		}
		
		Session session=factory.getCurrentSession();
		Query q=session.createQuery(hql);
		if(!newskw.isEmpty()) {
			q.setParameter("newsTitle", "%"+newskw+"%");
		}
		
		List list=q.getResultList();
		
		return list;
	}



	@Override
	public void saveintoplayliste(NewsPlaylistBean nb) {
		Session session = factory.getCurrentSession();
	    session.save(nb);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<NewsBean> getplaylistNews(String memberid) {
		Session session=factory.getCurrentSession();
		String hql="from NewsBean where newsId in (select FK_NewsBean_newsId from NewsPlaylistBean where member_id = :memberid)";
		return session.createQuery(hql).setParameter("memberid", memberid).getResultList();
			}
	
//	@Override
//	public List<NewsBean> getSearchPlaylist(String playlist) {
//		String hql="from NewsPlaylistBean nb where (1=1)";
//		if(!newskw.isEmpty()) {
//			hql +=" and nb.news like :newsTitle";
//		}
//		
//		Session session=factory.getCurrentSession();
//		Query q=session.createQuery(hql);
//		if(!newskw.isEmpty()) {
//			q.setParameter("newsTitle", "%"+playlist+"%");
//		}
//		
//		List list=q.getResultList();
//		
//		return list;
//	}
//	
//	@Transactional
//	@Override
//	public List<MessageBean> SearchmessageById(String keyword){
//		String hql="from MessageBean mb where (1=1)";
//		if(!keyword.isEmpty()) {
//			hql +=" and mb.title like :title";
//		}
//		
//		Session session=factory.getCurrentSession();
//		Query q=session.createQuery(hql);
//		if(!keyword.isEmpty()) {
//			q.setParameter("title", "%"+keyword+"%");
//		}
//		
//		List list=q.getResultList();
////		for(Object i: list) {
////			MessageBean m=(MessageBean)i;
////		}
//		
//		return list;
//	}
}
