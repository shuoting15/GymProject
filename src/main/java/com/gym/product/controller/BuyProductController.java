package com.gym.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gym.product.service.IProductService;



//當使用者按下『加入購物車』時，瀏覽器會送出請求到本程式
@Controller
@SessionAttributes({ "LoginOK","pageNo", "products", "ShoppingCart" }) 
public class BuyProductController {
	
	@Autowired
	IProductService productService;

	@SuppressWarnings("unchecked")
	//@PostMapping("/buy")  
	public String buyProduct(Model model, @RequestParam("productId") Integer productId,
			@RequestParam("qty") Integer qty,RedirectAttributes ra,HttpServletRequest request) {
		// =======之後整合============
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null) {
//			return "redirect:/_02_login/login";
//		}
		
		HttpSession session = request.getSession(false);
		if (session == null) {
			return "redirect:/_02_login/login";
		}

		// 取出存放在session物件內的ShoppingCart物件
		ShoppingCart cart = (ShoppingCart) model.getAttribute("ShoppingCart");
		// 如果找不到ShoppingCart物件
		if (cart == null) {
			// 就新建ShoppingCart物件
			cart = new ShoppingCart();
			// 並將此新建ShoppingCart的物件放到session物件內，成為它的屬性物件
			model.addAttribute("ShoppingCart", cart);
		}

		Map<Integer, ProductBean> productMap = (Map<Integer, ProductBean>) session.getAttribute("products");
		ProductBean bean = productMap.get(productId);
		String pageNo = request.getParameter("pageNo");
		if (pageNo == null || pageNo.trim().length() == 0) {
			pageNo = (String) model.getAttribute("pageNo");
			if (pageNo == null) {
				pageNo = "1";
			}
		}

		// 將訂單資料(價格，數量，折扣與BookBean)封裝到OrderItemBean物件內
		OrderItemBean oib = new OrderItemBean(null, productId, qty, bean.getProductPrice(),
				bean.getDiscount(), bean.getProductName());
		// 將OrderItem物件內加入ShoppingCart的物件內
		int newqty;
		Integer stock=productService.getStockById(productId);
		if(cart.getContent().get(productId)!=null)	{
			newqty=cart.getContent().get(productId).getQuantity()+qty;
		}else {
			newqty=qty;
		}
		
		if(newqty>stock) {
			ra.addFlashAttribute("outOfStockMsg", "庫存僅餘"+stock+"，請重新調整數量！");
		}else {
			cart.addToCart(productId, oib);
		}
		
		return "redirect:/productDisplay/productAll"; //在master加入購物車，再回到master
	}
	
	@GetMapping("/buy")  
		public @ResponseBody Map<String,Object> buyProduct2(Model model, @RequestParam("productId") Integer productId,
				@RequestParam("qty") Integer qty,HttpServletRequest request) {
			// =======之後整合============
//			MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//			if (memberBean == null) {
//				return "redirect:/_02_login/login";
//			}
			
			HttpSession session = request.getSession(false);
//			if (session == null) {
//				return "redirect:/_02_login/login";
//			}

			// 取出存放在session物件內的ShoppingCart物件
			ShoppingCart cart = (ShoppingCart) model.getAttribute("ShoppingCart");
			// 如果找不到ShoppingCart物件
			if (cart == null) {
				// 就新建ShoppingCart物件
				cart = new ShoppingCart();
				// 並將此新建ShoppingCart的物件放到session物件內，成為它的屬性物件
				model.addAttribute("ShoppingCart", cart);
			}
			

			@SuppressWarnings("unchecked")
			Map<Integer, ProductBean> productMap = (Map<Integer, ProductBean>) session.getAttribute("products");
			ProductBean bean = productMap.get(productId);
			String pageNo = request.getParameter("pageNo");
			if (pageNo == null || pageNo.trim().length() == 0) {
				pageNo = (String) model.getAttribute("pageNo");
				if (pageNo == null) {
					pageNo = "1";
				}
			}
			ProductBean bean2 =productService.getProductById(productId);
			System.out.println(productId);
			System.out.println(qty);
			System.out.println(bean2.getProductPrice());
			System.out.println(bean2.getDiscount());
			System.out.println(bean2.getProductName());
			
			// 將訂單資料(價格，數量，折扣與BookBean)封裝到OrderItemBean物件內
			OrderItemBean oib = new OrderItemBean(null, productId, qty, bean2.getProductPrice(),
					bean2.getDiscount(), bean2.getProductName());
			
			System.out.println(oib);
			// 將OrderItem物件內加入ShoppingCart的物件內
			int newqty;
			Integer stock=productService.getStockById(productId);
			if(cart.getContent().get(productId)!=null)	{
				newqty=cart.getContent().get(productId).getQuantity()+qty;
			}else {
				newqty=qty;
			}
			
			
			Map<String,Object> map= new HashMap<String,Object>();
			if(newqty>stock) {			
				map.put("msg","庫存僅餘"+stock+"，請重新調整數量！");				
			}else {
				cart.addToCart(productId, oib);
				map.put("msg","已成功加入購物車！");
				map.put("cartContent",String. valueOf(cart.getItemNumber()));
				map.put("cartSubtotal",String. valueOf(cart.getSubtotal()));
				map.put("cart",cart.getContent());
			}
			return map;		
		}

}
