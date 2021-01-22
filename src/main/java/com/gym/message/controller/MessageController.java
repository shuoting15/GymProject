package com.gym.message.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gym.message.model.MessageBean;
import com.gym.message.service.MailboxService;
import com.gym.message.service.MessageService;

@Controller
@SessionAttributes({"message"})
public class MessageController {
	@Autowired
	MessageService messageservice;
	
	
	@Autowired
	ServletContext context;

	@RequestMapping("/messages")
	public String list(Model model) {
		List<MessageBean> list=messageservice.getAllMessage();
		List<String> list1 = messageservice.getAllKanbanName();
		model.addAttribute("kanbanNameList", list1);
		model.addAttribute("messages",list);
		return "message/messages";
	}
	
	@RequestMapping("/allmessages")
	public String Alllist(Model model) {
		List<MessageBean> list=messageservice.getAllMessage();
		List<String> list1 = messageservice.getAllKanbanName();
		model.addAttribute("kanbanNameList", list1);
		model.addAttribute("messages",list);
		return "message/messages";
	}
	
	@RequestMapping("/message")
	public String getMessageById(@RequestParam("articleId") Integer articleId, Model model) {
		MessageBean mb = messageservice.getMessageById(articleId);
		model.addAttribute("message", messageservice.getMessageById(articleId));
		model.addAttribute("comments",mb.getMailbox());
		return "message/message";
	}
	
	@RequestMapping("/{kanbanName}")
	public String getMessageByKanbanName(@PathVariable("kanbanName") String kanbanName, Model model) {
		System.out.println(kanbanName);
		List<MessageBean> message = messageservice.getMessageByKanbanName(kanbanName);
		model.addAttribute("messages", message);
		List<String> list1 = messageservice.getAllKanbanName();
		model.addAttribute("kanbanNameList", list1);
		return "message/messages";
	}
	
	@GetMapping("MessageUpdate/{articleId}")
	public String getUpdateMessageForm(Model model ,
			@PathVariable String articleId) {
		MessageBean messageBean = messageservice.getMessageById(Integer.valueOf(articleId));
		model.addAttribute("messageBean", messageBean);
		return "message/MessageUpdate";
	}
	
	@PostMapping("MessageUpdate/{articleId}")
	public String updateForm(@ModelAttribute MessageBean mbean,  
			Model model,
			RedirectAttributes redirectAttributes
			) {
		
		mbean.setTime(new Timestamp(System.currentTimeMillis()));
		System.out.println(System.currentTimeMillis());
		long sizeInBytes = -1;
		
		
		if (mbean.getProductImage().getSize() == 0) {
			sizeInBytes = -1;
		} else {
			sizeInBytes = mbean.getProductImage().getSize();
			// Step1
			String imageOriginalFilename = mbean.getProductImage().getOriginalFilename();
			mbean.setFileName(imageOriginalFilename);
			// Step2		
			Blob Images = null;
			try {
				Images =convertMultipartFileToBlob(mbean.getProductImage());
			} catch(Exception e) {
				e.printStackTrace();
			}
			mbean.setImages(Images);
		}
		messageservice.updateMessage(mbean, sizeInBytes);
		redirectAttributes.addFlashAttribute("SUCCESS", "修改成功!!!");
		return "redirect:/messages";
	}
	
	
	
	@GetMapping("/messages/add")
	public String getAddNewMessageForm(Model model) {
		MessageBean messageBean = new MessageBean();
		model.addAttribute("messageBean", messageBean);
		return "message/MessageForm";
	}
	
	@PostMapping("/messages/add")
	public String processAddNewProductForm(@ModelAttribute("messageBean") MessageBean mb) {
		mb.setTime(new Timestamp(System.currentTimeMillis()));
		
		MultipartFile productImage = mb.getProductImage();
		String originalFilename = productImage.getOriginalFilename();
		mb.setFileName(originalFilename);
		// 建立Blob 物件 由 hibernate寫入資料庫
		if (productImage != null&& !productImage.isEmpty()) {
			try {
			byte[] b = productImage.getBytes();
			Blob blob = new SerialBlob(b);
			mb.setImages(blob);
			}catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常"+e.getMessage());
			}
		}
		
		messageservice.addMessage(mb);
		
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));  //看起來像是拿取副檔名
		String rootDirectory = context.getRealPath("/");
		System.out.println(rootDirectory);
		try {
			File imageFolder = new File(rootDirectory, "imagesCover"); //在rootDirectory 創立 名為"imagesCover"的資料夾
			if (!imageFolder.exists()) {                              //如果沒有資料夾就建立
				System.out.println("創立資料夾");
				imageFolder.mkdirs();
			}
			File file = new File(imageFolder,mb.getArticleId()+ext); //在imageFolder 創立 名為"bb.getBookId()+ext"的圖片 後面ext是副檔名
			productImage.transferTo(file); //把圖片內容傳入file裡面
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("檔案上傳發生異常"+e.getMessage());
		}
		return "redirect:/messages";

	}
	@PostMapping(value="/MessageDelete/{articleId}")
	public String deleteMessage(@PathVariable Integer articleId,
			RedirectAttributes redirectAttributes
		) {
		System.out.println("/MessageDelete/{articleId}"+articleId);
		messageservice.deleteMessage(articleId);
		System.out.println("1231213131");
		redirectAttributes.addFlashAttribute("SUCCESS", "刪除成功!!!");
		return "redirect:/messages";
	}
	
	
	@InitBinder
	public void whiteList(WebDataBinder binder) {
		binder.setAllowedFields("articleId", "title", "content", "kanbanName", "repliseCount", "time","productImage");
	}
	
	@ModelAttribute("kanbanNameList")
	public List<String> getKanbanNameList() {
		return messageservice.getAllKanbanName();
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getimage/{articleId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer articleId) {
		String filePath = "/resources/images/NoImage.jpg";

		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		MessageBean mbean = messageservice.getMessageById(articleId);
		System.out.println("圖片:======="+mbean.getImages());
		System.out.println(mbean.getTitle());
		if (mbean != null) {
			System.out.println("第一個if");
			Blob blob = mbean.getImages();
			filename = mbean.getFileName();
			if (blob != null) {
				System.out.println("第2個if");
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
		System.out.println("media"+media);
		String mimeType = context.getMimeType(filename); //getMimeType 會抓出副檔名的mimetype  
		System.out.println("123--------"+filename);
		MediaType mediaType = MediaType.valueOf(mimeType);
		System.out.println("mimeType = "+mimeType+"   mediaType = " + mediaType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}
	
	
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
	public static Blob convertMultipartFileToBlob(MultipartFile productImage) throws Exception {

		byte[] b = productImage.getBytes();
		return new SerialBlob(b);
	}
	
	@RequestMapping("MessageSearchByKey")
	private @ResponseBody List<MessageBean> courseSearchByKC(@RequestParam("Keyword") String keyword){
		List<MessageBean> list4 = messageservice.SearchmessageById(keyword);
		return list4;
	}
	
}
