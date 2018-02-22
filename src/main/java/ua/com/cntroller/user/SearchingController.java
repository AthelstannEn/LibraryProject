package ua.com.cntroller.user;

import static ua.com.util.ParamBuilder.getParams;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.dto.filter.BookFilter;
import ua.com.dto.form.Book_Form;
import ua.com.editor.AuthorEditor;
import ua.com.entity.Author;
import ua.com.entity.Items;
import ua.com.service.Author_Service;
import ua.com.service.Book_Service;
import ua.com.service.ItemsService;
import ua.com.service.User_Service;
import ua.com.util.ParamBuilder;
import ua.com.validator.BookValidator;


@Controller
@RequestMapping("/user/searching")
@SessionAttributes("form")
public class SearchingController {

	
	
	@Autowired
	private ItemsService itemService;
	
	@Autowired
	private User_Service userService;
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BookFilter filter){
		status.setComplete();
		return "redirect:/user/searching"+getParams(pageable, filter);
	}
	
	@Autowired
	private Book_Service goodModelService;
	

	
	@Autowired
	private Author_Service goodTypeService;
	
	

	@RequestMapping("/")
	public String goHome(){
		return "user-hustleMain";
	}
	
	@GetMapping("/cancel")

	
	@InitBinder("form")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Author.class, new AuthorEditor(goodTypeService));
		
		binder.setValidator(new BookValidator(goodModelService));
	}
	
	@ModelAttribute("filter")
	public BookFilter getFilter(){
		return new BookFilter();
	}
	
	@ModelAttribute("form")
	public Book_Form getForm(){
		return new Book_Form();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BookFilter filter){
		model.addAttribute("types", goodTypeService.findAll());
		model.addAttribute("model", goodModelService.findAll(pageable, filter));
		
		model.addAttribute("goodTypes", goodTypeService.findAll());
	
		return "user-searching";
	}
	
	
	
	private String getParams(Pageable pageable, BookFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder buffer = new StringBuilder(page);
		if(!filter.getMax().isEmpty()){
			buffer.append("&max=");
			buffer.append(filter.getMax());
		}
		if(!filter.getMin().isEmpty()){
			buffer.append("&min=");
			buffer.append(filter.getMin());
		}
		
		if(!filter.getAuthorId().isEmpty()){
			for(Author id: filter.getAuthorId()){
				buffer.append("&goodTypesId=");
				buffer.append(id);
			}
		}
		
		
		
		return buffer.toString();
		
	}
	
	
	
	
	
	
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id,@ModelAttribute("cartAdd")Book_Form goodModel, Model model, 
			Pageable pageable,@ModelAttribute("filter") BookFilter filter,@ModelAttribute("form") Items item, Principal principal,
			SessionStatus status){
		if(principal!=null){
			System.out.println(principal.getName());
			item.setUserName(principal.getName());
			item.setGoodName(goodModelService.findOne(id).getBookName());
			item.setTotalPrice(goodModelService.findOne(id).getPrice()*item.getQuantity());
			item.setFullName(userService.findByEmail(principal.getName()).getFullName());
			item.setDeliveryAddressCountry(userService.findByEmail(principal.getName()).getDeliveryAddressCountry());
			item.setDeliveryAddressCity(userService.findByEmail(principal.getName()).getDeliveryAddressCity());
			item.setDeliveryHomeAddress(userService.findByEmail(principal.getName()).getDeliveryHomeAddress());
			itemService.save(item);
			status.setComplete();
		}
		return "redirect:/user/searching";
	}
	
	
	
	
}
