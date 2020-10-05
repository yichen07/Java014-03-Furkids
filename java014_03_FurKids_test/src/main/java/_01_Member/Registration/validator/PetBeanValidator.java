package _01_Member.Registration.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import _01_Member.Registration.model.MemberBean;
import _01_Member.Registration.model.PetBean;



public class PetBeanValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		PetBean pet = (PetBean) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "petName", "", "寵物暱稱不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "petVariety", "", "寵物種類不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "petBreed", "", "寵物品種不能空白");		
		
//		System.out.println("mb.getMemberMultipartFile().getSize()=" + mb.getMemberMultipartFile().getSize());
//		if (mb.getMemberMultipartFile().getSize() == 0) {
//			errors.rejectValue("memberMultipartFile", "", "必須挑選圖片");
//		}
		
	}

}
