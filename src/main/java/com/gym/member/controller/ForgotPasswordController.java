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
public class ForgotPasswordController {
	
	@GetMapping("/forgotPassword")
	public String login() {
		return "member/forgotPassword";	
	}

	@Autowired
	private MemberService memberService;

	// 忘記密碼
	@RequestMapping(path = "/forgotPassword", method = RequestMethod.POST)
	protected void forgotPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 接收資料
		String member_id = request.getParameter("member_id");

		// 驗證資料
//		String path = request.getContextPath();// 專案路徑
//		HttpSession session = request.getSession();// session物件儲存跨頁資訊
		Map<String, String> errors = new HashMap<String, String>();// 儲存錯誤訊息
		request.setAttribute("errors", errors);// 有錯誤訊息回原頁

		if (member_id == null || member_id.length() == 0) {
			errors.put("accountx", "請輸入帳號");
			request.getRequestDispatcher("/WEB-INF/views/member/forgotPassword.jsp").forward(request, response);
			return;
		}
		
		// 轉換資料(無)
		// 呼叫Model
		String systemMessage;
		MemberBean memberbean = memberService.selectByMember_id(member_id);
		if (memberbean == null) {
			errors.put("accountx", "查無此會員帳號");
			request.getRequestDispatcher("/WEB-INF/views/member/forgotPassword.jsp").forward(request, response);
			return;
		}else {
			systemMessage = memberService.forgotPwd(member_id);			
		}

		// 根據Model執行結果呼叫View
		if (systemMessage == null) {
			errors.put("failedToSend", "發送密碼信件失敗");
			request.getRequestDispatcher("/WEB-INF/views/member/forgotPassword.jsp").forward(request, response);
		} else {
			errors.put("email2youOK", "已發送email到您的電子信箱");
			request.getRequestDispatcher("/WEB-INF/views/member/forgotPassword.jsp").forward(request, response);
		}
	}
}
