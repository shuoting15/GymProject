package com.gym.news.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gym.news.model.AuthorBean;
import com.gym.news.model.NewsBean;
import com.gym.news.service.NewsService;
//import com.web.store.exception.ProductNotFoundException;


@Controller
public class NewsController {
	NewsService newsservice;

	@Autowired
	public void setService(NewsService nservice) {
		this.newsservice = nservice;
	}

	@Autowired
	ServletContext context;

	@RequestMapping("/news")
	public String list(Model model) {
		List<NewsBean> list = newsservice.getAllNews();
		model.addAttribute("news", list);
		return "news/news";// 視圖邏輯名稱
	}


	@RequestMapping("/queryNewsByCategory")///queryByCategory
	public String getNewsCategoryList(Model model) {
		List<String> list = newsservice.getAllNewsCategories();
		model.addAttribute("newscategoryList", list);//categoryList
		return "types/newscategory";//types/category
	}

	@GetMapping("/newsmodify")
	public String newsmodify(){
		return "news/newsmodify";
	}
	
	@RequestMapping("/news/{newscategory}")//products/{category}
	public String getProductsByCategory(@PathVariable("newscategory") String newsCategory, Model model) {
		List<NewsBean> news = newsservice.getNewsByCategory(newsCategory);
		model.addAttribute("news", news);
		return "news/news";
	}

//	@DeleteMapping("/deleteNews/{newsId}")
//	public String deleteNews(@PathVariable Integer id) {
//		System.out.println(id);
//		newsservice.deleteNewsById(id);	
//		return "news";
//	}
	@RequestMapping("/deleteNews/newsId")
	public String deleteNews(@RequestParam("id") Integer id) {
		System.out.println(id);
		newsservice.deleteNewsById(id);	
		return "news/news";
	}

	@RequestMapping("/newsone")
	public String getNewsById(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("newsone", newsservice.getNewsById(id));
		return "news/newsone";
	}

	@GetMapping("/news/add")
	public String getAddNewNewsForm(Model model) {
		NewsBean bb = new NewsBean();
		model.addAttribute("newsBean", bb);
		return "news/addNewsone";
	}

	@PostMapping("/news/add")
	public String processAddNewNewsForm(@ModelAttribute("newsBean") NewsBean bb, BindingResult result, @RequestParam("videofile") MultipartFile videofile) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("嘗試傳入不允許的欄位: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		if (bb.getNewsViews() == null) {
			bb.setNewsViews(0);
		}
		MultipartFile newsproductImage = bb.getNewsproductImage();
		String originalFilename = newsproductImage.getOriginalFilename();
		String originalVideoname = videofile.getOriginalFilename();
		bb.setNewsFileName(originalFilename);
		bb.setNewsVideoName(originalVideoname);
		//  建立Blob物件，交由 Hibernate 寫入資料庫
		if (newsproductImage != null && !newsproductImage.isEmpty() ) {
			try {
				byte[] b = newsproductImage.getBytes();
				Blob blob = new SerialBlob(b);
				bb.setNewsImage(blob);
			} catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		newsservice.addNewsone(bb);
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		String rootDirectory = context.getRealPath("/");
		try {
			File imageFolder = new File(rootDirectory, "images");
			if (!imageFolder.exists()) imageFolder.mkdirs();//建資料夾
			File file = new File(imageFolder, bb.getNewsId() + ext);
			newsproductImage.transferTo(file);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
		}		
		
				if (!videofile.isEmpty()) {
            try {
                // 檔案存放服務端的位置
//                String rootPath = "c:/tmp123";
//                File dir = new File(rootPath + File.separator + "tmpFiles");
            	File dir = new File(rootDirectory,"video");
                if (!dir.exists())
                    dir.mkdirs();
                // 寫檔案到伺服器
                File serverFile = new File(dir.getAbsolutePath() + File.separator + videofile.getOriginalFilename());
                videofile.transferTo(serverFile);
//                return "You successfully uploaded file=" +  file.getOriginalFilename();
            } catch (Exception e) {
                
            }
		}
		return "redirect:/news/news";
	}

	@ModelAttribute("authorList")
	public Map<Integer, String> getAuthorList() {
		Map<Integer, String> authorMap = new HashMap<>();
		List<AuthorBean> list = newsservice.getAuthorList();
		for (AuthorBean cb : list) {
			authorMap.put(cb.getId(), cb.getName());
		}
		return authorMap;
	}

