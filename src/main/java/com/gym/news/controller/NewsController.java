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
import org.springframework.transaction.annotation.Transactional;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gym.coach.model.CoachBean;
import com.gym.coach.validator.CoachValidator;
import com.gym.init.util.SystemUtils2018;
//import com.gym.news.model.AuthorBean;
import com.gym.news.model.NewsBean;
import com.gym.news.model.NewsPlaylistBean;
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
	public String newslist(Model model) {
		List<NewsBean> list = newsservice.getAllNews();
		model.addAttribute("news", list);
		return "news/news";// 視圖邏輯名稱
	}

	@RequestMapping("/newsviews")
	public String viewslist(Model model) {
		List<NewsBean> list = newsservice.getNewsByViews();
		model.addAttribute("news", list);
		return "news/news";// 視圖邏輯名稱
	}

	@RequestMapping("/queryNewsByCategory")///queryByCategory
	public String getNewsCategoryList(Model model) {
		List<String> list = newsservice.getAllNewsCategories();
		model.addAttribute("newscategoryList", list);//categoryList
		return "news/newscategory";//types/category
	}

	@GetMapping("/newsmodify")
	public String newsmodify(Model model){
		List<NewsBean> list = newsservice.getAllNews();
		model.addAttribute("news", list);
		return "news/newsmodify";
	}
	

//	public String updateCoach(@ModelAttribute CoachBean bean, Model model, BindingResult result,
//			RedirectAttributes redirectAttributes) {
//		long sizeInBytes = -1;
//		CoachValidator validator = new CoachValidator(false);
//		validator.validate(bean, result);
//		if (result.hasErrors()) {
//			SystemUtils2018.showErrors(result);
//			return "coach/coachUpdate";
//		}
//
//		if (bean.getCoachImage().getSize() == 0) {
//			sizeInBytes = -1;
//		} else {
//			sizeInBytes = bean.getCoachImage().getSize();
//			// Step1
//			String imageOriginalFilename = bean.getCoachImage().getOriginalFilename();
//			bean.setFileName(imageOriginalFilename);
//			// Step2
//			Blob coachImage = null;
//			try {
//				coachImage = SystemUtils2018.convertMultipartFileToBlob(bean.getCoachImage());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			bean.setCoachPhoto(coachImage);
//		}
//		service.updateCoach(bean, sizeInBytes);
//		redirectAttributes.addFlashAttribute("SUCCESS", "修改成功!!!");
//		return "redirect:/coachMaintain";
//	}

	
	@RequestMapping("/news{newscategory}")//products/{category}
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
	@RequestMapping("/deletenewsbyId")
	public String deleteNewsById(@RequestParam("id") int newsId) {
		System.out.println("delete"+newsId);
		newsservice.deleteNewsById(newsId);	
//		return "news/news";
		return "redirect:/newsmodify";
	}
	
	@RequestMapping("/newsone")
	public String getNewsById(@RequestParam("id") Integer id, Model model) {
		
		NewsBean bean = newsservice.getNewsById(id);
		Integer nv = bean.getNewsViews();
		nv++;
		System.out.println("nv="+nv);
		bean.setNewsViews(nv);
		newsservice.update(bean);
		
		model.addAttribute("newsone", newsservice.getNewsById(id));

		return "news/newsone";
	}
	@RequestMapping("/newsonemodify")
	public String getNewsByIdmodify(@RequestParam("id") Integer id, Model model) {
		
		NewsBean bean = newsservice.getNewsById(id);
		Integer nv = bean.getNewsViews();
		nv++;
		System.out.println("nv="+nv);
		bean.setNewsViews(nv);
		newsservice.update(bean);
		
		model.addAttribute("newsone", newsservice.getNewsById(id));

		return "news/newsonemodify";
	}
	
	@GetMapping("/newsadd")
	public String getAddNewNewsForm(Model model) {
		NewsBean bb = new NewsBean();
		model.addAttribute("newsBean", bb);
		return "news/addNewsone";
	}
	

	@PostMapping("/newsadd")
	public String processAddNewNewsForm(@ModelAttribute("newsBean") NewsBean bb, BindingResult result) {
				String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("嘗試傳入不允許的欄位: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		if (bb.getNewsViews() == null) {
			bb.setNewsViews(0);
		}
		MultipartFile newsproductImage = bb.getNewsproductImage();
		String originalFilename = newsproductImage.getOriginalFilename();
//		String originalVideoname = videofile.getOriginalFilename();
		bb.setNewsFileName(originalFilename);
//		bb.setNewsVideoName(originalVideoname);
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
			System.out.println("You successfully uploaded file=" +imageFolder+ bb.getNewsId() + ext);
		     
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
		}		

		return "redirect:/newsmodify";
	}
