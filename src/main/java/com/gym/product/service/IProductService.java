package com.gym.product.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gym.product.model.ProductBean;


public interface IProductService {
	    //新增 
		//圖片和日期型別待修改
		void insertProduct(ProductBean pBean);

		//修改(看要不要回傳modal訊息)
		void updateProduct(ProductBean pBean,long sizeInBytes);

		//刪除某項商品
		void deleteProduct(int id);

		//====================Query=====================================
		//用productId取得一筆資料=>為了修改一筆資料，先搜出該本書的資料給修改
		ProductBean getProductById(int productId);
		
		//取得所有分類名稱
		List<String> getCategoryList();
		//分類查詢顯示商品
		Map<Integer, ProductBean> getProductsByCategory(String cate);
		//sortBy
		List<ProductBean> getProductsByPriceAsc();
		List<ProductBean> getProductsByPriceDesc();
		List<ProductBean> getProductsByIdDesc();
		//latest top 6
		List<ProductBean> queryNewestProducts();
		
		public List<ProductBean> filterProductsByPriceDesc(Double min,Double max);

		//商品模糊搜尋
		List<ProductBean> queryFuzzyProduct(String keyword);

		//後台編輯陳列所有商品資訊
		List<ProductBean> queryAllProduct();
		
		Integer getStockById(Integer id);
		
		//=================PageNo=====================================
		long getRecordCounts();
		Map<Integer, ProductBean> getPageProducts(int pageNo);
		List<ProductBean> getPageProductList(int pageNo);
		int getTotalPages();
	
}
