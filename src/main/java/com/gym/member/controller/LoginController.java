package com.gym.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;


@Controller
@RequestMapping
@SessionAttributes({"LoginOK"}) 
public class LoginController{
	
	@GetMapping("/login")
	public String login() {
		return "member/login";	
	}

	@Autowired
	private MemberService memberService;
	
	//會員登入
	@RequestMapping(path = "/login", method=RequestMethod.POST)
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 接收資料
		String member_id = request.getParameter("member_id");
		String password = request.getParameter("password");

		// 驗證資料
		String path = request.getContextPath();//專案路徑
		HttpSession session = request.getSession();//session物件儲存跨頁資訊
		Map<String, String> errors = new HashMap<String, String>();//儲存錯誤訊息
		request.setAttribute("errors", errors);
					
		if (member_id == null || member_id.length() == 0) {
			errors.put("accountx", "請輸入帳號密碼");
		}
		if (password == null || password.length() == 0) {
			errors.put("accountx", "請輸入帳號密碼");
		}
        
		if (errors != null && !errors.isEmpty()) { //有錯誤訊息回原頁
			request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
			return;
		}

		// 轉換資料(無)
		// 呼叫Model
		MemberBean memberbean = memberService.login(member_id, password);
		if (memberbean == null) {// 根據Model執行結果呼叫View
			errors.put("loginx", "登入失敗，請重新輸入帳號密碼");
			request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
		} else {
			session.setAttribute("LoginOK", memberbean);
			session.setAttribute("member_id", memberbean.getMember_id());
			session.setAttribute("password", memberbean.getPassword());
			session.setAttribute("username", memberbean.getUsername());
			session.setAttribute("member_type", memberbean.getMember_type());
			session.setAttribute("mobile",memberbean.getMobile());
			session.setAttribute("gender",memberbean.getGender());
			session.setAttribute("birth",memberbean.getBirth());
			session.setAttribute("address",memberbean.getAddress());
			session.setAttribute("point",memberbean.getPoint());
			session.setAttribute("facebook_account",memberbean.getFacebook_account());
			session.setAttribute("google_account",memberbean.getGoogle_account());			
			session.setAttribute("detail",memberbean.getDetail());
			session.setAttribute("memberphoto", memberbean.getMemberphoto());
			
			//TODO 路徑要確認
			response.sendRedirect(path);
		}
	}
}
