package com.gym.coupon.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gym.coupon.model.CouponBean;
import com.gym.coupon.service.ICouponService;
import com.gym.shoppingcart.model.ShoppingCart;



@Controller
@SessionAttributes({ "LoginOK","pageNo", "products", "ShoppingCart" }) 
public class CouponController {
	@Autowired
	ICouponService couponService;
	
	@GetMapping(value="/coupon",produces={"application/json; charset=UTF-8"})
	public @ResponseBody Map<String, String> coupon(Model model,@RequestParam String code){
		ShoppingCart cart = (ShoppingCart) model.getAttribute("ShoppingCart");
		double subtotal = cart.getSubtotal();
		String totalStr = Double.toString(subtotal);

		boolean a = couponService.checkCode(code);

		CouponBean cBean = couponService.selectByCode(code);
		
		Map<String, String> map = new HashMap<>();
		if (cBean != null) {
			try {
				int cdn = Integer.parseInt(cBean.getCondition());
				if (a != false && subtotal > cdn) {   //判斷消費金額是否符合
					double discode = 0;
					discode = Double.parseDouble(cBean.getAmount());  //取得折扣的金額
						
					Double newtotal = cart.getSubtotal()-discode;  
					model.addAttribute("newsubtotal", newtotal);  
					model.addAttribute("discode", discode);
					
					
					String discodeStr = Double.toString(discode);
					String newtotalStr = Double.toString(newtotal);
					
					map.put("newTotal", newtotalStr);
					map.put("codeMsg", "折抵成功");
					map.put("amount", discodeStr);

				} else {
					map.put("codeMsg", "消費金額不足");
					map.put("amount", "0");
					map.put("newTotal",totalStr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else {
			map.put("codeMsg", "折扣碼不存在");
			map.put("amount", "0");
			map.put("newTotal",totalStr);
		}
		return map;
	}
	
	

}
