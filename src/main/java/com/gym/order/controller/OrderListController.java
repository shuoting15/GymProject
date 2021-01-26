package com.gym.order.controller;

import java.sql.Blob;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;

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

import com.gym.shoppingcart.model.OrderBean;
import com.gym.shoppingcart.model.OrderItemBean;
import com.gym.shoppingcart.service.IOrderService;


@Controller
@RequestMapping("orderProcess")
@SessionAttributes({ "LoginOK", "pageNo", "products", "ShoppingCart" })
public class OrderListController {

	@Autowired
	ServletContext context;

	@Autowired
	IOrderService orderService;

	@GetMapping("orderList")
	protected String orderList(Model model) {
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null) {
//			return "redirect:/login/login";
//		}

		// List<OrderBean> memberOrders =
		// orderService.getMemberOrders(memberBean.getMemberId());
		List<OrderBean> memberOrders = orderService.getMemberOrders("200");
		model.addAttribute("memberOrders", memberOrders);
		return "order/orderList";
	}

	@GetMapping(value = "orderDetail", produces = { "application/json; charset=UTF-8" })
	protected @ResponseBody Set<OrderItemBean> orderDetail(Model model, @RequestParam("orderNo") Integer no) {
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null) {
//			return "redirect:/login/login";
//		}

		OrderBean ob = orderService.getOrder(no);
		Set<OrderItemBean> set = ob.getItems();
		System.out.println(set);
//		model.addAttribute("OrderBean", ob);
		return set;
	}

	@GetMapping("orderListAll")
	protected String orderListAll(Model model) {
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null) {
//			return "redirect:/login/login";
//		}

		List<OrderBean> allOrders = orderService.getAllOrders();
		model.addAttribute("allOrders", allOrders);
		return "order/orderListAll";
	}

	@GetMapping(value = "updateOrder", produces = { "text/plain; charset=UTF-8" })
	public @ResponseBody String updateOrder(@RequestParam("orderNo") Integer orderNo,@RequestParam("orderStatus")String orderStatus, Model model) {
		// model.addAttribute("orderBean", ob);
		orderService.updateOrderStatus(orderNo, orderStatus);		
		return orderStatus;
	}
//
//		@PostMapping(value = "updateOrder/{id}")
//		public String updateOrder2(@ModelAttribute("productBean") ProductBean pBean,
//				RedirectAttributes redirectAttributes) {
//			
//			productService.updateProduct(pBean,sizeInBytes);
//			redirectAttributes.addFlashAttribute("SUCCESS", "修改成功!");
//			return "redirect:/productMaintain/productAll";
//		}
	
	@PostMapping("findOrder")
	protected String searchOrder(@RequestParam("searchBy")String searchBy,@RequestParam("keyword")String keyword,Model model) {
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null) {
//			return "redirect:/login/login";
//		}
		List<OrderBean> allOrders=null;
		if(searchBy.equals("byMember")) {
			//orderService.getMemberOrders(memberBean.getMemberId());
			allOrders = orderService.getMemberOrders("200");
		}else {
			allOrders = orderService.getOrderByNo(Integer.parseInt(keyword));
		}

		//List<OrderBean> allOrders = orderService.getAllOrders();
		model.addAttribute("allOrders", allOrders);
		return "order/orderListAll";
	}
	
	

}
