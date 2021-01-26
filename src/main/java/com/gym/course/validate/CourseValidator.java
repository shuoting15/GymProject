package com.gym.course.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gym.course.model.CourseBean;


public class CourseValidator implements Validator {

	
	boolean insertMode = true;
	
	public CourseValidator() {
	}

	public CourseValidator(boolean insertMode) {
		this.insertMode = insertMode;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CourseBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CourseBean bean = (CourseBean) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "", "名稱不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "", "日期欄不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "st", "", "起始時間不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "et", "", "結束時間不能空白");
		
		
//		Double price = Double.parseDouble(bean.getListPrice());
		if(bean.getLocation().equals("-1")) {
			errors.rejectValue("location","", "請選擇教室");
		}
		if (bean.getCategory().equals("-1")) {
			errors.rejectValue("category","", "請選擇分類");
		}
		if(bean.getCoachId().equals(-1)) {
			errors.rejectValue("coachId","", "請選擇教練");
		}
		
		if (insertMode) {
			if (bean.getProductImage().isEmpty()) {
				errors.rejectValue("courseImage","", "必須挑選圖片");
			}
		}
		
		if(bean.getEt().equals("-1") || bean.getSt().equals("-1")) {
			errors.rejectValue("et","", "請選擇時段");
		}else if(Integer.parseInt(bean.getEt().substring(0,2)) < Integer.parseInt(bean.getSt().substring(0,2))) {
			errors.rejectValue("et","", "結束時間小於開始時間");
		}else if(Integer.parseInt(bean.getEt().substring(0,2)) == Integer.parseInt(bean.getSt().substring(0,2))) {
			errors.rejectValue("et","", "課程至少1小時");
		}
//		if (bean.getCoachBean().getId() == -1) {
//			errors.rejectValue("coachBean","", "必須挑選教練");
//		}
	}

}
