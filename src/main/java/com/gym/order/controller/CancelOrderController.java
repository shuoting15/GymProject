package com.gym.order.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
@SessionAttributes({"ShoppingCart"})
public class CancelOrderController {
	@Autowired
	ServletContext context;
	
	@GetMapping("cancelOrder")
	protected String cancelOrder(Model model, 
			WebRequest webRequest, SessionStatus status
			) {
		// 如果使用者取消訂單
		status.setComplete();
//		webRequest.removeAttribute("ShoppingCart", WebRequest.SCOPE_SESSION);
		return  "redirect:/";
	}

}
