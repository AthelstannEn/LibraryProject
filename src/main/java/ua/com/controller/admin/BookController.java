package ua.com.controller.admin;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.dto.filter.BookFilter;
import ua.com.dto.form.Book_Form;
import ua.com.editor.AuthorEditor;
import ua.com.entity.Author;
import ua.com.service.Author_Service;
import ua.com.service.Book_Service;
import ua.com.util.ParamBuilder;
import ua.com.validator.BookValidator;




@Controller
@RequestMapping("/admin/goodModels")
@SessionAttributes("goodModel")
public class BookController {

	
	
	@Autowired
	private Book_Service goodModelService;
	
	@Autowired
	private Author_Service goodTypeService;
	
	
	
	@RequestMapping("/")
	public String goHome(){
		return "user-hustleMain";
	}
	
	@GetMapping("/cancel")
	public String cancel(SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BookFilter filter){
		status.setComplete();
		return "redirect:/admin/goodModels"+getParams(pageable, filter);
	}
	
	@InitBinder("goodModel")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Author.class, new AuthorEditor(goodTypeService));	
		binder.setValidator(new BookValidator(goodModelService));
	}
	
	@ModelAttribute("filter")
	public BookFilter getFilter(){
		return new BookFilter();
	}
	
	@ModelAttribute("goodModel")
	public Book_Form getForm(){
		return new Book_Form();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") BookFilter filter){
		model.addAttribute("types", goodTypeService.findAll());
		model.addAttribute("page", goodModelService.findAll(pageable, filter));
		model.addAttribute("goodTypes", goodTypeService.findAll());
		
		return "admin-goodModels";
	}
	
	
	
	
	
	@PostMapping
	public String save(@ModelAttribute("goodModel")@Valid Book_Form goodModel ,BindingResult br, Model model,SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") BookFilter filter){
		if(br.hasErrors()) return show(model, pageable, filter);
		goodModelService.save(goodModel);
		status.setComplete();
		return "redirect:/admin/goodModels"+getParams(pageable, filter);
	}
	

	private String getParams(Pageable pageable,BookFilter filter){
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
	
	
}
