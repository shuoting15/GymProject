package com.gym.shoppingcart.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ShoppingCart {
	private Map<Integer, OrderItemBean> cart = new LinkedHashMap< >();
	
	public ShoppingCart() {}
	
	public Map<Integer, OrderItemBean>  getContent() { // ${ShoppingCart.content}
		return cart;
	}
	
	public void addToCart(int productId, OrderItemBean  oib) {
		if (oib.getQuantity() <= 0 ) {
			return;
		}
		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
		if ( cart.get(productId) == null ) {
		    cart.put(productId, oib);
		} else {
			OrderItemBean oiBean = cart.get(productId);		
			oiBean.setQuantity(oib.getQuantity() + oiBean.getQuantity());
		}
	}
	
	public boolean modifyQty(int productId, int newQty) {
		if ( cart.get(productId) != null ) {
		   OrderItemBean  bean = cart.get(productId);
		   bean.setQuantity(newQty);
	       return true;
		} else {
		   return false;
		}
	}
	
	public int deleteProduct(int productId) {
		if ( cart.get(productId) != null ) {
	       cart.remove(productId);  
	       return 1;
		} else {
		   return 0;
		}
	}
	public int getItemNumber(){   // ShoppingCart.itemNumber
		return cart.size();
	}
	
	public double getSubtotal(){
		double subTotal = 0 ;
		Set<Integer> set = cart.keySet();
		for(int n : set){
			OrderItemBean oib = cart.get(n);
			double price    = oib.getUnitPrice();
			double discount = oib.getDiscount();
			int qty      = oib.getQuantity();
			subTotal +=  price * discount * qty;
		}
		return subTotal;
	}

}

