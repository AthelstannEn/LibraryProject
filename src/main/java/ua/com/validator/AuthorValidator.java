package ua.com.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.entity.Author;
import ua.com.service.Author_Service;


public class AuthorValidator  implements Validator {

	private final Author_Service authorservice;
	
	public AuthorValidator(Author_Service authorservice){
		super();
		this.authorservice = authorservice;
	}
	
	
	public boolean supports(Class<?> clazz) {
	
		return Author.class.equals(clazz);
	}

	
	public void validate(Object target, Errors errors) {
		
		Author author = (Author) target;
		
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "authorname", "", "Can not be empty");
		
		if(authorservice.findByAuthor(author.getAuthorname())!=null){
			errors.rejectValue("authorname", "", "Already exists");
		}
	}



	
	
	
}
