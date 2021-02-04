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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gym.coach.model.CoachBean;
import com.gym.coach.service.CoachCustomerManageService;
import com.gym.coach.service.CoachService;
import com.gym.coach.service.CoachsPerFormanceService;
import com.gym.coach.validator.CoachValidator;
import com.gym.init.util.SystemUtils2018;
import com.gym.member.model.MemberBean;
import com.gym.member.service.MemberService;

@Controller
@SessionAttributes({"LoginOK"}) 
public class CoachController {
	@Autowired
	CoachService service;

	@Autowired
	ServletContext context;
	
	@Autowired
	CoachsPerFormanceService pfservice;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	CoachCustomerManageService customerService;

	// 教練展示
	@GetMapping("/coachs")
	public String list(Model model) {

		model.addAttribute("coachs", service.getAllCoachs());
		return "coach/coachs";
	}
	// 教練業績
	@GetMapping("/coachPerformance")
	public String coachsPerformance(Model model) {
		List<CoachBean> list = service.getAllCoachs();
		int coachId;
		int totalrevenue;
		int monthrevenue;
		int allmonthrevenue = pfservice.monthAllCoachRevenue();
		int alltotalrevenue = pfservice.totalAllCoachRevenue();
		for (CoachBean i:list) {
			 coachId = i.getCoachId();
			 totalrevenue = pfservice.totalCoachRevenue(coachId);
			 monthrevenue = pfservice.monthCoachRevenue(coachId);
			 i.setTotalrevenue(totalrevenue);
			 i.setTotalrevenuePercent(totalrevenue*100/alltotalrevenue);
			 i.setMonthrevenue(monthrevenue);
			 i.setMonthrevenuePercent(monthrevenue*100/allmonthrevenue);
		}
		model.addAttribute("coachs", list);
		model.addAttribute("allmonthrevenue", allmonthrevenue);
		model.addAttribute("alltotalrevenue", alltotalrevenue);
		return "coach/coachsPerformance";
	}
	
	//教練顧客管理
	@GetMapping("/customerManage")
	public String costomerManage(Model model,@RequestParam("id") int coachId) {

		List<MemberBean> list = memberService.selectAll();
		String memberId;
		int  totalConsumeInCoach;
		int  monthConsumeInCoach;
		for (MemberBean i: list) {
			memberId = i.getMember_id();
			totalConsumeInCoach = customerService.totalCaochConsumeByMemberId(memberId,coachId);
			i.setTotalConsumeInCoach(totalConsumeInCoach);
			monthConsumeInCoach = customerService.monthCaochConsumeByMemberId(memberId,coachId);
			i.setMonthConsumeInCoach(monthConsumeInCoach);
		}
		model.addAttribute("members", list);
		model.addAttribute("coach", service.getCoachById(coachId));
		return "coach/customerManage";
	}
	//教練顧客管理api
	@PostMapping(value = "/customerManageapi", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String customerManageapi(@RequestParam("coachId") int coachId,Model model) {
		List<MemberBean> list = memberService.selectAll();
		String memberId;
		int  totalConsumeInCoach;
		int  monthConsumeInCoach;
		for (MemberBean i: list) {
			memberId = i.getMember_id();
			totalConsumeInCoach = customerService.totalCaochConsumeByMemberId(memberId,coachId);
			i.setTotalConsumeInCoach(totalConsumeInCoach);
			monthConsumeInCoach = customerService.monthCaochConsumeByMemberId(memberId,coachId);
			i.setMonthConsumeInCoach(monthConsumeInCoach);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").disableHtmlEscaping().create();
		return gson.toJson(list);
	}
	
	
	
	
	
	
	// 後臺教練展示
	@GetMapping("/coachMaintain")
	public String Maintainlist(Model model) {

		
		model.addAttribute("coachs", service.getAllCoachs());
		return "coach/coachsMaintain";
	}
	//單個教練展示
	@GetMapping(value = "/coach")
	public String getCoachById(Model model,@RequestParam("id") int id ) {

		model.addAttribute("coach", service.getCoachById(id));
		return "coach/coach";
	}
	//顯示新增教練表單
	@PostMapping("/coachs/add")
	public String processAddNewProductForm(@ModelAttribute("coachBean") CoachBean bb, BindingResult result) {
		
		CoachValidator  validator = new CoachValidator();
		validator.validate(bb, result);
		if (result.hasErrors()) {
			SystemUtils2018.showErrors(result);
			return "coach/addCoach";
		}
		
		
		
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
		return "redirect:/coachMaintain";
	}
	//新增教練
	@GetMapping("/coachs/add")
	public String getAddNewProductForm(Model model) {
		CoachBean coachBean = new CoachBean();
		model.addAttribute("coachBean", coachBean);
		return "coach/addCoach";
	}
	//顯示教練照片
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
		String mimeType = context.getMimeType(filename); // getMimeType 會抓出副檔名的mimetype
		MediaType mediaType = MediaType.valueOf(mimeType);
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
	//顯示修改教練畫面
	@GetMapping("coachUpdate/{coachId}")
	public String showUpdateForm(Model model, @PathVariable Integer coachId) {
		model.addAttribute("coachBean", service.getCoachById(coachId));
		return "coach/coachUpdate";
	}
	//刪除教練
	@PostMapping("/coachDelete")
	public String deleteBook(@RequestParam Integer coachId) {
		service.deleteCoach(coachId);
		return "redirect:/coachMaintain";
	}
	//修改教練
	@PostMapping("coachUpdate/{coachId}")
	public String updateCoach(@ModelAttribute CoachBean bean, Model model, BindingResult result,
			RedirectAttributes redirectAttributes) {
		long sizeInBytes = -1;
		CoachValidator validator = new CoachValidator(false);
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
			} catch (Exception e) {
				e.printStackTrace();
			}
			bean.setCoachPhoto(coachImage);
		}
		service.updateCoach(bean, sizeInBytes);
		redirectAttributes.addFlashAttribute("SUCCESS", "修改成功!!!");
		return "redirect:/coachMaintain";
	}
	//傳出專業表
	@ModelAttribute("expertiseList")
	public List<String> getExpertiseList() {
		return service.getAllExpertise();
	}
	//用專業分類教練
	@PostMapping(value="/getCoachsByExpertise",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getCoachsByExpertise(@RequestParam("coachExpertise") String coachExpertise){
		Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(service.getCoachsByExpertise(coachExpertise));
		
	}
	//模糊搜尋
	@PostMapping(value="/getCoachsByFuzzySearch",produces="text/html;charset=UTF-8")
	@ResponseBody
	public String getCoachsByFuzzySearch(@RequestParam("any") String any){
		Gson gson = new GsonBuilder().disableHtmlEscaping().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(service.getCoachsByFuzzySearch(any));
		
	}
	
	
}
