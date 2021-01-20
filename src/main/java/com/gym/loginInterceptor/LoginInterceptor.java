package com.gym.loginInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.gym.member.model.MemberBean;
@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MemberBean bean = (MemberBean) request.getSession().getAttribute("LoginOK");
		if (bean == null) {
			response.sendRedirect("login");
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
