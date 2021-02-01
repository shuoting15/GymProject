package com.gym.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.member.common.CreatePassword;
import com.gym.member.common.HttpUtil;
import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;

@Controller
@RequestMapping("/account/github")
public class GitHubLoginController{
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String githubLogin() {
        String githubState = "adgasgdsdhgi";
        return "redirect:https://github.com/login/oauth/authorize?client_id=cda077776c5d2e05af27&redirect_uri=http://localhost:8080/GymProject/account/github/callback&state=" + githubState;
    }
	
	@RequestMapping(value = "/callback", method = RequestMethod.GET)
    public void githubCallback(String code, String state, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("==>state:" + state);
        System.out.println("==>code:" + code);
        
        String path = request.getContextPath();//專案路徑
        
        HttpSession session = request.getSession();//session物件儲存跨頁資訊
        
        String githubAccessTokenResult = HttpUtil.sendGet("https://github.com/login/oauth/access_token",
                "client_id=cda077776c5d2e05af27&client_secret=1189c838cab577361ad8ee932ec1453c775f8de4&code=" + code +
                        "&redirect_uri=http://localhost:8080/GymProject/account/github/callback");

        System.out.println("==>githubAccessTokenResult: " + githubAccessTokenResult);
        
        memberService.createGithubMember("eeit12128@outlook.com", "eeit1219453", 1);//註冊
        
        MemberBean bean = memberService.login("eeit12128@outlook.com", "eeit1219453");
        
        session.setAttribute("LoginOK", bean);
		session.setAttribute("member_id", bean.getMember_id());
		session.setAttribute("password", bean.getPassword());
		session.setAttribute("username", bean.getUsername());
		session.setAttribute("member_type", bean.getMember_type());
		session.setAttribute("member_height", bean.getMember_height());
		session.setAttribute("member_weight", bean.getMember_weight());
		session.setAttribute("mobile",bean.getMobile());
		session.setAttribute("gender",bean.getGender());
		session.setAttribute("birth",bean.getBirth());
		session.setAttribute("address",bean.getAddress());
		session.setAttribute("point",bean.getPoint());
		session.setAttribute("facebook_account",bean.getFacebook_account());
		session.setAttribute("google_account",bean.getGoogle_account());			
		session.setAttribute("detail",bean.getDetail());
		session.setAttribute("memberphoto", bean.getMemberphoto());
		session.setAttribute("activate", bean.getActivate());
		session.setAttribute("LoginOrN", "ImLogin");


		response.sendRedirect(path);
		return;
		
		
    }
	
}