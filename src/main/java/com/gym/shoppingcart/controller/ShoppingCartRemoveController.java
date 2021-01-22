package com.gym.shoppingcart.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("shoppingCart")
// 由於要執行status.setComplete();來移除Session範圍的ShoppingCart物件，所以
// @SessionAttributes({ "ShoppingCart" }) 只能單讀編寫該物件的識別字串。
@SessionAttributes({"ShoppingCart","orderNo","memberId"})
public class ShoppingCartRemoveController {
	
	@Autowired
	ServletContext context;
	@RequestMapping("removeShoppingCart")         
	public String removeCart(Model model, SessionStatus status) {
		status.setComplete();
		
		return "shoppingcart/thanksForOrdering";
	}

}
