package _03_FriendlyService.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import _01_Member.Registration.model.MerchantChildBean;

public class ConvenienceInsertValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return MerchantChildBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "convenienceBean_H.conItemList", "", "服務項目不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "convenienceBean_H.conCloseDay", "", "公休日不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "convenienceBean_H.conOpenTime", "", "開始營業時間不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "convenienceBean_H.conCloseTime", "", "結束營業時間不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "busChildDescription", "", "商店介紹／備註不能空白");
		
	}
	
	

}
