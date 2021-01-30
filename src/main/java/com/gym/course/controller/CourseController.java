package com.gym.course.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gym.course.init.SystemUtils2018;
import com.gym.course.model.CourseCoachBean;
import com.gym.course.model.CourseBean;
import com.gym.course.model.CourseInfoBean;
import com.gym.course.service.CourseService;
import com.gym.course.validate.CourseValidator;
import com.gym.member.model.MemberBean;

@Controller
@SessionAttributes({"LoginOK"})
public class CourseController {

	@Autowired
	CourseService service;
	
	@Autowired
	ServletContext context;
	
	@GetMapping("/courseMaintainIndex")
	public String maintainPage(Model model) {
//		service.changeAllCoursesStatus();
//		model.addAttribute("courses", service.getAllCourses());
		return "/index";
	}
	
	@GetMapping("/coursesMaintain")
	public String getAllCourses_Admin(Model model) {
		service.changeAllCoursesStatus();
		model.addAttribute("courses", service.getAllCoursesAdmin());
		return "/course/courses_admin";
	}
	
	@GetMapping("/courses")
	public String getAllCourses(Model model) {
		service.changeAllCoursesStatus();
		model.addAttribute("courses", service.getAllCourses());
		return "course/courses";
	}
	
	@GetMapping("/courses/date")
	public String getAllCoursesByDate(@RequestParam("date") Date date, Model model) {
		model.addAttribute("courses", service.getAllCoursesByDate(date));
		return "course/courses";
	}
	
	@GetMapping("/coursesAdd")
	public String getAddNewCourseForm(Model model) {
		CourseBean bean = new CourseBean();
		model.addAttribute("courseBean", bean);
		return "course/addCourse";
	}
	
	@GetMapping("/courseUpdate")
	public String getUpdateCourseForm(@RequestParam("id") Integer courseId, Model model) {
		CourseBean bean = service.getCourseById(courseId);
//		System.out.println(bean.getCourseCoachBean().getId());
		bean.setCoachId(bean.getCourseCoachBean().getId());
		model.addAttribute("courseBean", bean);
		return "course/addCourse";
	}
	
	@PostMapping("/courseUpdate")
	public String updateSave(CourseBean course, BindingResult result,
			RedirectAttributes rd, Model model) {
		long sizeInBytes = -1;
		CourseValidator validator = new CourseValidator(false);
		validator.validate(course, result);
		if(result.hasErrors()) {
			SystemUtils2018.showErrors(result);
			return "course/addCourse";
		}
		if(course.getProductImage().getSize() == 0) {
			sizeInBytes = -1;
		}else {
			sizeInBytes = course.getProductImage().getSize();
			//Step 1
			String imageOriginalFilename = course.getProductImage().getOriginalFilename();
			course.setFileName(imageOriginalFilename);
			// Step2		
			Blob coverImage = null;
			try {
				coverImage = SystemUtils2018.convertMultipartFileToBlob(course.getProductImage());
			} catch(Exception e) {
				e.printStackTrace();
			}
			course.setCoverImage(coverImage);
		}
//		course.setSelected(course.getSelected());
		String newStartTime = course.getDate() + " " + course.getSt();
		String newEndTime = course.getDate() + " " + course.getEt();
		Timestamp chStart = Timestamp.valueOf(newStartTime);
		Timestamp chEnd = Timestamp.valueOf(newEndTime);
		course.setStarttime(chStart);
		course.setEndtime(chEnd);
		
		service.updateCourse(course, sizeInBytes);
//		List<CourseInfoBean> cilist = service.getCourseInfoBycid(course.getCourseId());
		//我要改courseinfobean每筆id是4的課程
//		for(CourseInfoBean ci : cilist) {
//			ci.setC_start(Timestamp.valueOf(course.getStarttime()));
//			ci.setC_end(Timestamp.valueOf(course.getEndtime()));
//			
//		}
		service.updateSelected(course);
		rd.addFlashAttribute("messages", "修改成功!");
		return "redirect:/coursesMaintain";
	}
	
	@GetMapping("/courseOnOff/{courseId}")
	public String offCourse(@PathVariable Integer courseId, RedirectAttributes rd) {
		CourseBean course = service.getCourseById(courseId);
		if(course.getStatus() == true) {
			course.setStatus(false);	
			service.updateCourseStatus(course);	
			rd.addFlashAttribute("messages", "已將課程下架!");	
		}else {
			course.setStatus(true);
			service.updateCourseStatus(course);	
			rd.addFlashAttribute("messages", "已將課程重新上架!");	
		}
		
		
		return "redirect:/coursesMaintain";
	}

