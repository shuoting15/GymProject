package com.gym.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping
//@SessionAttributes({"LoginOK"})
public class LogoutController {
		
		//會員登出	
		@RequestMapping(path = "/logout", method=RequestMethod.GET)
		protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		String path = request.getContextPath();
		HttpSession session = request.getSession();
		Object LoginOK = request.getSession().getAttribute("LoginOK");
		System.out.println("LoginOK:"+LoginOK);
		if (LoginOK != null) {//TODO 路徑要確認
			session.invalidate();//刪除session
			System.out.println("登出囉!");
			response.sendRedirect(path);
			
		} else {//TODO 路徑要確認
			System.out.println("尚未登入so沒做登出");
			response.sendRedirect(path);

		}
	}
}
