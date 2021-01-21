package com.gym.shoppingcart.dao.impl;

import java.sql.Connection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gym.shoppingcart.dao.IOrderItemDao;
import com.gym.shoppingcart.model.OrderItemBean;



@Repository
public class OrderItemDao implements IOrderItemDao {
    
	@Autowired
	SessionFactory factory;
	
	public OrderItemDao() {}
	
	//計算客戶欲購買之某項商品(以OrderItemBean物件oib來表示)的小計金額(subtotal)，
	//計算公式為: 商品的數量 * 商品的單價 ** 商品的折扣
	public double findItemAmount(OrderItemBean oib) {
		double subtotal = oib.getQuantity() * oib.getUnitPrice() * oib.getDiscount();
		return subtotal;
	}
	
	//檢查訂購之商品的數量是否足夠。
	@Override 
	public int updateProductStock(OrderItemBean oib) {
		int n = 0;
		Integer stock = 0;
		Session session = factory.getCurrentSession();
		String hql0 = "SELECT pb.productInStock FROM ProductBean pb WHERE pb.productId = :Id";
		String hql1 = "UPDATE ProductBean SET productInStock = productInStock - :orderAmount WHERE productId = :Id";
		stock = (Integer) session.createQuery(hql0)
								 .setParameter("Id", oib.getProductId())
								 .getSingleResult();
		if (stock == null) {
			stock = 0;
		}
		int stockLeft = stock - oib.getQuantity();
		if (stockLeft < 0) {
			System.out.println("庫存數量不足: ProductId: " + oib.getProductId() + ", 在庫量: " 
				    + stock + ", 訂購量: " + oib.getQuantity());
//			throw new ProductStockException(
//					"庫存數量不足: ProductId: " + oib.getProductId() + ", 在庫量: " 
//				    + stock + ", 訂購量: " + oib.getQuantity());
		}
		n = session.createQuery(hql1)
				   .setParameter("Id", oib.getProductId())
				   .setParameter("orderAmount", oib.getQuantity())
				   .executeUpdate();
		return n;
	}
//	@Override
//	public void setConnection(Connection conn) {
//		throw new RuntimeException("本類別未實作此方法");
//	}


}
