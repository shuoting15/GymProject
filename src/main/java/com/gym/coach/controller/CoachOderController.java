package com.gym.coach.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gym.coach.model.CoachBean;
import com.gym.coach.model.CoachOrderBean;
import com.gym.coach.model.CoachRatingBean;
import com.gym.coach.service.CoachOrderService;
import com.gym.coach.service.CoachRatingService;
import com.gym.coach.service.CoachService;
import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;

@Controller
@SessionAttributes({"LoginOK"}) 
public class CoachOderController  {
	@Autowired
	CoachService coachService;
	@Autowired
	CoachOrderService coachOrderService;
	@Autowired
	CoachRatingService coachRatingService;
	@Autowired
	MemberService memberService;
	//日歷JSON
	@GetMapping(value="findall/{coachId}",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String findTimeByCoachId(@PathVariable int coachId){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		return gson.toJson(coachOrderService.findTimeByCoachId(coachId));
		
	}
	//進入教練時間輸入表
	@GetMapping(value="/addCoachTime/{coachId}",produces="text/html;charset=UTF-8")
	public String addCoachTimesForm(Model model, @PathVariable Integer coachId){
		model.addAttribute("coachBean", coachService.getCoachById(coachId));
		return "coach/addCoachTime";
		
	}
	//寫入教練時間
	@PostMapping(value="/addCoachTime/{coachId}",produces="text/html;charset=UTF-8")
	public String addCoachTimes(HttpServletRequest request,@PathVariable Integer coachId) throws ParseException{
		String[] orderTimeSp = null;
		String orderStatus = request.getParameter("orderStatus");
		String orderTitle = request.getParameter("orderTitle");
		String orderDate = request.getParameter("orderDate");
		String[] orderTime = request.getParameterValues("time");
		CoachOrderBean coachOrderBean = new CoachOrderBean();
		SimpleDateFormat sd =   new SimpleDateFormat( "yyyy-MM-dd" );
		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		
		for (int i = 0; i < orderTime.length; i++) {
			coachOrderBean.setCoachId(coachId);
			coachOrderBean.setOrderTitle(orderTitle);
			coachOrderBean.setOrderStatus(orderStatus);
			coachOrderBean.setOrderColor("blue");
			coachOrderBean.setOrderDate(sd.parse(orderDate));
			orderTimeSp = orderTime[i].split("/");
			coachOrderBean.setOrderStartTime(sdf.parse(orderDate+" "+orderTimeSp[0]));
			coachOrderBean.setOrderEndTime(sdf.parse(orderDate+" "+orderTimeSp[1]));
			CoachBean coachBean = coachService.getCoachById(coachId);
			coachOrderBean.setCoachBean(coachBean);
			coachOrderService.addCoachTime(coachOrderBean);
		}
		return "redirect:/coachTimeMaintain?id="+coachId;
		
	}
	//進入教練時間維護表
	@GetMapping(value = "/coachTimeMaintain")
	public String getCoachTimeById(@RequestParam("id") int id, Model model) {
		model.addAttribute("coach", coachService.getCoachById(id));
		return "coach/coachTimeMaintain";
	}
	//預約時間
	@PostMapping(value = "/addOrderTime")
	public String OrderCoachTime(@RequestParam int orderId,@RequestParam int coachId,Model model ) {
		MemberBean memberBean =   (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null ) {
			return "member/login";
		}
		CoachOrderBean coachOrderBean = coachOrderService.getCoachTimeById(orderId);
		coachOrderBean.setOrderTitle("已被預約");
		CoachBean coachBean = coachService.getCoachById(coachId);
		memberBean.setPoint(memberBean.getPoint()-coachBean.getCoachPrice());
		coachOrderBean.setMemberBean(memberBean);
		coachOrderBean.setOrderStatus("x");
		coachOrderBean.setOrderColor("");
		coachOrderService.orderCoachTime(coachOrderBean);
		return "redirect:/coach/?id="+coachId  ;
	}
	//刪除預約時間
	@PostMapping("/coachTimeDelete")
	public String deleteBook(@RequestParam int orderId,@RequestParam int coachId) {
		coachOrderService.deleteCoachTime(orderId);
		findTimeByCoachId(coachId);
		return "coach/coachTimeMaintain";
	}
	//顧客預定查詢
	@RequestMapping("/showBookingList")
	public String findBookingByMemberId(Model model) {
		MemberBean memberBean =   (MemberBean) model.getAttribute("LoginOK");
		if (memberBean  != null) {
			String memberId = memberBean.getMember_id();
			model.addAttribute("Booking",coachOrderService.findBookingByMemberId(memberId));
		}
		
		
		return "coach/showBookingList";
	}
	//取消預約
	@PostMapping(value = "/cancelBooking")
	public String cancelBooking(@RequestParam int orderId,Model model ) {
		MemberBean memberBean =   (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null ) {
			return "member/login";
		}
		CoachOrderBean coachOrderBean = coachOrderService.getCoachTimeById(orderId);
		CoachBean coachBean = coachService.getCoachById(coachOrderBean.getCoachBean().getCoachId());
		memberBean.setPoint(memberBean.getPoint()+coachBean.getCoachPrice());
		memberService.updatePoint(memberBean);
		coachOrderBean.setOrderTitle("可預約");
		coachOrderBean.setMemberBean(null);
		coachOrderBean.setOrderStatus("o");
		coachOrderBean.setOrderColor("blue");
		coachOrderService.cancelBooking(coachOrderBean);
		return "redirect:/showBookingList"  ;
	}
	@PostMapping(value = "/finishBooking")
	public String finishBooking(Model model,
			@RequestParam("orderId") int orderId,
			@RequestParam("rating") int rating,
			@RequestParam("coachId") int coachId,
			@RequestParam("memberId") String memberId
			) {
		CoachOrderBean coachOrderBean = coachOrderService.getCoachTimeById(orderId);
		coachOrderBean.setOrderStatus("f");
		coachOrderService.finishBooking(coachOrderBean);
		//加入評分
		CoachRatingBean coachRatingBean = new CoachRatingBean();
		coachRatingBean.setRating(rating);
		coachRatingBean.setCoachId(coachId);
		coachRatingBean.setMemberId(memberId);
		coachRatingService.addRating(coachRatingBean);
		double avgRating = coachRatingService.countAvgRating(coachId);
		CoachBean coachBean = coachService.getCoachById(coachId);
		coachBean.setCoachRating(avgRating);
		coachService.updateCoachRating(coachBean);
		return "redirect:/showBookingList"  ;
	}
	@RequestMapping("/showWorkingList")
	public String findBookingBycoachId(@RequestParam int coachId,Model model) {
		model.addAttribute("Booking",coachOrderService.findBookingByCoachId(coachId));
		
		return "coach/showWorkingList";
	}
	
	@PostMapping(value="/checkEmptyTime",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String checkEmptyTime(@RequestParam("coachId") int coachId,@RequestParam("orderDate") String orderDate) throws ParseException{
//		 List<CoachOrderBean> list = coachOrderService.checkEmptyTime(coachId, orderDate);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
		SimpleDateFormat sd =   new SimpleDateFormat( "yyyy-MM-dd" );
		return gson.toJson(coachOrderService.checkEmptyTime(coachId, sd.parse(orderDate)));
		
	}
	
	
}
