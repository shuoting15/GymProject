package com.gym.shoppingcart.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gym.product.service.IProductService;
import com.gym.shoppingcart.model.ShoppingCart;



@Controller
@RequestMapping("shoppingCart")
@SessionAttributes({ "LoginOK", "ShoppingCart" })
public class ShoppingCartEditController {

	@Autowired
	ServletContext context;
	
	@Autowired
	IProductService productService;

	@GetMapping("showCartContent")
	protected String showCartContent(Model model, SessionStatus status) {
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null) {
//			status.setComplete();
//			return "redirect:/login/login";
//		}
		return "shoppingcart/showCartContent";
	}

	@GetMapping("checkout")
	protected String checkout(Model model, SessionStatus status) {
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null) {
//			status.setComplete();
//			return "redirect:/_02_login/login";
//		}
		return "shoppingcart/orderConfirm";
	}

	@PostMapping("UpdateItem")
	protected String UpdateItem(@RequestParam("cmd") String cmd,
			@RequestParam(value = "productId", required = false) Integer productId,
			@RequestParam(value = "newQty", required = false) Integer newQty, Model model, 
			RedirectAttributes ra,
			HttpSession session, SessionStatus status) {
		
		ShoppingCart sc = (ShoppingCart) model.getAttribute("ShoppingCart");
		if (sc == null) {
			status.setComplete();
			return "redirect:/_02_login/login";
		}
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
////		memberBean = null;     // 此敘述測試用
//		if (memberBean == null) {
//			status.setComplete();
//			return "redirect:/_02_login/login";
//		}
		
		
		if (cmd.equalsIgnoreCase("DEL")) {
			sc.deleteProduct(productId); 
			return "shoppingcart/showCartContent";
		} else if (cmd.equalsIgnoreCase("MOD")) {
			Integer stock=productService.getStockById(productId);
			if(newQty>stock) {
				model.addAttribute("errorMsg","庫存數量不足，剩餘 : "+stock+" ，請重新調整訂購量 !");
				return "shoppingcart/showCartContent";
			}
			sc.modifyQty(productId, newQty); 
			return "shoppingcart/showCartContent";
		} else {
			return "shoppingcart/showCartContent";
		}
	}
//	
//	@GetMapping
//	public void updateQty(
//			@RequestParam(value = "productId", required = false) Integer productId,
//			@RequestParam(value = "newQty", required = false) Integer newQty, Model model) {
//		ShoppingCart sc = (ShoppingCart) model.getAttribute("ShoppingCart");
//		sc.modifyQty(productId, newQty);
//	}
	
}
