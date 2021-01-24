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
import org.springframework.web.bind.annotation.GetMapping;
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

import com.gym.message.model.MailboxBean;
import com.gym.message.model.MessageBean;
import com.gym.message.service.MailboxService;
import com.gym.message.service.MessageService;

@Controller
@SessionAttributes({ "message" })
public class MailboxController {

	@Autowired
	MessageService messageservice;

	@Autowired
	MailboxService mailboxservice;

	@Autowired
	ServletContext context;

	@RequestMapping("/mailboxes")
	public String list(Model model) {
		List<MailboxBean> list = mailboxservice.getAllMailboxContent();
		model.addAttribute("mailbox", list);
		return "mailboxes";

	}

	@RequestMapping("/mailbox")
	public String getMailboxById(@RequestParam("mailboxId") Integer mailboxId, Model model) {
		model.addAttribute("message", mailboxservice.getMailboxContentById(mailboxId));
		return "mailbox";
	}

	@GetMapping("MailboxUpdate/{mailboxId}")
	public String getUpdateMailboxForm(Model model, @PathVariable String mailboxId) {
		MailboxBean mailboxBean = mailboxservice.getMailboxContentById(Integer.valueOf(mailboxId));
		model.addAttribute("mailboxBean", mailboxBean);
		return "MailboxUpdate";
	}

	@PostMapping("MailboxUpdate/{mailboxId}")
	public String updateForm(@ModelAttribute MailboxBean mb, Model model, RedirectAttributes redirectAttributes) {

		mb.setTime(new Timestamp(System.currentTimeMillis()));
		System.out.println(System.currentTimeMillis());
		long sizeInBytes = -1;

		if (mb.getProductImage().getSize() == 0) {
			sizeInBytes = -1;
		} else {
			sizeInBytes = mb.getProductImage().getSize();
			// Step1
			String imageOriginalFilename = mb.getProductImage().getOriginalFilename();
			mb.setFileName(imageOriginalFilename);
			// Step2
			Blob Images = null;
			try {
				Images = convertMultipartFileToBlob(mb.getProductImage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			mb.setImages(Images);
		}
		mailboxservice.updateMailbox(mb);
		redirectAttributes.addFlashAttribute("SUCCESS", "修改成功!!!");
		return "";
	}

	@PostMapping(value="/mailbox/add",produces="text/html;charset=UTF-8")
	public @ResponseBody String processAddNewMailboxForm(Model m, @ModelAttribute("message") MessageBean mb,
			@RequestParam("nd") String mbstring) {
		
		MailboxBean mbb = new MailboxBean(mbstring, mb);
		System.out.println(mbb.getMailboxContent());
		System.out.println(mb.getArticleId());
		mb.getMailbox().add(mbb);
		mailboxservice.addMailbox(mbb);
//		String rtn="redirect:/message?articleId="+mb.getArticleId();
		return mbstring;

		// mb.setTime(new Timestamp(System.currentTimeMillis()));

//		
	}

	@GetMapping(value = "/MailboxDelete/{mailboxId}")
	public String deleteMailbox(@PathVariable("mailboxId") Integer mailboxId,  RedirectAttributes redirectAttributes) {
		Integer art = mailboxservice.getMailboxContentById(mailboxId).getMessageBean().getArticleId();
		System.out.println("---------"+art);
		mailboxservice.deleteMailbox(mailboxId);
		System.out.println("++++++++++"+art);
		System.out.println("1231213131");
		String abc="redirect:/message?articleId="+art;
		return abc;
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/getpicture/{mailboxId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer mailboxId) {
		String filePath = "/resources/images/NoImage.jpg";

		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		MailboxBean mbean = mailboxservice.getMailboxContentById(mailboxId);
		if (mbean != null) {
			Blob blob = mbean.getImages();
			filename = mbean.getFileName();
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
		System.out.println("media" + media);
		String mimeType = context.getMimeType(filename); // getMimeType 會抓出副檔名的mimetype
		System.out.println("123--------" + filename);
		MediaType mediaType = MediaType.valueOf(mimeType);
		System.out.println("mimeType = " + mimeType + "   mediaType = " + mediaType);
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
//	@ModelAttribute("message")
//	public Integer getmessageId(MessageBean mb) {
//			return mb.getArticleId();
//	}

}
