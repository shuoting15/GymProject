package com.gym.message.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;
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
	
	@PostMapping(value="/report/add",produces="text/html;charset=UTF-8")
	public @ResponseBody String processAddNewMailboxForm(Model m, @ModelAttribute("message") MessageBean mb,
			@RequestParam("rd") String rbstring,HttpSession session) {
		ReportBean rbb = new ReportBean(rbstring, mb);
		MemberBean mbssss = (MemberBean) m.getAttribute("LoginOK");
//		MessageBean mbvv =(MessageBean) m.getAttribute("message");
		rbb.setTime(new Timestamp(System.currentTimeMillis()));
		Integer nv = mb.getReportText();
		nv++;
		System.out.println("nv="+nv);
		mb.setReportText(nv);
		messageservice.updateMessage(mb, 0);
		System.out.println(mb.getArticleId());
		rbb.setMemberbean(mbssss);
		reportservice.addReportContent(rbb);
//		session.setAttribute("message",mbvv);
		return "檢舉成功";
	}
	
	@GetMapping(value = "/ReportDelete/{reportId}")
	public String deleteMailbox(@PathVariable("reportId") Integer reportId,  RedirectAttributes redirectAttributes) {
		Integer art = reportservice.getReportContentById(reportId).getMessageBean().getArticleId();
		System.out.println("---------"+art);
		reportservice.deleteMessageReport(reportId);
		System.out.println("++++++++++"+art);
		System.out.println("1231213131");
		String abc="redirect:/message?articleId="+art;
		return abc;
	}
}
