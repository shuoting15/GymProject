package com.gym.product.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.gym.product.dao.IProductDao;
import com.gym.product.model.ProductBean;


@Repository
public class ProductDao implements IProductDao {
	@Autowired
	SessionFactory factory;
	
	//=============pageNo=================================
	private int recordsPerPage =9;
	private int totalPages = -1;
	
		@Override
		public int getTotalPages() {
			// 注意下一列敘述的每一個型態轉換
			totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
			return totalPages;
		}
		@SuppressWarnings("unchecked")
		@Override
		public Map<Integer, ProductBean> getPageProducts(int pageNo) {
			Map<Integer, ProductBean> map = new LinkedHashMap<>();
			String hql = "FROM ProductBean";
	        Session session = factory.getCurrentSession();
	        int startRecordNo = (pageNo - 1) * recordsPerPage;
	        List<ProductBean> list = session.createQuery(hql)
	                      .setFirstResult(startRecordNo)
	                      .setMaxResults(recordsPerPage)
	                      .getResultList();
			for(ProductBean bean: list) {
				map.put(bean.getProductId(), bean);
			}
			return map;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public List<ProductBean> getPageProductList(int pageNo) {
			String hql = "FROM ProductBean";
	        Session session = factory.getCurrentSession();
	        int startRecordNo = (pageNo - 1) * recordsPerPage;
	        List<ProductBean> list = session.createQuery(hql)
	                      .setFirstResult(startRecordNo)
	                      .setMaxResults(recordsPerPage)
	                      .getResultList();
	        
			return list;
		}

		@Override
		public long getRecordCounts() {
			long count = 0; // 必須使用 long 型態
			String hql1 = "SELECT count(*) FROM ProductBean";
			String hql2 = "SELECT count(*) FROM ProductBean pb where pb.productCategory=:cate";
			Session session = factory.getCurrentSession();
			//if(condition=="all") {
				count = (Long)session.createQuery(hql1).getSingleResult();
			//}else {
				//count = (Long)session.createQuery(hql2).setParameter("cate", condition).getSingleResult();
			//}
			return count;
		}
	
		
	//============新刪修============================================ 
	@Override  
	public void insertProduct(ProductBean pBean) {
		Session session = factory.getCurrentSession();
		session.save(pBean);
	}
	
	@Override  
	public void updateProduct(ProductBean pBean,long sizeInBytes){
		if (sizeInBytes == -1) { // 不修改圖片
            updateProduct(pBean);
        }		
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(pBean);
	}
	
	public void updateProduct(ProductBean bean) {
        ProductBean b0 = null;
        Session session = factory.getCurrentSession();
        b0 = session.get(ProductBean.class, bean.getProductId());
//        bean.setStock(b0.getStock());
//        bean.setPriceStr(b0.getPriceStr());
        bean.setCoverImage(b0.getCoverImage());
        bean.setFileName(b0.getFileName());
        session.evict(b0);
        session.saveOrUpdate(bean);
	}
	
	@Override 
	public void deleteProduct(int id){
		Session session = factory.getCurrentSession();
		ProductBean bean = new ProductBean();
		bean.setProductId(id);
		session.delete(bean);
	}
	
	
	//====================Query=====================================
	@Override   
	public ProductBean getProductById(int productId) {
		Session session = factory.getCurrentSession();
		ProductBean pBean = session.get(ProductBean.class, productId);
		return pBean;
	}
	
	@Override   
	public List<String> getCategoryList() {
		Session session = factory.getCurrentSession();
		String hql ="select distinct pb.productCategory From ProductBean pb ";
		return session.createQuery(hql).getResultList();
	}
	
	@Override
	public Map<Integer, ProductBean> getProductsByCategory(String cate){
		Session session = factory.getCurrentSession();
		Map<Integer, ProductBean> map = new LinkedHashMap<>();
		String hql = "From ProductBean as pb Where pb.productCategory=:cate";
		List<ProductBean> pBeanLst = session.createQuery(hql).setParameter("cate",cate).getResultList();
		for(ProductBean bean: pBeanLst) {
			map.put(bean.getProductId(), bean);
		}
		return map;
//		return pBeanLst;	
	}	
	
	//sortBy
	@Override
	public List<ProductBean> getProductsByPriceAsc(){
		Session session = factory.getCurrentSession();
		Map<Integer, ProductBean> map = new LinkedHashMap<>();
		String hql = "From ProductBean as pb ORDER BY pb.productPrice ASC";
		List<ProductBean> pBeanLst = session.createQuery(hql).getResultList();
//		for(ProductBean bean: pBeanLst) {
//			map.put(bean.getProductId(), bean);
//		}
//		return map;
		return pBeanLst;	
	}	
	@Override
	public List<ProductBean> getProductsByIdDesc(){
		Session session = factory.getCurrentSession();
		Map<Integer, ProductBean> map = new LinkedHashMap<>();
		String hql = "From ProductBean as pb ORDER BY pb.productId DESC";
		List<ProductBean> pBeanLst = session.createQuery(hql).getResultList();
//		for(ProductBean bean: pBeanLst) {
//			map.put(bean.getProductId(), bean);
//		}
//		return map;
		return pBeanLst;	
	}
	
	@Override
	public List<ProductBean> getProductsByPriceDesc(){
		Session session = factory.getCurrentSession();
		Map<Integer, ProductBean> map = new LinkedHashMap<>();
		String hql = "From ProductBean as pb ORDER BY pb.productPrice DESC";
		List<ProductBean> pBeanLst = session.createQuery(hql).getResultList();
//		for(ProductBean bean: pBeanLst) {
//			map.put(bean.getProductId(), bean);
//		}
//		return map;
		return pBeanLst;	
	}
	
	@Override  
	public List<ProductBean> queryNewestProducts() {
		Session session = factory.getCurrentSession();
		String hql = "From ProductBean as pb ORDER BY pb.productId DESC";
		List<ProductBean> pBeanLst = session.createQuery(hql).setFirstResult(1).setMaxResults(6).getResultList();
		return pBeanLst;
	}
	
	@SuppressWarnings("unchecked")
	@Override  
	public List<ProductBean> queryFuzzyProduct(String keyword) {
		Session session = factory.getCurrentSession();
		String hql = "From ProductBean as pb Where pb.productName like :kw";
		List<ProductBean> pBeanLst = session.createQuery(hql).setParameter("kw","%"+keyword+"%").getResultList();
		return pBeanLst;
	}
	

	@Override   
	public List<ProductBean> queryAllProduct(){
		Session session = factory.getCurrentSession();
		String hql = "From ProductBean";
		@SuppressWarnings("unchecked")
		List<ProductBean> pBeanLst = session.createQuery(hql).getResultList();
		return pBeanLst;		
	}	
	@Override  
	public Integer getStockById(Integer id) {
		Session session = factory.getCurrentSession();
		String hql0 = "SELECT pb.productInStock FROM ProductBean pb WHERE pb.productId = :Id";
		Integer stock = (Integer) session.createQuery(hql0)
				 .setParameter("Id", id)
				 .getSingleResult();
		return stock;
	}


}

