package com.web.store.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.web.store.model.BookBean;
import com.web.store.model.CompanyBean;
import com.web.store.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService service;
	
	@Autowired
	ServletContext context;

	@GetMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", service.getAllProducts());
		return "products";
	}

	@GetMapping("/update/stock")
	public String updateStock(Model model) {
		service.updateAllStock();
		return "redirect:/products";
	}

	@GetMapping("/queryByCategory")
	public String getCategoryList(Model model) {
		model.addAttribute("categorylist", service.getAllCategories());
		return "types/category";

	}

	@GetMapping(value = "/products/{category}")
	public String getProductsByCategory(@PathVariable("category") String category, Model model) {
		model.addAttribute("products", service.getProductByCategory(category));
		return "products";
	}

	@GetMapping(value = "/product")
	public String getProductById(@RequestParam("id") int id, Model model) {
		model.addAttribute("product", service.getProductById(id));
		return "product";

	}

	@GetMapping("/products/add")
	public String getAddNewProductForm(Model model) {
		BookBean bookBean = new BookBean();
		model.addAttribute("bookBean", bookBean);
		return "addProduct";
	}

	@PostMapping("/products/add")
	public String processAddNewProductForm(@ModelAttribute("bookBean") BookBean bb, BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("嘗試輸入不允許的欄位:" + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		if (bb.getStock() == null) {
			bb.setStock(0);
		}
		MultipartFile productImage = bb.getProductImage();
		String originalFilename = productImage.getOriginalFilename();
		bb.setFileName(originalFilename);
		// 建立Blob 物件 由 hibernate寫入資料庫
		if (productImage != null&& !productImage.isEmpty()) {
			try {
			byte[] b = productImage.getBytes();
			Blob blob = new SerialBlob(b);
			bb.setCoverImage(blob);
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+e.getMessage());
			}
		}
		
		service.addProduct(bb);
		
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));  //看起來像是拿取副檔名
		String rootDirectory = context.getRealPath("/");
		System.out.println(rootDirectory);
		try {
			File imageFolder = new File(rootDirectory, "imagesCover"); //在rootDirectory 創立 名為"imagesCover"的資料夾
			if (!imageFolder.exists()) {                              //如果沒有資料夾就建立
				System.out.println("創立資料夾");
				imageFolder.mkdirs();
			}
			File file = new File(imageFolder,bb.getBookId()+ext); //在imageFolder 創立 名為"bb.getBookId()+ext"的圖片 後面ext是副檔名
			productImage.transferTo(file); //把圖片內容傳入file裡面
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("檔案上傳發生異常"+e.getMessage());
		}
		return "redirect:/products";
	}

	@ModelAttribute("companyList")
	public Map<Integer, String> getCompanyList() {
		HashMap<Integer, String> companyMap = new HashMap<>();
		List<CompanyBean> list = service.getCompanyList();
		for (CompanyBean cb : list) {
			companyMap.put(cb.getId(), cb.getName());
		}
		return companyMap;
	}

	@ModelAttribute("categoryList")
	public List<String> getCategoryList() {
		return service.getAllCategories();
	}

	@InitBinder
	public void whiteList(WebDataBinder binder) {
		binder.setAllowedFields("author", "bookNo", "category", "price", "title", "companyId","productImage");
	}

	// return "forward:/anotherFWD": 轉發(forward)給能夠匹配給 /anotherFWD的控制器方法
	// 將與下一棒的程式共用同一個請求物件
	// return "anotherFWD": 也是轉發，但Spring框架會視anotherFWD為視圖的邏輯名稱來尋找
	// 對應的視圖，然後由該視圖來產生回應
	@RequestMapping(value = "/forwardDemo")
	public String forward(Model model, HttpServletRequest request) {
		String uri = request.getRequestURI();
		model.addAttribute("modelData0", "這是以/forwardDemo送來的請求");
		model.addAttribute("uri0", uri);
		return "forward:/anotherFWD";
	}

	// 被轉發的方法，將與前一個方法共用同一個請求物件
	@RequestMapping(value = "/anotherFWD")
	public String forwardA(Model model, HttpServletRequest request) {
		String uri = request.getRequestURI();
		model.addAttribute("modelData1", "這是以/anotherFWD送來的請求");
		model.addAttribute("uri1", uri);
		return "forwardedPage";
	}

	// return "redirect:/redirectAnother": 通知瀏覽器對新網址 /redirectAnother發出請求，即重定向
	// (redirect)。由於是另外一個請求，所以放在原來之請求物件內的資料將不存在。必須將屬性物件儲存
	// 在 RedirectAttributes物件內，另外一個請求才會看的到對應的視圖，然後由該視圖來產生回應。
	@RequestMapping(value = "/redirectDemo")
	public String redirect(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		String uri = request.getRequestURI();
		model.addAttribute("modelData2", "這是以/redirectDemo送來的請求，即將通知瀏覽器對" + "新網址發出請求，但瀏覽器不會顯示這樣的訊息");
		model.addAttribute("uri2", uri);
		redirectAttributes.addFlashAttribute("modelData3", "這是加在RedirectAttributes" + "物件內的屬性物件，瀏覽器會顯示");
		redirectAttributes.addFlashAttribute("uri3", uri);
		return "redirect:/redirectAnother";
	}

	// -------------------------
	// 瀏覽器對新網址重新發出的請求將會由這個請求器方法來處理
	@RequestMapping(value = "/redirectAnother")
	public String redirectA(Model model, HttpServletRequest request) {
		return "redirectedPage";
	}
	@RequestMapping(value = "/getPicture/{bookId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer bookId) {
		String filePath = "/resources/images/NoImage.jpg";

		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		BookBean bean = service.getProductById(bookId);
		if (bean != null) {
			Blob blob = bean.getCoverImage();
			filename = bean.getFileName();
			if (blob != null) {
				try {
					len = (int) blob.length();
//					System.out.println(len);
					media = blob.getBytes(1, len);
//					System.out.println(media);
				} catch (SQLException e) {
					throw new RuntimeException("ProductController的getPicture()發生SQLException: " + e.getMessage());
				}
			} else {
				media = toByteArray(filePath);
				filename = filePath;
			}
		} else {
			media = toByteArray(filePath);
			filename = filePath;
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		String mimeType = context.getMimeType(filename); //getMimeType 會抓出副檔名的mimetype  
		MediaType mediaType = MediaType.valueOf(mimeType);
		System.out.println("mimeType = "+mimeType+"   mediaType = " + mediaType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}
	//如果沒有圖片從本地拿圖片
	private byte[] toByteArray(String filepath) {
		byte[] b = null;
		String realPath = context.getRealPath(filepath);  
		try {
//			System.out.println("filepath = "+filepath);  //resources/images/NoImage.jpg
//			System.out.println("realPath = "+realPath);  //C:\WebSource\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\mvcExercise\resources\images\NoImage.jpg
			File file = new File(realPath);
			long size = file.length();
			b = new byte[(int) size];
			InputStream fis = context.getResourceAsStream(filepath);
			fis.read(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}
	
}

