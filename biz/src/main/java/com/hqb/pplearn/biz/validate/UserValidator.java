package com.hqb.pplearn.biz.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.hqb.pplearn.biz.form.UserForm;

public class UserValidator implements Validator {

	private EmailValidator emailValidator = new EmailValidator();

	@Override
	public boolean supports(Class<?> clazz) {
		return UserForm.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userAccount", null, "Username is empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", null, "Email is empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", null, "Password is empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "offer", null, "I cam Offer is empty.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "want", null, "I want to learn is empty.");

		UserForm form = (UserForm) obj;
		if (!emailValidator.validate(form.getEmail())) {
			errors.rejectValue("email", null, "Invalid email address.");
		}
	}
}