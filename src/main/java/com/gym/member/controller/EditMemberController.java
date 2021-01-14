package com.gym.member.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;

@Controller
@RequestMapping
public class EditMemberController {
	
	@GetMapping("/editmember")
	public String editmember() {
		return "member/editmember";	
	}

	@Autowired
	private MemberService memberService;

	// 會員修改
	@RequestMapping(path = "/editmember", method = RequestMethod.POST)
	protected void editMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 接收資料
		String member_id = request.getParameter("member_id");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String mobile = request.getParameter("mobile");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String address = request.getParameter("address");
		String facebook_account = request.getParameter("facebook_account");
		String google_account = request.getParameter("google_account");
		String detail = request.getParameter("detail");

		Map<String, String> errors = new HashMap<String, String>();// 儲存錯誤訊息
		request.setAttribute("errors", errors);
		// 驗證資料
		// 轉換資料(為了存回資料庫)
		int mOrF = Integer.parseInt(gender);// 轉字串為數字

		Date sqlDate = null;// 轉字串為java.sql.Date
		java.util.Date bday = null;
		if (birth != null && birth.length() != 0) {
			try {
				bday = sdFormat.parse(birth);
				sqlDate = new java.sql.Date(bday.getTime());// 要
			} catch (ParseException e) {
				e.printStackTrace();
				errors.put("birthx", "生日格式為YYYY/MM/DD");
			}
		}

		if (password == null || password.length() == 0) {
			errors.put("passwordx", "請填寫密碼");
		}

		if (errors != null && !errors.isEmpty()) {
			request.getRequestDispatcher("/WEB-INF/views/member/editmember.jsp").forward(request, response);
			return;
		}

		// 呼叫Model
		memberService.selectByMember_id(member_id);
		MemberBean newinfo = memberService.updateMemberInfo(member_id, password, username, mobile, mOrF, sqlDate,
				address, facebook_account, google_account, detail);

		//根據Model執行結果呼叫View
		if (newinfo == null) {
			errors.put("updatex", "更新失敗,請照格式填寫");
			request.getRequestDispatcher("/WEB-INF/views/member/editmember.jsp").forward(request, response);
		} else {//更新成功:將登入時session存的會員資料更新
			HttpSession session = request.getSession();
			session.setAttribute("member_id", newinfo.getMember_id());
			session.setAttribute("password", newinfo.getPassword());
			session.setAttribute("username", newinfo.getUsername());
			session.setAttribute("mobile", newinfo.getMobile());
			session.setAttribute("gender", newinfo.getGender());
			session.setAttribute("birth", newinfo.getBirth());
			session.setAttribute("address", newinfo.getAddress());
			session.setAttribute("facebook_account", newinfo.getFacebook_account());
			session.setAttribute("google_account", newinfo.getGoogle_account());
			session.setAttribute("detail", newinfo.getDetail());

			errors.put("updateok", "更新成功");
			//TODO 路徑要確認
			request.getRequestDispatcher("/WEB-INF/views/member/editmember.jsp").forward(request, response);

		}
	}

}
