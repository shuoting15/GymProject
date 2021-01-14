package com.gym.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;

@Controller
@RequestMapping
public class RegisterController {
	
	@GetMapping("/register")
	public String register() {
		return "member/register";	
	}
	
	@Autowired
	private MemberService memberService;
	
	//會員註冊
	@RequestMapping(path = "/register", method=RequestMethod.POST)
	protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 接收資料
		String member_id = request.getParameter("member_id");
		String password = request.getParameter("password");

		// 驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);

		if (member_id == null || member_id.length() == 0) {
			errors.put("accountx", "請輸入帳號");
		}
		if (password == null || password.length() == 0) {
			errors.put("passwordx", "請輸入密碼");
		}
		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(request, response);
			return;
		}		
		
		// 轉換資料(無)
		// 呼叫Model
		MemberBean newMember = memberService.createNewMember(member_id, password);//註冊

		// 根據Model執行結果呼叫View
		if (newMember == null) {
			errors.put("accountexist", "此帳號已存在");
			request.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(request, response);
		} else {
			errors.put("success", "恭喜您加入我們! 請重新登入網站，謝謝您!");
			request.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(request, response);
		} 
	}
}