//
	@ModelAttribute("authorList")
	public Map<Integer, String> getAuthorList() {
		Map<Integer, String> authorMap = new HashMap<>();
		List<CoachBean> list = newsservice.getAuthorList();
		for (CoachBean cb : list) {
			authorMap.put(cb.getCoachId(), cb.getCoachName());
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
	
	
	@PostMapping(value="/messageadd",produces="text/html;charset=UTF-8")
	public @ResponseBody String processAddNewsMessageForm(Model m, @ModelAttribute("newsmessage") NewsPlaylistBean nb,
	@RequestParam("nd") String mbstring) {
//	MailboxBean mbb = new MailboxBean(mbstring, mb);
//		mbb.setTime(new Timestamp(System.currentTimeMillis()));
//	System.out.println(mbb.getMailboxContent());
//	System.out.println(mb.getArticleId());
//	mb.getMailbox().add(mbb);
//	mbb.setMemberbean(mbssss);
//	mailboxservice.addMailbox(mbb);
	return mbstring;
	}
	
//	@PostMapping(value="/mailbox/add",produces="text/html;charset=UTF-8")
//	public @ResponseBody String processAddNewMailboxForm(Model m, @ModelAttribute("message") MessageBean mb,
//	@RequestParam("nd") String mbstring) {
//	MailboxBean mbb = new MailboxBean(mbstring, mb);
//	MemberBean mbssss = (MemberBean) m.getAttribute("LoginOK");
//	mbb.setTime(new Timestamp(System.currentTimeMillis()));
//	System.out.println(mbb.getMailboxContent());
//	System.out.println(mb.getArticleId());
//	mb.getMailbox().add(mbb);
//	mbb.setMemberbean(mbssss);
//	mailboxservice.addMailbox(mbb);
//	return mbstring;
//
//	}
	
	@RequestMapping("/searchnews")
	public String newssearchlist(Model model,@RequestParam("newskw") String newskw) {
		List<NewsBean> list = newsservice.getSearchNews(newskw);
		model.addAttribute("news", list);
		return "news/news";// 視圖邏輯名稱
	}
	
	@RequestMapping("/searchnewsmodify")
	public String newssearchlistmodify(Model model,@RequestParam("newskw") String newskw) {
		List<NewsBean> list = newsservice.getSearchNews(newskw);
		model.addAttribute("news", list);
		return "news/newsmodify";// 視圖邏輯名稱
	}
	
	@RequestMapping("/addintoplay/{memberid}/{id}")
	public String addintoplaylist(@PathVariable("id") Integer id,@PathVariable("memberid") String member_id) {
		System.out.println(id+member_id);
		System.out.println("here");
		NewsPlaylistBean nb = new NewsPlaylistBean();
		nb.setMember_id(member_id);
		nb.setFK_NewsBean_newsId(id);
		newsservice.saveintoplaylist(nb);
		return "redirect:/news";
		
	}
	
	
//	@RequestMapping("/newsplaylist")
//	public String newsplaylist(Model model,@RequestParam("member_id") String member_id) {
//		System.out.println(member_id);
//		List<NewsBean> list = newsservice.getplaylistNews(member_id);
//		model.addAttribute("news", list);
//		return "news/news";// 視圖邏輯名稱
//	}
	@RequestMapping("/newsplaylist{LoginOK.member_id}")
	public String newsplaylist(Model model,@PathVariable("LoginOK.member_id") String memberid) {
		System.out.println(memberid+"mail");
		List<NewsBean> list = newsservice.getplaylistNews(memberid);
		model.addAttribute("news", list);
		return "news/news";// 視圖邏輯名稱
	}
	
	@GetMapping("/updatenewsone")
	public String updatenewsone(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("newsone", newsservice.getNewsById(id));
		NewsBean bb = new NewsBean();
		model.addAttribute("newsBean", bb);
		return "news/updatenewsone";
		
	}
	

	
	
	@PostMapping("/updatenewsone{newsId}")
	public String processUpdateNewNewsForm(@ModelAttribute("newsone") NewsBean bb, BindingResult result) {
		CoachBean cb = newsservice.getAuthorById(bb.getAuthorId());
		bb.setCoachBean(cb);

		MultipartFile newsproductImage = bb.getNewsproductImage();
		
		String originalFilename = newsproductImage.getOriginalFilename();
				bb.setNewsFileName(originalFilename);
//		  建立Blob物件，交由 Hibernate 寫入資料庫	
//				System.out.println(originalFilename);
//				System.out.println(originalFilename);
//				if (originalFilename.isEmpty()) {
//					System.out.println("isEmpty");
//					System.out.println(bb.getNewsId());
//					Blob op=newsservice.getNewsById(bb.getNewsId()).getNewsImage();
//					bb.setNewsImage(op);
//				};
			
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
		newsservice.update(bb);
		
//		List<NewsBean> list = newsservice.getAllNews();
//		model.addAttribute("news", list);
		return "redirect:/newsmodify";
	}
}
