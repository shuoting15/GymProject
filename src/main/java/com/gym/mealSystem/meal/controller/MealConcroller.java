package com.gym.mealSystem.meal.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.gym.mealSystem.meal.model.MealCategoryBean;
import com.gym.mealSystem.meal.model.MealListBean;
import com.gym.mealSystem.meal.service.MealCategoryService;
import com.gym.mealSystem.meal.service.MealListService;
import com.gym.mealSystem.meal.vaildators.MealListValidator;

@Controller
//@RequestMapping("/meal")
@SessionAttributes({"LoginOK"}) 
public class MealConcroller {
	String noImage = "/images/meal/NoImage.jpg";

	@Autowired
	MealListService mealListService;

	@Autowired
	MealCategoryService mealCategoryService;

	@Autowired
	ServletContext context;

	// 顯示所有菜單資料
	@GetMapping("/showAllMealList")
	public String list(Model model) {
		model.addAttribute("mealLists", mealListService.getAllMealList());
		model.addAttribute("categoryList", mealListService.getCategory());
		return "mealSystem/meals";
	}
	
	
	// 分類查詢
	@GetMapping("/queryByCategory")
	public String getCategoryList(Model model) {
		System.out.println(mealListService.getCategory());
		model.addAttribute("categoryList", mealListService.getCategory());
//		return "mealSystem/category";
		return "redirect:/showAllMealList";
	}

	@GetMapping("/showCategoryMealList")
	public String getProductsByCategory(@RequestParam("id") MealCategoryBean id, Model model) {
		model.addAttribute("categoryList", mealListService.getCategory());
		model.addAttribute("mealLists", mealListService.getCategoryNameByCategoryId(id));
		return "mealSystem/meals";
	}

	@GetMapping("/mealList")
	public String getProductById(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("mealList", mealListService.getMealList(id));
		return "mealSystem/meal";
	}

	// 本方法於新增時，送出空白的表單讓使用者輸入資料
	@GetMapping("/menu")
	public String showEmptyForm(Model model) {
		MealListBean ml = new MealListBean();
		model.addAttribute("add", ml);
		return "mealSystem/insertMealList";
	}

	// 當使用者填妥資料按下Submit按鈕後，本方法接收將瀏覽器送來的會員資料，新進行資料的檢查，
	// 若資料有誤，轉向寫入錯誤訊息的網頁，若資料無誤，則呼叫Service元件寫入資料庫
	@PostMapping("/menu")
	// BindingResult 參數必須與@ModelAttribute修飾的參數連續編寫，中間不能夾其他參數
	public String add(@ModelAttribute("add") MealListBean ml, BindingResult result, Model model,
			HttpServletRequest request) {
		System.out.println("餐點資料:" + ml);
		MealListValidator validator = new MealListValidator();
		// 呼叫Validate進行資料檢查
		validator.validate(ml, result);
		if (result.hasErrors()) {
			return "mealSystem/insertMealList";
		}
		MultipartFile picture = ml.getProductImage();
		String originalFilename = picture.getOriginalFilename();
		if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
			ml.setMealImgName(originalFilename);
		}
		// 建立Blob物件，交由 Hibernate 寫入資料庫
		if (picture != null && !picture.isEmpty()) {
			try {
				byte[] b = picture.getBytes();
				Blob blob = new SerialBlob(b);
				ml.setMealImage(blob);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
			}
		}
		// 必須要找出對應的CategoryId物件
		MealCategoryBean mm = mealCategoryService.getMealCategoryBean(ml.getMealCategoryBean().getCategoryId());
		ml.setMealCategoryBean(mm);

