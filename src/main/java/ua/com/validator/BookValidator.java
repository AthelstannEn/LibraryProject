package ua.com.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.dto.form.Book_Form;
import ua.com.service.Book_Service;

public class BookValidator implements Validator {

	
	private static final Pattern REG = Pattern.compile("^[0-9]+$");
	
	private final Book_Service bookService;
	
	
	public BookValidator(Book_Service bookService){
		super();
		this.bookService = bookService;
	}
	
	
	public boolean supports(Class<?> clazz){
		return Book_Form.class.equals(clazz);
	}
	
	
	public void validate(Object target, Errors errors){
		
		Book_Form form = (Book_Form) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bookName", "", "Can`t be empty");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "", "Can not be empty");
		
		if(!REG.matcher(form.getPrice()).matches()){
			errors.rejectValue("price","", "Onlu digits here");
		}
		if(errors.getFieldError("price")==null){
			if(bookService.findUnique(form.getBookName(), 
					form.getPrice(), 
					form.getAuthor())!=null){
				errors.rejectValue("author", "", "Already exists");
				
			}
		}
	}
	
}
