package _01_Member.Registration.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import _01_Member.Registration.model.MemberBean;



public class MemberBeanValidator_ChangePassword implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		MemberBean mb = (MemberBean) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cusPassword", "", "密碼欄不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "", "確認密碼欄不能空白");
		
		if (mb.getCusPassword() != null && mb.getConfirmPassword() != null) {
			if (! mb.getCusPassword().equals(mb.getConfirmPassword())) {
				errors.rejectValue("cusPassword","", "密碼欄與確認密碼不一致");
			}
		}
		
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "電子郵件欄不能空白");
//		ValidationUtils.rejectIfEmpty(errors, "memberMultipartFile", "", "必須挑選圖片");
		
//		if (mb.getMemberId().length()<5) {
//			errors.rejectValue("memberId","", "帳號欄不能小於五個字元");
//		}
		
//		System.out.println("mb.getMemberMultipartFile().getSize()=" + mb.getMemberMultipartFile().getSize());
//		if (mb.getMemberMultipartFile().getSize() == 0) {
//			errors.rejectValue("memberMultipartFile", "", "必須挑選圖片");
//		}
		
	}

}
