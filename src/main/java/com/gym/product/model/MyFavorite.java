package com.gym.product.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MyFavorite {
	private Map<Integer, ProductBean> favorite = new LinkedHashMap< >();
	
	public MyFavorite() {}
	
	public Map<Integer, ProductBean>  getContent() { // ${ShoppingCart.content}
		return favorite;
	}

	public String addToFavorite(int productId, ProductBean  pb) {
		// 如果客戶在伺服器端沒有此項商品的資料，則客戶第一次購買此項商品
		if ( favorite.get(productId) == null ) {
			favorite.put(productId,pb);
			return "已加入收藏";
		}
		return "已在收藏清單中";
	}
	
	// 刪除某項商品
	public int deleteProduct(int productId) {
		if ( favorite.get(productId) != null ) {
		   favorite.remove(productId);  // Map介面的remove()方法
	       return 1;
		} else {
		   return 0;
		}
	}
	public int getItemNumber(){   // ShoppingCart.itemNumber
		return favorite.size();
	}
	

}

