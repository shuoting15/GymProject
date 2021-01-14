package com.gym.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.google.gson.Gson;
import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;

@Controller
@RequestMapping
public class MemberIdCheckAjaxController {

	@Autowired
	private MemberService memberService;

	@RequestMapping(path = "/memberCheckAjex", method = RequestMethod.POST)
	protected void memberIdCheckAjax(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接
		request.setCharacterEncoding("UTF-8");
		String member_id = request.getParameter("member_id");
		System.out.println("Ajax:欲註冊帳號="+member_id);
		//回應
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = "";//回應文字
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<>();//回應文字Map
		
		if (member_id != null && member_id.trim().length() != 0) {
			try {
				MemberBean bean = memberService.selectByMember_id(member_id);//查資料庫有無此資料
				if(bean==null) {//查無資料
					map.put("id",id);//放空字串
					System.out.println("Ajax:註冊帳號:資料庫查無資料");
				}else {//已有資料
					id=bean.getMember_id();
					map.put("id", id);
					System.out.println("Ajax:註冊帳號:資料庫已有資料");
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}else {
			map.put("id", "Error:註冊帳號沒填喔");
		}
		out.println(gson.toJson(map));
		out.close();
	}
}
