package com.gym.message.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;
import com.gym.message.model.MailboxBean;
import com.gym.message.model.MessageBean;
import com.gym.message.model.ReportBean;
import com.gym.message.service.MessageService;
import com.gym.message.service.ReportService;

@Controller
@SessionAttributes({ "message","LoginOK" })
public class ReportController {
	@Autowired
	MessageService messageservice;

	@Autowired
	ReportService reportservice;

	@Autowired
	ServletContext context;
	
	@Autowired
	MemberService mservice;

	
	@RequestMapping("/reports")
	public String list(Model model,HttpSession session) {
		MemberBean mbssss = (MemberBean) model.getAttribute("LoginOK");
		model.addAttribute("memname",mbssss);
		MessageBean mb =(MessageBean) model.getAttribute("message");
		List<ReportBean> list = reportservice.getAllReport();
		model.addAttribute("reports", list);
		session.setAttribute("message",mb);
		return "message/report";

	}
}