	@PostMapping("/coursesAdd")
	public String processAddNewCourseForm(@ModelAttribute("courseBean") CourseBean bean, BindingResult result
			, HttpServletRequest request, RedirectAttributes rd) throws ParseException {
		String[] suppressedFields = result.getSuppressedFields();
		if (suppressedFields.length > 0) {
			throw new RuntimeException("嘗試傳入不允許的欄位: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		CourseValidator validator = new CourseValidator(false);
		validator.validate(bean, result);
		if(result.hasErrors()) {
			SystemUtils2018.showErrors(result);
			return "course/addCourse";
		}
		// 要先拿coachbean才能正常存團課教練
		CourseCoachBean cb = service.getCoachById(bean.getCoachId());
		
		String newStartTime = bean.getDate() + " " + bean.getSt();
		String newEndTime = bean.getDate() + " " + bean.getEt();
		Timestamp chStart = Timestamp.valueOf(newStartTime);
		Timestamp chEnd = Timestamp.valueOf(newEndTime);
		bean.setStarttime(chStart);
		bean.setEndtime(chEnd);
		
		bean.setCourseCoachBean(cb);
		if (bean.getSelected() == null) {
			bean.setSelected(0);
		}
		MultipartFile productImage = bean.getProductImage();
		String originalFilename = productImage.getOriginalFilename();
		bean.setFileName(originalFilename);
		//  建立Blob物件，交由 Hibernate 寫入資料庫
		if (productImage != null && !productImage.isEmpty() ) {
			try {
				byte[] b = productImage.getBytes();
				Blob blob = new SerialBlob(b);
				bean.setCoverImage(blob);
			} catch(Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		service.addCourse(bean);
		String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
		String rootDirectory = context.getRealPath("/");
		try {
			File imageFolder = new File(rootDirectory, "images");
			if (!imageFolder.exists()) imageFolder.mkdirs();
			File file = new File(imageFolder, bean.getCourseId() + ext);
			productImage.transferTo(file);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
		}
		rd.addFlashAttribute("messages", "新增成功!");
		return "redirect:/coursesMaintain";
	}

	@ModelAttribute("coachList")
	public Map<Integer, String> getCoachList() {
		Map<Integer, String> coachMap = new HashMap<>();
		List<CourseCoachBean> list = service.getCoachList();
		for (CourseCoachBean cb : list) {
			coachMap.put(cb.getId(), cb.getName());
		}
		return coachMap;
	}
	
	@RequestMapping("/course")
	public String getProductById(@RequestParam("id") Integer id, Model model) {
		CourseBean course = service.getCourseById(id);
		model.addAttribute("course", course);
		return "course/courseDetail";
	}
	
	@RequestMapping("/courseMaintain")
	public String courseMaintain_Admin(@RequestParam("id") Integer id, Model model) {
		CourseBean course = service.getCourseById(id);
		List<CourseInfoBean> courseslist = service.getCourseInfoBycid(id);
		List<String> namelist = new LinkedList<>();
		for(CourseInfoBean i : courseslist) {
			if(i.getStatus() == true) {
				namelist.add(i.getM_name());
			}
			
		}
		model.addAttribute("namelist", namelist);
//		System.out.println(namelist);
		model.addAttribute("course", course);
		return "course/courseDetail_admin";
	}
	
	
	@RequestMapping("/booking/course")
	public String bookingCourse(@RequestParam("id") Integer id, RedirectAttributes rd, Model model) {
		CourseBean course = service.getCourseById(id);
		//這裡要拿user資料
		//...
		MemberBean memberBean =   (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null ) {
			return "member/login";
		}
		if(memberBean.getPoint() < course.getPrice()) {
			rd.addFlashAttribute("messages","餘額不足");
			return "redirect:/courses";
		}
		memberBean.setPoint(memberBean.getPoint()-course.getPrice());
		int i = service.selectCourse(memberBean, id);
		String message = null;
		if(i == 0) {
			message = "已成功預約課程: " + course.getTitle();
		} else if(i == 1){
			message = "您已預約本課程，請勿重複預約!";
        } else if(i == 2){
        	message = "本課程已額滿!";
        }else{
        	message = "未知錯誤!請洽電話:xxxx";
        }
//		service.updateMax(course.getMax(), id);
//		model.addAttribute("max",n);
		rd.addFlashAttribute("messages", message);
		return "redirect:/courses";
	}
	
	@GetMapping("/unbooking/course")
	public String unbookingCourse(@RequestParam("id") Integer id, RedirectAttributes rd, Model model) {
		MemberBean memberBean =   (MemberBean) model.getAttribute("LoginOK");
		CourseInfoBean info = service.getCourseInfoById(id);
		//拿時間存成timestamp
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String newStartTime = info.getC_date() + " " + info.getC_start() + ":00";
		Timestamp chStart = Timestamp.valueOf(newStartTime);
//		System.out.println(ts);
//		System.out.println(chStart);
		String nowtime = ts.toString().split(" ")[1].substring(0,2);
		String ctime = chStart.toString().split(" ")[1].substring(0,2);
//		System.out.println(ts.getTime() / (1000*60*60));
//		System.out.println(chStart.getTime() / (1000*60*60));
//		int nT = Integer.parseInt(nowtime);
//		int cT = Integer.parseInt(ctime);
		long nT = ts.getTime() / (1000*60*60);
		long cT = chStart.getTime() / (1000*60*60);
		if(cT - nT <= 1) {
			rd.addFlashAttribute("messages", "課程開始前1小時無法取消!");
			return "redirect:/mycourses";
		}
		info.setStatus(false);
		memberBean.setPoint(memberBean.getPoint() + info.getCourseBean().getPrice());
		info.setMemberBean(memberBean);
		service.unbookingCourse(info);
		
		rd.addFlashAttribute("messages", "已取消預約: " + info.getC_name());
		return "redirect:/mycourses";
	}
	
	@GetMapping("/mycourses")
	public String getMyCourses(Model model){
		
		//這裡要拿user資料
		//...
		MemberBean memberBean =   (MemberBean) model.getAttribute("LoginOK");
		if (memberBean == null ) {
			return "member/login";
		}
		String userid = memberBean.getMember_id();
		List<CourseInfoBean> mycourses = service.getMyCourses(userid);
		List<CourseInfoBean> list = new LinkedList<CourseInfoBean>();
		for(CourseInfoBean mycourse : mycourses) {
			if(mycourse.getStatus() == true) {
				list.add(mycourse);
			}
		}
		List<CourseInfoBean> mynowcourses = service.getMyNowCourses(userid);
		List<CourseInfoBean> finishedlist = service.getMyFinishedCourses(userid);
		model.addAttribute("mycourses", list);
		model.addAttribute("mynowlist", mynowcourses);
		model.addAttribute("finishedlist", finishedlist);
//		model.addAttribute("mycourses", service.getMyCourses(userid));
		return "course/mycourses";
	}
	
	@GetMapping("/getCPicture/{courseId}")
	public ResponseEntity<byte[]> getPicture(HttpServletResponse resp, @PathVariable Integer courseId) {
		String filePath = "/resources/images/NoImage.jpg";

		byte[] media = null;
		HttpHeaders headers = new HttpHeaders();
		String filename = "";
		int len = 0;
		CourseBean bean = service.getCourseById(courseId);
		if (bean != null) {
			Blob blob = bean.getCoverImage();
			filename = bean.getFileName();
			if (blob != null) {
				try {
					len = (int) blob.length();
					media = blob.getBytes(1, len);
				} catch (SQLException e) {
					throw new RuntimeException("CourseController的getPicture()發生SQLException: " + e.getMessage());
				}
			} else {
				media = toByteArray2(filePath);
				filename = filePath;
			}
		} else {
			media = toByteArray2(filePath);
			filename = filePath;
		}
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());
		String mimeType = context.getMimeType(filename);
		MediaType mediaType = MediaType.valueOf(mimeType);
//		System.out.println("mediaType =" + mediaType);
		headers.setContentType(mediaType);
		ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
		return responseEntity;
	}

	private byte[] toByteArray2(String filepath) {
		byte[] b = null;
//		String realPath = context.getRealPath(filepath);
		try {
//			File file = new File(realPath);
//			long size = file.length();
//			b = new byte[(int) size];
			InputStream fis = context.getResourceAsStream(filepath);
			ByteArrayOutputStream baos = new ByteArrayOutputStream(); //把東西寫到記憶體
			int len = 0;
			b = new byte[81920]; //[]放512的倍數
			while((len = fis.read(b)) != -1) {
				baos.write(b, 0, len); //從b1陣列第0個寫到第len個
			}
			fis.read(b);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return b;
	}
//
//	@ModelAttribute("categoryList")
//	public List<String> getCategoryList() {
//		return service.getAllCategories();
//	}
	
	@GetMapping("/courseManage")
	public String getCourseManage() {
		return "/course/courseManage";
	}

	@InitBinder
	public void whiteListing(WebDataBinder binder) {
		binder.setAllowedFields("title", "time", "location", "price",
				"category", "max", "coachId", "productImage",
				"status", "date", "starttime", "endtime","courseId","st","et","selected","description");
	}
}
