package ua.com.validator;


import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.entity.User;
import ua.com.service.User_Service;


public class UserValidator implements Validator{

	private static final Pattern EMAIL_VALID = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

	private final User_Service userService;
	
	
	public UserValidator(User_Service userService){
		super();
		this.userService = userService;
	}
	
	
	@Override
	public boolean supports(Class<?> clazz){
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {

		User user = (User) target;
		
		ValidationUtils.rejectIfEmpty(errors, "firstName", "", "Required field");
		
		ValidationUtils.rejectIfEmpty(errors, "lastName", "", "Required field");
		
		ValidationUtils.rejectIfEmpty(errors, "email", "", "Required field");
		
		ValidationUtils.rejectIfEmpty(errors, "password", "", "Required field");
		
		ValidationUtils.rejectIfEmpty(errors, "repPassword", "", "Required field");
		
		ValidationUtils.rejectIfEmpty(errors, "mobilePhone", "", "Required field");
		
		ValidationUtils.rejectIfEmpty(errors, "postCode", "", "Required field");
		
		ValidationUtils.rejectIfEmpty(errors, "deliveryAddressCountry", "", "Required field");
		
		ValidationUtils.rejectIfEmpty(errors, "deliveryAddressCity", "", "Required field");
		
		ValidationUtils.rejectIfEmpty(errors, "deliveryHomeAddress", "", "Required field");
		
		if(!EMAIL_VALID.matcher(user.getEmail()).matches()){
			errors.rejectValue("email", "", "Enter your e-mail here");
		}
		
		if(userService.findByEmail(user.getEmail())!=null){
			errors.rejectValue("email", "", "Already exists");
		}
		
		if(userService.findByMobilePhone(user.getMobilePhone())!=null){
			errors.rejectValue("mobilePhone", "", "Already exists");
		}
		
		if(!user.getPassword().equals(user.getRepPassword())){
			errors.rejectValue("repPassword", "", "Passwords must be equals!");
		}
		
	}
	
	
	
}
