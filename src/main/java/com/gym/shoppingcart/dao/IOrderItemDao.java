package com.gym.shoppingcart.dao;

import com.gym.shoppingcart.model.OrderItemBean;

public interface IOrderItemDao {
	//某項商品(以OrderItemBean物件oib來表示)的小計金額(subtotal)
	public double findItemAmount(OrderItemBean oib);
	//檢查訂購之商品的數量是否足夠。
	int updateProductStock(OrderItemBean oib);

}