	@ModelAttribute("newscategoryList")
	public List<String> getNewsCategoryList() {
		return newsservice.getAllNewsCategories();
	}

//	@InitBinder
//	public void whiteListing(WebDataBinder binder) {
//		binder.setAllowedFields("author", "bookNo", "category", "price", "title", "companyId", "productImage");
//	}

	// return "forward:/anotherFWD": 轉發(forward)給能夠匹配給 /anotherFWD的控制器方法
	// 將與下一棒的程式共用同一個請求物件
	// return "anotherFWD": 也是轉發，但Spring框架會視anotherFWD為視圖的邏輯名稱來尋找
	// 對應的視圖，然後由該視圖來產生回應
//	@RequestMapping(value = "/forwardDemo")
//	public String forward(Model model, HttpServletRequest request) {
//		String uri = request.getRequestURI();
//		model.addAttribute("modelData0", "這是以/forwardDemo送來的請求");
//		model.addAttribute("uri0", uri);
//		return "forward:/anotherFWD";
//	}
//
//	// 被轉發的方法，將與前一個方法共用同一個請求物件
//	@RequestMapping(value = "/anotherFWD")
//	public String forwardA(Model model, HttpServletRequest request) {
//		String uri = request.getRequestURI();
//		model.addAttribute("modelData1", "這是以/anotherFWD送來的請求");
//		model.addAttribute("uri1", uri);
//		return "forwardedPage";
//	}

	// return "redirect:/redirectAnother": 通知瀏覽器對新網址 /redirectAnother發出請求，即重定向
	// (redirect)。由於是另外一個請求，所以放在原來之請求物件內的資料將不存在。必須將屬性物件儲存
	// 在 RedirectAttributes物件內，另外一個請求才會看的到對應的視圖，然後由該視圖來產生回應。
//	@RequestMapping(value = "/redirectDemo")
//	public String redirect(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
//		String uri = request.getRequestURI();
//		model.addAttribute("modelData2", "這是以/redirectDemo送來的請求，即將通知瀏覽器對" + "新網址發出請求，但瀏覽器不會顯示這樣的訊息");
//		model.addAttribute("uri2", uri);
//		redirectAttributes.addFlashAttribute("modelData3", "這是加在RedirectAttributes" + "物件內的屬性物件，瀏覽器會顯示");
//		redirectAttributes.addFlashAttribute("uri3", uri);
//		return "redirect:/redirectAnother";
//	}

	// -------------------------
	// 瀏覽器對新網址重新發出的請求將會由這個請求器方法來處理
//	@RequestMapping(value = "/redirectAnother")
//	public String redirectA(Model model, HttpServletRequest request) {
//		return "redirectedPage";
//	}

	@RequestMapping(value = "/getNewsPicture/{newsId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer newsId) {
		String filePath = "/resources/images/NoImage.jpg";

		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		NewsBean bean = newsservice.getNewsById(newsId);
		if (bean != null) {
			Blob blob = bean.getNewsImage();
			filename = bean.getNewsFileName();
			if (blob != null) {
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
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
		String mimeType = context.getMimeType(filename);
		MediaType mediaType = MediaType.valueOf(mimeType);
		System.out.println("mediaType =" + mediaType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

//	private byte[] toByteArray1(String filepath) {
//		byte[] b = null;
//		String realPath = context.getRealPath(filepath);
//		try {
//			File file = new File(realPath);
//			long size = file.length();
//			b = new byte[(int) size];
//			InputStream fis = context.getResourceAsStream(filepath);
//			fis.read(b);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return b;
//	}

	private byte[] toByteArray(String filepath) {
		byte[] b = null;
		try (InputStream fis = context.getResourceAsStream(filepath);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			int len = 0;
			byte[] b0 = new byte[2048];
			while ((len = fis.read(b0)) != -1) {
				baos.write(b0, 0, len);
			}
			b=baos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}
	
//	@ExceptionHandler({ProductNotFoundException.class, SQLException.class})
//	public ModelAndView handleError(HttpServletRequest request, 
//							ProductNotFoundException exception) {
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("invalidBookId", exception.getBookId());
//		mv.addObject("exception", exception);
//		mv.addObject("url", request.getRequestURL()+"?" + request.getQueryString());
//		mv.setViewName("productNotFound");
//		return mv;
//	}
}