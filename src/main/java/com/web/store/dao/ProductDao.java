package com.web.store.dao;

import java.util.List;

import com.web.store.model.BookBean;
import com.web.store.model.CompanyBean;

public interface ProductDao {
		List<BookBean> getAllProducts();
		void updateAllStock(int productId,int newQuentity);
		List<String> getAllCategories();
		List<BookBean> getProductByCategory(String category);
		BookBean getProductById(int productId);
		void addProduct(BookBean product);
		CompanyBean getCompanyById(int companyId);
		List<CompanyBean> getCompanyList();
}
