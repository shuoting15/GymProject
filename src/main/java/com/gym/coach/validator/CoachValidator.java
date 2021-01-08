package com.gym.coach.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gym.coach.model.CoachBean;

public class CoachValidator implements Validator {

	boolean insertMode = true;
	
	public CoachValidator() {
	}

	public CoachValidator(boolean insertMode) {
		this.insertMode = insertMode;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return CoachBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		CoachBean bean = (CoachBean) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coachName", "", "姓名欄不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coachGender", "", "性別欄不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coachExpertise", "", "專長欄不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "coachIntroduction", "", "介紹欄不能空白");
		
//		Double price = Double.parseDouble(bean.getListPrice());
		
		if (insertMode) {
			if (bean.getCoachImage().isEmpty()) {
				errors.rejectValue("productImage","", "必須挑選圖片");
			}
		}
	}

}
