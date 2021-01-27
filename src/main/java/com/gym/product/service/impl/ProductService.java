package com.gym.product.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.gym.product.dao.IProductDao;
import com.gym.product.model.ProductBean;
import com.gym.product.service.IProductService;

@Service
@EnableTransactionManagement
public class ProductService implements IProductService {
    @Autowired
	IProductDao productDao;

	@Override @Transactional
	public void insertProduct(ProductBean pBean) {
		productDao.insertProduct(pBean);
		
	}

	@Override @Transactional
	public void updateProduct(ProductBean pBean,long sizeInBytes) {
		productDao.updateProduct(pBean,sizeInBytes);
		
	}

	@Override  @Transactional
	public void deleteProduct(int id) {
		productDao.deleteProduct(id);
		
	}

	@Override  @Transactional
	public ProductBean getProductById(int productId) {
		return productDao.getProductById(productId);
	}

	@Override  @Transactional
	public List<ProductBean> queryFuzzyProduct(String keyword) {
		return productDao.queryFuzzyProduct(keyword);
	}

	@Override  @Transactional
	public List<ProductBean> queryAllProduct() {
		return productDao.queryAllProduct();
	}

	@Override  @Transactional
	public List<String> getCategoryList() {
		return productDao.getCategoryList();
	}

	@Override  @Transactional
	public Map<Integer, ProductBean> getProductsByCategory(String cate) {
		return productDao.getProductsByCategory(cate);
	}

	@Override  @Transactional
	public long getRecordCounts() {
		return productDao.getRecordCounts();
	}

	@Override  @Transactional
	public Map<Integer, ProductBean> getPageProducts(int pageNo) {
		return productDao.getPageProducts(pageNo);
	}
	@Override  @Transactional
	public  List<ProductBean> getPageProductList(int pageNo){
		return productDao.getPageProductList(pageNo);
	}

	@Override  @Transactional
	public int getTotalPages() {
		return productDao.getTotalPages();
	}

	@Override  @Transactional
	public List<ProductBean> getProductsByPriceAsc() {
		return productDao.getProductsByPriceAsc();
	}
	
	@Override @Transactional
	public List<ProductBean> getProductsByPriceDesc() {
		return productDao.getProductsByPriceDesc();
	}

	@Override  @Transactional
	public List<ProductBean> queryNewestProducts() {
		return productDao.queryNewestProducts();
	}

	@Override  @Transactional
	public Integer getStockById(Integer id) {
		return productDao.getStockById(id);
	}

	@Override @Transactional
	public List<ProductBean> getProductsByIdDesc() {
		return productDao.getProductsByIdDesc();
	}

	@Override @Transactional
	public List<ProductBean> filterProductsByPriceDesc(Double min, Double max) {
		return productDao.filterProductsByPriceDesc(min, max);
	}

	

	
	

}