		try {
			mealListService.saveMealList(ml);
		} catch (org.hibernate.exception.ConstraintViolationException e) {
			result.rejectValue("mealName", "", "餐點已存在，請重新輸入");
			return "mealSystem/insertMealList";
		} catch (Exception ex) {
			System.out.println(ex.getClass().getName() + ", ex.getMessage()=" + ex.getMessage());
			result.rejectValue("mealName", "", "請通知系統人員...");
			return "mealSystem/insertMealList";
		}
//		mealListService.saveMealList(ml);
		return "redirect:/updateShowAllMealList";
	}

	// 當使用者需要修改時，本方法送回含有會員資料的表單，讓使用者進行修改
	// 由這個方法送回修改記錄的表單...
	@GetMapping("/update/{id}")
	public String showDataForm(@PathVariable("id") Integer id, Model model) {
		MealListBean mealList = mealListService.getMealList(id);
		model.addAttribute(mealList);
		System.out.println("@GetMapping："+mealList);
		return "mealSystem/updateMealList";
	}

	// 刪除一筆紀錄
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		mealListService.deleteMealList(id);
		return "redirect:/updateShowAllMealList";
	}

	// 當將瀏覽器送來修改過的會員資料時，由本方法負責檢核，若無誤則寫入資料庫
	@PostMapping("/update/{id}")
	// BindingResult 參數必須與@ModelAttribute修飾的參數連續編寫，中間不能夾其他參數
	//
	public String modify(@ModelAttribute("mealListBean") MealListBean mealListBean, BindingResult result, Model model,
			@PathVariable Integer id, HttpServletRequest request) {		
		System.out.println("@PostMapping前："+mealListBean);
		// 找到對應的MealCategory物件
		MealCategoryBean mm = mealCategoryService.getMealCategoryBean(mealListBean.getMealCategoryBean().getCategoryId());
		mealListBean.setMealCategoryBean(mm);

		MultipartFile picture = mealListBean.getProductImage();
		if (picture.getSize() == 0) {
			//@ModelAttribute   public void getMember最底下有宣告
			MealListBean mealList = mealListService.getMealList(id);		//可註解
			mealListBean.setMealImage(mealList.getMealImage());				//可註解
			mealListBean.setMealImgName(mealList.getMealImgName());			//可註解
			
		} else {
			String originalFilename = picture.getOriginalFilename();
			if (originalFilename.length() > 0 && originalFilename.lastIndexOf(".") > -1) {
				mealListBean.setMealImgName(originalFilename);
			}

			// 建立Blob物件
			if (picture != null && !picture.isEmpty()) {
				try {
					byte[] b = picture.getBytes();
					Blob blob = new SerialBlob(b);
					mealListBean.setMealImage(blob);
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("檔案上傳發生異常: " + e.getMessage());
				}
			}
		}
		System.out.println("@PostMapping後："+mealListBean);
		mealListService.updateMealList(mealListBean);
		return "redirect:/updateShowAllMealList";
	}

	@GetMapping("/meal/picture/{id}")
	public ResponseEntity<byte[]> getPicture(@PathVariable("id") Integer id) {
		byte[] body = null;
		ResponseEntity<byte[]> re = null;
		MediaType mediaType = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setCacheControl(CacheControl.noCache().getHeaderValue());

		MealListBean ml = mealListService.getMealList(id);
		if (ml == null) {
			return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
		}
		String filename = ml.getMealImgName();
		if (filename != null) {
			if (filename.toLowerCase().endsWith("jfif")) {
				mediaType = MediaType.valueOf(context.getMimeType("dummy.jpeg"));
			} else {
				mediaType = MediaType.valueOf(context.getMimeType(filename));
				headers.setContentType(mediaType);
			}
		}
		Blob blob = ml.getMealImage();
		if (blob != null) {
			body = blobToByteArray(blob);
		} else {
			String path = noImage;

			body = fileToByteArray(path);
		}
		re = new ResponseEntity<byte[]>(body, headers, HttpStatus.OK);

		return re;
	}

	@ModelAttribute
	public void commonData(Model model) {
		List<MealCategoryBean> menuList = mealCategoryService.getAllMealCategorys();
		model.addAttribute("menuList", menuList);
	}

	private byte[] fileToByteArray(String path) {
		byte[] result = null;
		try (InputStream is = context.getResourceAsStream(path);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			byte[] b = new byte[819200];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			result = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public byte[] blobToByteArray(Blob blob) {
		byte[] result = null;
		try (InputStream is = blob.getBinaryStream(); ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			byte[] b = new byte[819200];
			int len = 0;
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len);
			}
			result = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	@ModelAttribute
	public void getMember(@PathVariable(value = "id", required = false) Integer id, Model model) {
		if (id != null) {
			MealListBean mealList = mealListService.getMealList(id);
			model.addAttribute("mealList1", mealList);
			System.out.println("getMember：" + mealList);
		} 
	}
	
	
	
	//後臺管理用
	@GetMapping("/updateShowAllMealList")
	public String updateMeals(Model model) {
		model.addAttribute("mealLists", mealListService.getAllMealList());
		model.addAttribute("categoryList", mealListService.getCategory());
		return "mealSystem/updateMeals";
	}

	@GetMapping("/updateMealList")
	public String updateMeal(@RequestParam("id") Integer id, Model model) {
		model.addAttribute("mealList", mealListService.getMealList(id));
		return "mealSystem/updateMeal";
	}
	
	@GetMapping("/updateShowCategoryMealList")
	public String updateMealByCategory(@RequestParam("id") MealCategoryBean id, Model model) {
		model.addAttribute("categoryList", mealListService.getCategory());
		model.addAttribute("mealLists", mealListService.getCategoryNameByCategoryId(id));
		return "mealSystem/updateMeals";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
