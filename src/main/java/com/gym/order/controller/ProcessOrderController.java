package com.gym.order.controller;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;
import com.gym.shoppingcart.model.OrderBean;
import com.gym.shoppingcart.model.OrderItemBean;
import com.gym.shoppingcart.model.ShoppingCart;
import com.gym.shoppingcart.orderMail.OrderMail;
import com.gym.shoppingcart.service.IOrderService;




@Controller
@RequestMapping("shoppingCart")
@SessionAttributes({ "LoginOK", "ShoppingCart", "OrderErrorMessage","orderNo","memberId"})
public class ProcessOrderController {	
	@Autowired
	ServletContext context;	
	@Autowired
	IOrderService orderService;
	@Autowired
	MemberService mService;
	
	@PostMapping("processOrder")
	protected String processOrder(Model model, 
//			@RequestParam("memberName") String memberName,
//			@RequestParam("memberPhone") String memberPhone,
			@RequestParam("memberMail") String memberMail,
			@RequestParam("total") String total,
			@RequestParam("ShippingAddress") String shippingAddress,
			@RequestParam("BNO") String bNO,
			@RequestParam("InvoiceTitle") String invoiceTitle, 
			@RequestParam("payment") String payment, 
			@RequestParam("note") String note,
			WebRequest webRequest, SessionStatus status
			) {
		
		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null) {
			return "redirect:/login";
		}
		
		ShoppingCart sc = (ShoppingCart) model.getAttribute("ShoppingCart");
		if (sc == null) {		
			// 處理訂單時如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			return "redirect:/login";
		}
	
		//==================================
		String memberId = memberBean.getMember_id();   		// 取出會員代號
		double totalAmount = Double.parseDouble(total);  	// 計算訂單總金額 
		Date today = new Date();   							// 新增訂單的時間
		// 新建訂單物件。OrderBean:封裝一筆訂單資料的容器，包含訂單主檔與訂單明細檔的資料。目前只存放訂單主檔的資料。
//		OrderBean ob = new OrderBean(null, memberId, totalAmount, shippingAddress, 
//				bNO, invoiceTitle, today, null, null);
		//先塞假memberId
		OrderBean ob = new OrderBean(null, memberId, totalAmount, shippingAddress, 
				bNO, invoiceTitle, today, payment,note,"付款成功",null);
		
	
		Map<Integer, OrderItemBean> content = sc.getContent();
        
		Double point=null;
		Set<OrderItemBean> items = new LinkedHashSet<>();
		Set<Integer> set = content.keySet();
		for(Integer i : set) {		
			OrderItemBean oib = content.get(i);
			oib.setOrderBean(ob);
			items.add(oib);
			
			//查看是否為點數加值，呼叫mDao
			if(oib.getProductId()==202029 ||oib.getProductId()==202030||oib.getProductId()==202031) {
				System.out.println("點數加值共："+oib.getUnitPrice()*oib.getQuantity());
				point=(oib.getUnitPrice()*oib.getQuantity());
			}
		}
		ob.setItems(items); 
		
		try {
			orderService.persistOrder(ob);
			//===點數加值===
			if(point!=null) {
				System.out.println("進入加值");
				mService.addPoint(memberId,point);
				memberBean.setPoint(memberBean.getPoint()+point);
				System.out.println("加值完成");
			}
			
			//這邊之後改掉 
			  List<OrderBean> orderLst = orderService.getAllOrders();
			  int orderNo=orderLst.get(0).getOrderNo();
			  model.addAttribute("orderNo",orderNo);
			  //model.addAttribute("memberId",orderLst.get(orderLst.size()-1).getMemberId());
			  OrderMail.sendOrderFinishMail(memberMail,orderNo);
			  
			System.out.println("Order Process OK");		
			//加入ＮＣＣＣ
//			return "forward:/shoppingCart/removeShoppingCart";
//			model.addAttribute("ob",ob);
			model.addAttribute("transAmt",ob.getTotalAmount().intValue());
			return "shoppingcart/NcccPaymentPage";
		} catch(RuntimeException ex){
			String message = ex.getMessage();
			String shortMsg = "" ;   
			System.out.println("message=" + message);
			shortMsg =  message.substring(message.indexOf(":") + 1);
			System.out.println(shortMsg);
			model.addAttribute("OrderErrorMessage", "處理訂單時發生異常: " + shortMsg  + "，請調正訂單內容" );
			return "redirect:/shoppingCart/showCartContent";
		}
	}
	
	@PostMapping("ncccProcess")
	protected String processOrder(Model model,
			@RequestParam("MerchantID") String MerchantID,
			@RequestParam("TerminalID") String TerminalID,
			@RequestParam("OrderID") String OrderID,
			@RequestParam("TransAmt") String TransAmt,
			@RequestParam("ResponseCode") String ResponseCode,
			@RequestParam("ResponseMsg") String ResponseMsg
			) {
		
		model.addAttribute("MerchantID",MerchantID );
		model.addAttribute("TerminalID",TerminalID);
		model.addAttribute("OrderID",OrderID );
		model.addAttribute("TransAmt",TransAmt );
		model.addAttribute("ResponseCode",ResponseCode );
		model.addAttribute("ResponseMsg",ResponseMsg+"1111" );		
		return "shoppingcart/NcccResponsePage";
	}

}
