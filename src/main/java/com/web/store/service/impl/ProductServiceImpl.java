package com.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.store.dao.ProductDao;
import com.web.store.model.BookBean;
import com.web.store.model.CompanyBean;
import com.web.store.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao dao;

	public ProductServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Transactional
	@Override
	public List<BookBean> getAllProducts() {
		return dao.getAllProducts();
	}
	@Transactional
	@Override
	public void updateAllStock() {
		List<BookBean> list = dao.getAllProducts();
		for (BookBean list1 : list) {
			if (list1.getStock() < 50 && list1.getStock() != null) {
				dao.updateAllStock(list1.getBookId(), 50);
			}
		}

	}
	@Transactional
	@Override
	public List<String> getAllCategories() {
		// TODO Auto-generated method stub
		return dao.getAllCategories();
	}
	@Transactional
	@Override
	public List<BookBean> getProductByCategory(String category) {
		// TODO Auto-generated method stub
		return dao.getProductByCategory(category);
	}
	@Transactional
	@Override
	public BookBean getProductById(int productId) {
		// TODO Auto-generated method stub
		return dao.getProductById(productId);
	}
	@Transactional
	@Override
	public void addProduct(BookBean product) {
		dao.addProduct(product);
		
	}
	@Transactional
	@Override
	public CompanyBean getCompanyById(int companyId) {
		// TODO Auto-generated method stub
		return dao.getCompanyById(companyId);
	}
	@Transactional
	@Override
	public List<CompanyBean> getCompanyList() {
		// TODO Auto-generated method stub
		return dao.getCompanyList();
	}

}
