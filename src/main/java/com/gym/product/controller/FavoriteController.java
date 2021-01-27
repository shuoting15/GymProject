package com.gym.product.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gym.product.model.MyFavorite;
import com.gym.product.model.ProductBean;
import com.gym.product.service.IProductService;

@Controller
@SessionAttributes({ "LoginOK", "ShoppingCart", "MyFavorite" })
public class FavoriteController {

	@Autowired
	IProductService productService;

	@GetMapping("/showFavorite")
	protected String showCartContent(Model model, SessionStatus status) {
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null) {
//			status.setComplete();
//			return "redirect:/login/login";
//		}

	
		MyFavorite favorite = (MyFavorite) model.getAttribute("MyFavorite");
		if (favorite == null) {
			model.addAttribute("noFavMsg", "目前還沒有收藏喔！趕緊買起來～");
		}
		return "product/myFavorite";
	}

	@GetMapping(value="/addFavorite")
	public @ResponseBody List<String> buyProduct(Model model, @RequestParam("productId") Integer productId, RedirectAttributes ra,
			HttpServletRequest request) {
		// =======之後整合============
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null) {
//			return "redirect:/_02_login/login";
//		}

		HttpSession session = request.getSession(false);
//		if (session == null) {
//			return "redirect:/_02_login/login";
//		}

		// 取出存放在session物件內的ShoppingCart物件
		MyFavorite favorite = (MyFavorite) model.getAttribute("MyFavorite");
		
		if (favorite == null) {
			favorite = new MyFavorite();
			model.addAttribute("MyFavorite", favorite);
		}
		
		List<String> msglst= new ArrayList<String>();
		ProductBean pb = productService.getProductById(productId);
		String msg = favorite.addToFavorite(productId, pb);
		msglst.add(msg);
		msglst.add(String. valueOf(favorite.getItemNumber()));	
		return msglst;
	}
	
	
	@PostMapping("/deleteFavorite")
	protected String UpdateItem(
			@RequestParam(value = "productId", required = false) Integer productId,
			Model model,  RedirectAttributes ra,
			HttpSession session, SessionStatus status) {
		MyFavorite f = (MyFavorite) model.getAttribute("MyFavorite");
		f.deleteProduct(productId); 
		return "product/myFavorite";
	}

}
