package com.gym.message.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;
import com.gym.message.model.MailboxBean;
import com.gym.message.model.MessageBean;
import com.gym.message.service.MailboxService;
import com.gym.message.service.MessageService;

@Controller
@SessionAttributes({ "message","LoginOK" })
public class MailboxController {

	@Autowired
	MessageService messageservice;

	@Autowired
	MailboxService mailboxservice;

	@Autowired
	ServletContext context;
	
	@Autowired
	MemberService mservice;

	@RequestMapping("/mailboxes")
	public String list(Model model) {
		MemberBean mbssss = (MemberBean) model.getAttribute("LoginOK");
		model.addAttribute("memname",mbssss);
		List<MailboxBean> list = mailboxservice.getAllMailboxContent();
		model.addAttribute("mailbox", list);
		return "mailboxes";

	}

	@RequestMapping("/mailbox")
	public String getMailboxById(@RequestParam("mailboxId") Integer mailboxId, Model model) {
		model.addAttribute("message", mailboxservice.getMailboxContentById(mailboxId));
		return "mailbox";
	}


	@PostMapping(value="/mailbox/add",produces="text/html;charset=UTF-8")
	public @ResponseBody String processAddNewMailboxForm(Model m, @ModelAttribute("message") MessageBean mb,
			@RequestParam("nd") String mbstring) {
		Map<String,MailboxBean> putMap=new HashMap<String, MailboxBean>();
		MailboxBean mbb = new MailboxBean(mbstring, mb);
		MemberBean mbssss = (MemberBean) m.getAttribute("LoginOK");
		mbb.setTime(new Timestamp(System.currentTimeMillis()));
		System.out.println(mbb.getMailboxContent());
		System.out.println(mb.getArticleId());
		mb.getMailbox().add(mbb);
		mbb.setMemberbean(mbssss);
		mailboxservice.addMailbox(mbb);
		putMap.put(mbssss.getMember_id(), mbb);
		System.out.println(mbb.getMemberbean().getUsername());
		return mbb.getMemberbean().getMemberphoto()+","+mbb.getMemberbean().getUsername()+":"+mbb.getMailboxContent();
		
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

//

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


}
