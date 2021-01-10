package com.gym.coach.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
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
import org.springframework.util.StringUtils;
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

import com.gym.coach.model.CoachBean;
import com.gym.coach.service.CoachService;
import com.gym.coach.validator.CoachValidator;
import com.gym.init.util.SystemUtils2018;

@Controller
public class CoachController {
	@Autowired
	CoachService service;

	@Autowired
	ServletContext context;
	//教練展示
	@GetMapping("/coachs")
	public String list(Model model) {
		model.addAttribute("coachs", service.getAllCoachs());
		return "coach/coachs";
	}
	//後臺教練展示
	@GetMapping("/coachMaintain")
	public String Maintainlist(Model model) {
		model.addAttribute("coachs", service.getAllCoachs());
		return "coach/coachsMaintain";
	}

	@GetMapping(value = "/coach")
	public String getCoachById(@RequestParam("id") int id, Model model) {
		model.addAttribute("coach", service.getCoachById(id));
		return "coach/coach";
	}

	@PostMapping("/coachs/add")
	public String processAddNewProductForm(@ModelAttribute("coachBean") CoachBean bb, BindingResult result) {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("嘗試輸入不允許的欄位:" + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		if (bb.getCoachRating() == null) {
			bb.setCoachRating(0.0);
		}
		MultipartFile productImage = bb.getCoachImage();
		String originalFilename = productImage.getOriginalFilename();
		bb.setFileName(originalFilename);
		// 建立Blob 物件 由 hibernate寫入資料庫
		if (productImage != null && !productImage.isEmpty()) {
			try {
				byte[] b = productImage.getBytes();
				Blob blob = new SerialBlob(b);
				bb.setCoachPhoto(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常" + e.getMessage());
			}
		}

		service.addCoach(bb);

		String ext = originalFilename.substring(originalFilename.lastIndexOf(".")); // 看起來像是拿取副檔名
		String rootDirectory = context.getRealPath("/");
		System.out.println(rootDirectory);
		try {
			File imageFolder = new File(rootDirectory, "imagesCover"); // 在rootDirectory 創立 名為"imagesCover"的資料夾
			if (!imageFolder.exists()) { // 如果沒有資料夾就建立
				System.out.println("創立資料夾");
				imageFolder.mkdirs();
			}
			File file = new File(imageFolder, bb.getCoachId() + ext); // 在imageFolder 創立 名為"bb.getBookId()+ext"的圖片
																		// 後面ext是副檔名
			productImage.transferTo(file); // 把圖片內容傳入file裡面
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("檔案上傳發生異常" + e.getMessage());
		}
		return "redirect:/coachs";
	}
	
	@GetMapping("/coachs/add")
	public String getAddNewProductForm(Model model) {
		CoachBean coachBean = new CoachBean();
		model.addAttribute("coachBean", coachBean);
		return "coach/addCoach";
	}
	
	@RequestMapping(value = "/getPicture/{coachId}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer coachId) {
		String filePath = "/resources/images/NoImage.jpg";

		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		CoachBean bean = service.getCoachById(coachId);
		if (bean != null) {
			Blob blob = bean.getCoachPhoto();
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
		String mimeType = context.getMimeType(filename); // getMimeType 會抓出副檔名的mimetype
		MediaType mediaType = MediaType.valueOf(mimeType);
		System.out.println("mimeType = " + mimeType + "   mediaType = " + mediaType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	// 如果沒有圖片從本地拿圖片
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
	@GetMapping("coachUpdate/{coachId}")
	public String showUpdateForm(Model model,@PathVariable Integer coachId) {
		model.addAttribute("coachBean", service.getCoachById(coachId));
		return "coach/coachUpdate";
	}
	@PostMapping("/coachDelete/{coachId}")
	public String deleteBook(@PathVariable Integer coachId) {
		service.deleteCoach(coachId);
		return "redirect:/coachMaintain";
	}
	@PostMapping("coachUpdate/{coachId}")
	public String updateForm(@ModelAttribute CoachBean bean,  
			Model model, BindingResult result, 
			RedirectAttributes redirectAttributes
			) {
		long sizeInBytes = -1;
		CoachValidator  validator = new CoachValidator(false);
		validator.validate(bean, result);
		if (result.hasErrors()) {
			SystemUtils2018.showErrors(result);
			return "coach/coachUpdate";
		}
		
		if (bean.getCoachImage().getSize() == 0) {
			sizeInBytes = -1;
		} else {
			sizeInBytes = bean.getCoachImage().getSize();
			// Step1
			String imageOriginalFilename = bean.getCoachImage().getOriginalFilename();
			bean.setFileName(imageOriginalFilename);
			// Step2		
			Blob coachImage = null;
			try {
				coachImage = SystemUtils2018.convertMultipartFileToBlob(bean.getCoachImage());
			} catch(Exception e) {
				e.printStackTrace();
			}
			bean.setCoachPhoto(coachImage);
		}
		service.updateCoach(bean, sizeInBytes);
		redirectAttributes.addFlashAttribute("SUCCESS", "修改成功!!!");
		return "redirect:/coachMaintain";
	}
	@ModelAttribute("expertiseList")
	public List<String> getExpertiseList() {
		return service.getAllExpertise();
	}
}
