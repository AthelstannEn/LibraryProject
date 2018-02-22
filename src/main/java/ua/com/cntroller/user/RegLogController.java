package ua.com.cntroller.user;



import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import ua.com.dto.filter.BookFilter;
import ua.com.entity.User;
import ua.com.service.Author_Service;
import ua.com.service.User_Service;
import ua.com.validator.UserValidator;

@Controller
public class RegLogController {

	@Autowired
	private User_Service userService;
	
	@Autowired
	private Author_Service goodTypeService;
	
	@ModelAttribute("filter")
	private BookFilter getFilter(){
		return new BookFilter();
	}
	
	
	
	@InitBinder("user")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new UserValidator(userService));
	}
	
	@GetMapping("/RegForm")
	public String registration(Model model, Principal principal){
		model.addAttribute("user", new User());
		model.addAttribute("types",goodTypeService.findAll() );
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		return "user-RegForm";
	}
	
	@PostMapping("/RegForm")
	public String save(@ModelAttribute("user")@Valid User user, BindingResult br){
		if(br.hasErrors()) return "user-RegForm";
		userService.save(user);
		userService.sendMail("Registration status", user.getEmail(), "Done, "+user.getFirstName()+"! You successfully joined Apple Shop  team!"
				+ "  if you have some question  whrite to us!!! ");
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(Model model , Principal principal){
		model.addAttribute("types",goodTypeService.findAll() );
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		return "user-login";
	}
	
	
	
	
}
