package com.gym.coach.aop;

import java.io.IOException;
import java.net.http.HttpResponse;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.resource.HttpResource;

import com.gym.member.model.MemberBean;

@Aspect
@Component
@SessionAttributes({"LoginOK"}) 
public class CheckLogin {
	@Before("execution(* com.gym.coach.controller.*.**(..)) && args(model,..)")
	public void checkLogin(Model model) {
//		System.out.println("APJ測試");
//		MemberBean memberBean =   (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null ) {
//			response.sendRedirect("member/login");
//		}
		
	}	
	
}
