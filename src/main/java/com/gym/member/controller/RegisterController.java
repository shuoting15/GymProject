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

import com.gym.member.common.CreatePassword;
import com.gym.member.common.GmailSendMail;
import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;

@Controller
@RequestMapping
public class RegisterController {
	
	@GetMapping("/register")
	public String register() {
		return "member/register";	
	}
	
	@GetMapping("/registerResult")
	public String registerResult() {
		return "member/registerResult";
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
		CreatePassword numberMaker = new CreatePassword();// 產生亂數
		String registerNum = numberMaker.getRandomPassword();
		System.out.println("registerNum:" + registerNum);
		MemberBean newMember = memberService.createNewMember(member_id, password, registerNum);//註冊

		// 根據Model執行結果呼叫View
		if (newMember == null) {
			errors.put("accountexist", "此帳號已存在");
			request.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(request, response);
			return;
		} else {
			// 寄驗證信
			GmailSendMail gmailSendMail = new GmailSendMail();
			String emailOrNot = gmailSendMail.sendRegisterNumber(member_id, registerNum);
			errors.put("successYN", emailOrNot);
			request.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(request, response);
			return;
		} 
	}
	
	// 會員驗證
		@GetMapping("/memberActivate")
		public void memberActivate(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// 接收資料
			String member_id = request.getParameter("who");// 帳號from信件
			String registerNum = request.getParameter("mm");// 註冊碼from信件
			System.out.println("待驗證帳號:" + member_id);
			System.out.println("註冊碼(待驗帳號):" + registerNum);

			// 呼叫Model
			String path = request.getContextPath();// 專案路徑
			HttpSession session = request.getSession();// session物件儲存跨頁資訊

			MemberBean memberbean = memberService.selectByMember_id(member_id);
			if (memberbean == null) {
				session.setAttribute("message", "註冊失敗:此帳號不存在");
				response.sendRedirect(path + "/registerResult");
				return;
			} else {
				int activateStatus = memberbean.getActivate();// 啟用狀態
				String register_num = memberbean.getRegister_num();// 註冊碼From資料庫
				System.out.println("原啟用狀態:" + activateStatus);
				System.out.println("註冊碼(資料庫)" + register_num);
				if (activateStatus == 1) {
					session.setAttribute("message", "此帳號之前已完成註冊");
					response.sendRedirect(path + "/registerResult");
					return;
				} else {
					System.out.println("registerNum:" + registerNum);
					System.out.println("register_num:" + register_num);
					if (registerNum.equals(register_num)) {
						boolean ans = memberService.activateMember(member_id);
						System.out.println("現在啟用狀態:" + ans);
						session.setAttribute("message", "已完成註冊和驗證♥ 恭喜您加入我們! 請重新登入網站，謝謝您!");
						response.sendRedirect(path + "/registerResult");
						return;
					} else {
						session.setAttribute("message", "註冊失敗:驗證錯誤，請重新驗證");
						response.sendRedirect(path + "/registerResults");
						return;
					}
				}
			}
		}
}
