package com.gym.product.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import product.model.ProductBean;
import product.service.IProductService;
//之後要做換頁功能
@Controller
@RequestMapping("/productMaintain")
public class ProductQueryController {
	@Autowired
	ServletContext context;
	
	@Autowired
	IProductService productService;
	
	//陳列後台所有商品 管理維護
	@RequestMapping("productAll")
	public String getAllProduct(Model model) {
		List<ProductBean> products;
		products = productService.queryAllProduct();
		model.addAttribute("products", products);
		return "product/products2";
	}
	
	//模糊搜尋
	@RequestMapping(value="productFuzzy",method=RequestMethod.POST)
	public String getFuzzyProduct(Model model,@RequestParam("keyword") String keyword) {
		List<ProductBean> products;
		products = productService.queryFuzzyProduct(keyword);
		long productsFound = productService.getRecordCounts();
		model.addAttribute("products", products);
		model.addAttribute("productsFound", productsFound);
		return "product/products";
	}
	
	
	@GetMapping(value = "/pageProductList", produces = { "application/json; charset=UTF-8" })
	public @ResponseBody Map<String, Object> getPageProductList(
		@RequestParam(value="pageNo", required = false, defaultValue = "1") Integer pageNo,
		@RequestParam(value="totalPage", required = false) Integer totalPage) {
		
		if (totalPage == null) {
			totalPage = productService.getTotalPages();
		}
		
		Map<String, Object> map = new HashMap<>();
		List<ProductBean> list = productService.getPageProductList(pageNo);
		map.put("list", list);
		map.put("totalPage", totalPage);
		map.put("currPage", pageNo);
		
		return map;
	}
	
	
	//===========================================
	
	@GetMapping("getBookImage")
	public ResponseEntity<byte[]> getBookImage(	@RequestParam("id") Integer id 
	) {
		InputStream is = null;
		OutputStream os = null;
		String fileName = null;
		String mimeType = null;
		byte[] media = null;
		ResponseEntity<byte[]> responseEntity = null;
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = null;
		Blob blob = null;
		try {
			ProductBean bean = productService.getProductById(id);
			if (bean != null) {
				blob = bean.getCoverImage();
				if (blob != null) {
					is = blob.getBinaryStream();
				}
				fileName = bean.getFileName();
			}
			// 如果圖片的來源有問題，就送回預設圖片(/images/NoImage.png)	
//			if (is == null) {
//				fileName = "NoImage.png" ; 
//				is = context.getResourceAsStream(
//						"/images/" + fileName);
//			}
			// 由圖片檔的檔名來得到檔案的MIME型態
			mimeType = context.getMimeType(fileName);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 由InputStream讀取位元組，然後由OutputStream寫出
			int len = 0;
			byte[] bytes = new byte[8192];
			
			while ((len = is.read(bytes)) != -1) {
				baos.write(bytes, 0, len);
			}
			media = baos.toByteArray();
			mediaType = MediaType.valueOf(mimeType);
			headers.setCacheControl(CacheControl.noCache().getHeaderValue());
			headers.setContentType(mediaType);
			responseEntity =  new ResponseEntity<>(media, headers, HttpStatus.OK);
			
		} catch(Exception ex) {
			ex.printStackTrace();
			//throw new RuntimeException("_00_init.util.RetrieveBookImageServlet#doGet()發生Exception: " + ex.getMessage());
		} finally{
			try {
				if (is != null) is.close();
			} catch(IOException e) {
				;
			}
			try {
				if (os != null) os.close();
			} catch(IOException e) {
				;
			}
		}
		return responseEntity;
	}

}
