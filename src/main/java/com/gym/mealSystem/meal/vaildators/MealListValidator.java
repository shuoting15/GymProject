package com.gym.mealSystem.meal.vaildators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.gym.mealSystem.meal.model.MealListBean;



@Component
public class MealListValidator implements Validator {
	

	@Override
	public boolean supports(Class<?> clazz) {
		boolean b = MealListBean.class.isAssignableFrom(clazz);
		return b;
	}

	@Override
	public void validate(Object target, Errors errors) {
		MealListBean ml = (MealListBean)target;
		ValidationUtils.rejectIfEmptyOrWhitespace (errors, "mealKcal", "", "熱量不能空白");
		
		if (ml.getMealName().length()<1) {
			errors.rejectValue("mealName","", "餐點不能空白");
		}
		if (ml.getMealPrice().doubleValue() == 0) {
			errors.rejectValue("mealPrice","", "價格不能空白");
		}
		if (ml.getMealCategoryBean().getCategoryId() == -1) {
			errors.rejectValue("mealCategoryBean","", "必須選擇分類");
		}

	}

}
