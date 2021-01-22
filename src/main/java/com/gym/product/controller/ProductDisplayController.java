package com.gym.product.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gym.product.model.ProductBean;
import com.gym.product.service.IProductService;



@Controller
@RequestMapping("productDisplay")
@SessionAttributes({"pageNo", "products","LoginOK" }) 
public class ProductDisplayController {
	
	@Autowired
	ServletContext context;
	
	@Autowired
	IProductService productService;
	
	//顯示一項商品詳細資料
	@RequestMapping("product")
	public String getProductByID(@RequestParam("id") Integer id,Model model) {
		model.addAttribute("product",productService.getProductById(id));
		return "product/productDetail";
	}
	
	//顯示所有商品--沒做分頁 
	@RequestMapping("productAll2")
	public String getAllProduct(Model model) {
		List<ProductBean> products= productService.queryAllProduct();;
		model.addAttribute("products", products);		
		return "product/productMaster";
	}
	//顯示所有商品--有做分頁
	@GetMapping("/productAll")
	public String getPageProduct(Model model, 
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "pageNo", required = false) Integer pageNo
			//@RequestParam String condition
		) {
//		MemberBean memberBean = (MemberBean) model.getAttribute("LoginOK");
//		if (memberBean == null) {
//			return "redirect:/_02_login/login";
//		}
//		String memberId = memberBean.getMemberId();
		
		if (pageNo == null) {
			pageNo = 1;	
			// 讀取瀏覽器送來的所有 Cookies
//			Cookie[] cookies = request.getCookies();
//			if (cookies != null) {
//				// 逐筆檢視Cookie內的資料
//				for (Cookie c : cookies) {
//					if (c.getName().equals(memberId + "pageNo")) {
//						try {
//							pageNo = Integer.parseInt(c.getValue().trim());
//						} catch (NumberFormatException e) {
//							;
//						}
//						break;
//					}
//				}
//			}
		}
		//model.addAttribute("baBean", service);
		Map<Integer, ProductBean> pMap = productService.getPageProducts(pageNo);
		model.addAttribute("pageNo", String.valueOf(pageNo));
		model.addAttribute("totalPages", productService.getTotalPages());
		System.out.println("共幾頁"+productService.getTotalPages());
		// 將讀到的一頁資料放入request物件內，成為它的屬性物件
		//model.addAttribute("products_DPP", pMap);
		model.addAttribute("products", pMap);
		
		
		List<ProductBean> latestProducts;
		latestProducts = productService.queryNewestProducts();
		model.addAttribute("latestProducts",latestProducts );
		
		long productsFound = productService.getRecordCounts();
		model.addAttribute("productsFound", productsFound);
		// 使用Cookie來儲存目前讀取的網頁編號，Cookie的名稱為memberId + "pageNo"
		// -----------------------
//		Cookie pnCookie = new Cookie(memberId + "pageNo", String.valueOf(pageNo));
//		// 設定Cookie的存活期為30天
//		pnCookie.setMaxAge(30 * 24 * 60 * 60);
//		// 設定Cookie的路徑為 Context Path
//		pnCookie.setPath(request.getContextPath());
//		// 將Cookie加入回應物件內
//		response.addCookie(pnCookie);
		
		return "product/productMaster";
	}

	
	//分類顯示商品-not AJAX
	@RequestMapping("/{category}")
	public String getCategoryProducts(@PathVariable("category") String category,Model model,
			@RequestParam(value = "pageNo", required = false) Integer pageNo
			) {
		Map<Integer, ProductBean> products= productService.getProductsByCategory(category);
		model.addAttribute("products", products);
		model.addAttribute("productsFound", products.size());
		model.addAttribute("totalPages", (int) (Math.ceil(products.size()/12)));
		pageNo=1;
		model.addAttribute("pageNo", String.valueOf(pageNo));
		
		List<ProductBean> latestProducts;
		latestProducts = productService.queryNewestProducts();
		model.addAttribute("latestProducts",latestProducts );
		
		return "product/productMaster";
	}
	
	//分類顯示商品-AJAX
//	@GetMapping(value="/{category}",produces= {"application/json"})
//	public @ResponseBody List<ProductBean> getCategoryProducts(@PathVariable("category") String category){
//		List<ProductBean> products= productService.getProductsByCategory(category);
//		return products;
//	}
	
	//Fuzzy
	@RequestMapping(value="productFuzzy",method=RequestMethod.POST)
	public String getFuzzyProduct(Model model,@RequestParam("keyword") String keyword) {
		List<ProductBean> products;
		products = productService.queryFuzzyProduct(keyword);
		Map<Integer, ProductBean> map = new LinkedHashMap<>();
		for(ProductBean bean: products) {
			map.put(bean.getProductId(), bean);
		}
		model.addAttribute("products", map);
		model.addAttribute("productsFound", products.size());
		
		
		List<ProductBean> latestProducts;
		latestProducts = productService.queryNewestProducts();
		model.addAttribute("latestProducts",latestProducts );
		
		return "product/productMaster";
	}

	
	
	//========sortBy price bestseller newest================
	//@RequestMapping("sort")
//	public String getSortProducts(@RequestParam("by") String sort,Model model,
//			@RequestParam(value = "pageNo", required = false) Integer pageNo
//			) {
//		Map<Integer, ProductBean> products= new LinkedHashMap<Integer, ProductBean>();
//		if(sort=="price") {
//			products= productService.getProductsByPrice();
//			System.out.println("hi");
//		}	
//				
//		model.addAttribute("products", products);
//		model.addAttribute("productsFound", products.size());
//		
//		List<ProductBean> latestProducts;
//		latestProducts = productService.queryNewestProducts();
//		model.addAttribute("latestProducts",latestProducts );
//		
////		model.addAttribute("totalPages", (int) (Math.ceil(products.size()/12)));
////		pageNo=1;
////		model.addAttribute("pageNo", String.valueOf(pageNo));
//		return "product/productMaster";
//	}
	
	@GetMapping(value = "/sort", produces = { "application/json; charset=UTF-8" })
	public @ResponseBody Map<String, Object> getProductsByPrice(@RequestParam("by") String sort,
		@RequestParam(value="pageNo", required = false, defaultValue = "1") Integer pageNo,
		@RequestParam(value="totalPage", required = false) Integer totalPage) {
		
		if (totalPage == null) {
			totalPage = productService.getTotalPages();
		}
		
		List<ProductBean> sortLst= new ArrayList<ProductBean>();
		if(sort.equals("priceAsc")) {
			sortLst=productService.getProductsByPriceAsc();
		}else if(sort.equals("priceDesc")) {
			sortLst=productService.getProductsByPriceDesc();
		}else {
			sortLst=productService.getProductsByIdDesc();
		}

		Map<String, Object> map =new LinkedHashMap<String, Object>();		
		map.put("sort", sortLst);
		map.put("totalPage", totalPage);
		map.put("currPage", pageNo);	
		return map;
	}
	
	//========@ModelAttribute===============================
	@ModelAttribute("categoryLst")
	public List<String> getCategoryList() {
		//System.out.println(productService.getCategoryList());
		return productService.getCategoryList();
	}
	

}
