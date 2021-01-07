package com.web.store.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.store.dao.ProductDao;
import com.web.store.model.BookBean;
import com.web.store.model.CompanyBean;
@Repository
public class ProductDaoImpl implements ProductDao {
	@Autowired
	SessionFactory sessionFactory;
	public ProductDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookBean> getAllProducts() {
		String hql = "FROM BookBean";
		Session session = sessionFactory.getCurrentSession();
		List<BookBean> list = new ArrayList<BookBean>();
		list = session.createQuery(hql).getResultList();
		return list;
	}

	@Override
	public void updateAllStock(int productId, int newQuentity) {
		String hql = "UPDATE BookBean m SET m.stock = m.stock+:newQuentity  WHERE m.bookId = :productId";
		Session session = sessionFactory.getCurrentSession();
		session.createQuery(hql)
		.setParameter("newQuentity", newQuentity)
		.setParameter("productId", productId)
		.executeUpdate();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllCategories() {
		String hql = "SELECT DISTINCT b.category FROM BookBean b";
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookBean> getProductByCategory(String category) {
		String hql = "FROM BookBean WHERE category = :category";
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(hql)
		.setParameter("category", category)
		.getResultList();
	}

	@Override
	public BookBean getProductById(int productId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(BookBean.class, productId);
	}

	@Override
	public void addProduct(BookBean product) {
		Session session = sessionFactory.getCurrentSession();
		CompanyBean cb = getCompanyById(product.getCompanyId());
		product.setCompanyBean(cb);
		session.save(product);
		
	}

	@Override
	public CompanyBean getCompanyById(int companyId) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(CompanyBean.class, companyId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyBean> getCompanyList() {
		String hql ="FROM CompanyBean";
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery(hql).getResultList();
	}

}
