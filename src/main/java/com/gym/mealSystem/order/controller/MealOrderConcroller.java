package com.gym.mealSystem.order.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.gym.coach.model.CoachBean;
import com.gym.coach.model.CoachOrderBean;
import com.gym.mealSystem.meal.model.MealCategoryBean;
import com.gym.mealSystem.meal.model.MealListBean;
import com.gym.mealSystem.meal.service.MealCategoryService;
import com.gym.mealSystem.meal.service.MealListService;
import com.gym.mealSystem.meal.vaildators.MealListValidator;
import com.gym.mealSystem.order.model.MealOrderBean;
import com.gym.mealSystem.order.service.MealOrderService;
import com.gym.member.model.MemberBean;

@Controller
@SessionAttributes({ "LoginOK" })
public class MealOrderConcroller {
	String noImage = "/images/NoImage.jpg";
	@Autowired
	MealListService mealListService;
	@Autowired
	MealCategoryService mealCategoryService;
	@Autowired
	MealOrderService mealOrderService;
	@Autowired
	ServletContext context;

	@GetMapping("/orderMeal")
	public String orderEnter(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("mealList", mealListService.getMealList(id));
		return "mealSystem/orderMeal";
	}

	@PostMapping("/orderAdd/{mealId}")
	public String saveOrder(
			@PathVariable("mealId") Integer mealId,
			Model model,
			HttpServletRequest request) {
		System.out.println("ID：" + mealId);
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		MealOrderBean mealOrderBean = new MealOrderBean();
		MealListBean mealListBean = mealListService.getMealList(mealId);
		memberBean.setPoint(memberBean.getPoint() - mealListBean.getMealPrice()); // 扣款
		mealOrderBean.setMemberBean(memberBean); // 存memberID
		mealOrderBean.setOrderStatus(0); // 訂單狀態為0
		Long adminTime = System.currentTimeMillis();
		Timestamp starttime = new Timestamp(adminTime);
		mealOrderBean.setOrderStartTime(starttime); // 訂餐時間
		Timestamp finishtime = new Timestamp(adminTime + 30 * 60 * 1000);
		mealOrderBean.setOrderFinishTime(finishtime); // 取餐時間
		mealOrderBean.setTotalAmount(mealListBean.getMealPrice()); // 訂單金額
		mealOrderBean.setMealListBean(mealListBean);
		System.out.println("@PostMapping後：" + mealOrderBean);
		mealOrderService.saveMealOrder(mealOrderBean);
		return "redirect:/showOrderList";
	}

	@RequestMapping("/showOrderList")
	public String ByMemberId(Model model) {
		MemberBean memberBean =   (MemberBean) model.getAttribute("LoginOK");
		if (memberBean  != null) {
			String memberId = memberBean.getMember_id();
			model.addAttribute("orderList",mealOrderService.getMemberOrders(memberId));
		}
		return "mealSystem/orderOK";
	}
	
	@GetMapping("/updateOrderStatus")
	public String updateStatus(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("orderList", mealOrderService.getAllOrders());
		mealOrderService.updateOrderStatus(id);
		return "redirect:/showOrderList";
	}

}
