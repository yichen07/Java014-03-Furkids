package _01_Member.Registration.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.MerchantChildBean;



public class MerchantChildBeanValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MerchantChildBean mcb = (MerchantChildBean) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "busChildName", "", "商家分店姓名不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "busChildEmail", "", "商家分店電子郵件不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "busChildTel", "", "商家分店電話不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "busChildAddress", "", "商家分店地址不能空白");
		
				
//		ValidationUtils.rejectIfEmpty(errors, "memberMultipartFile", "", "必須挑選圖片");
		
		
//		System.out.println("mb.getMemberMultipartFile().getSize()=" + mb.getMemberMultipartFile().getSize());
//		if (mb.getMemberMultipartFile().getSize() == 0) {
//			errors.rejectValue("memberMultipartFile", "", "必須挑選圖片");
//		}
		
	}

}
