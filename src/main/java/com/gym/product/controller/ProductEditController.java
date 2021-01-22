package com.gym.product.controller;

import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletContext;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gym.product.model.ProductBean;
import com.gym.product.service.IProductService;



@Controller
@RequestMapping("/productMaintain")
public class ProductEditController {
	@Autowired
	ServletContext context;
	@Autowired
	IProductService productService;

	long sizeInBytes;
	
	@ModelAttribute("category")
	public List<String> getCategoryList() {
		System.out.println(productService.getCategoryList());
		return productService.getCategoryList();
	}

	@RequestMapping("delete")
	public String deleteProduct(@RequestParam Integer id) {
		productService.deleteProduct(id);
		return "redirect:/productMaintain/productAll";
	}

	// =====UPDATE=======
	// 修改一項商品的某資料
	@GetMapping("updateAny/{id}")
	public String preUpdateProduct(@PathVariable("id") Integer id, Model model) {
		ProductBean pb = productService.getProductById(id);
		model.addAttribute("productBean", pb);
		return "product/update";
	}

	@PostMapping(value = "updateAny/{id}")
	public String updateProduct(@ModelAttribute("productBean") ProductBean pBean,
			RedirectAttributes redirectAttributes) {
		
		if (pBean.getProductImage().getSize() == 0) {
			sizeInBytes = -1;
		} else {
			sizeInBytes = pBean.getProductImage().getSize();
			String imageOriginalFilename = pBean.getProductImage().getOriginalFilename();
			pBean.setFileName(imageOriginalFilename);
			
			Blob coverImage = null;
			try {
				coverImage = convertMultipartFileToBlob(pBean.getProductImage());
			} catch(Exception e) {
				e.printStackTrace();
			}
			pBean.setCoverImage(coverImage);
		}
		
		productService.updateProduct(pBean,sizeInBytes);
		redirectAttributes.addFlashAttribute("SUCCESS", "修改成功!");
		return "redirect:/productMaintain/productAll";
	}

	// 修改多筆商品的一項共同資料=>更新庫存量

	// =====Insert======
	@GetMapping("insert")
	public String addInitProduct(Model model) {
		ProductBean pb = new ProductBean();
		model.addAttribute("productBean", pb);
		return "product/addProduct";
	}

	@PostMapping("insert")
	public String addNewProduct(@ModelAttribute("productBean") ProductBean productBean,
			RedirectAttributes redirectAttributes) {
		System.out.println(productBean.getProductName());
		
		String imageOriginalFilename = productBean.getProductImage().getOriginalFilename();
		productBean.setFileName(imageOriginalFilename);
		
		Blob coverImage = null;
		try {
			coverImage = convertMultipartFileToBlob(productBean.getProductImage());
		} catch(Exception e) {
			e.printStackTrace();
		}
		productBean.setCoverImage(coverImage);
		
		if (productBean.getDiscount() == null) {
			productBean.setDiscount(1.0);
		}		
		if (productBean.getProductInStock() == null) {
			productBean.setProductInStock(0);
		}
		
		productService.insertProduct(productBean);
		redirectAttributes.addFlashAttribute("SUCCESS", "新增成功!");
		return "redirect:/productMaintain/productAll";
	}

	public static Blob convertMultipartFileToBlob(MultipartFile productImage) throws Exception {
		byte[] b = productImage.getBytes();
		return new SerialBlob(b);
	}

}
