package _03_FriendlyService.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import _03_FriendlyService.model.ConvenienceBean_H;

public class ConvenienceAlterValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return ConvenienceBean_H.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "conItemList", "", "服務項目不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "conCloseDay", "", "公休日不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "conOpenTime", "", "開始營業時間不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "conCloseTime", "", "結束營業時間不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "merchantChildBean.busChildEmail", "", "分店信箱不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "merchantChildBean.busChildTel", "", "分店電話不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "merchantChildBean.busChildDescription", "", "商店介紹／備註不能空白");
		
	}

}
