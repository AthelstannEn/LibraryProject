package ua.com.cntroller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.dto.filter.BookFilter;
import ua.com.entity.Items;
import ua.com.service.Author_Service;
import ua.com.service.Book_Service;
import ua.com.service.User_Service;


public class IndexController {

	@Autowired
	private Book_Service bookService;
	
	@Autowired
	private Author_Service authorService;
	
	@Autowired
	private User_Service userService;

	@ModelAttribute("filter")
	private BookFilter getFilter(){
		return new BookFilter();
	}
	
	@GetMapping("/peoples")
	public String people(){
		return "user-people";
	}
	
	
	@RequestMapping("/user/hustleMain")
	public String index(@PathVariable int id, Principal principal, Model model ){
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		model.addAttribute("username",userService.findOne(id));
		return "user-hustleMain";
	}

	
	@RequestMapping("/hustleMain")
	public String hustleMain(Model model, Principal principal ){
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}	
		model.addAttribute("types",bookService.findAll() );
		return "user-hustleMain";
	}
	
	// work w/ ("/RegForm") or w/o ??//?? 
	@RequestMapping("/RegForm")
	public String regForm(Principal principal, Model model){
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		return "user-RegForm";
	}
	

	@RequestMapping("/admin/goodModels")
	public String adminPage(Model model, Principal principal){
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		return "admin-goodModels";
	}
	
	@RequestMapping("/")
	public String goodType(@ModelAttribute("purchaseInfo") Items item, Model model, Principal principal){
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		model.addAttribute("types",bookService.findAll() );
		return "user-hustleMain";
	}	
	
	
	@RequestMapping("/type/{id}")
	public String typeModelSearch(@PathVariable int id, Model model , Principal principal){
		model.addAttribute("books", bookService.findAll());
		model.addAttribute("author", bookService.findOne(id));
		
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		return "user-fromBooks";
	}
	
	@RequestMapping("//model/")
	public String goodModel(Model model, Principal principal){
		model.addAttribute("books", bookService.findAll());
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		return "user-ModelsDescript";
	}
	
	@RequestMapping("/model/{id}")
	public String modelGoodSearch(@PathVariable int id, Model model, Principal principal){
		model.addAttribute("books", bookService.findAll());
		model.addAttribute("author", authorService.findOne(id));
		
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		return "user-ModelsDescript";
	}
	
	@ModelAttribute("purchaseInfo")
	public Items getForm(){
		return new Items();
	}
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id, Model model, 
			Pageable pageable,@ModelAttribute("purchaseInfo") Items item, Principal principal,
			SessionStatus status){
		if(principal!=null){
			System.out.println(principal.getName());
			item.setUserName(principal.getName());
			item.setGoodName(goodModelService.findOne(id).getModelName());
			item.setTotalPrice(goodModelService.findOne(id).getPrice()*item.getQuantity());
			item.setFullName(userService.findByEmail(principal.getName()).getFullName());
			item.setDeliveryAddressCountry(userService.findByEmail(principal.getName()).getDeliveryAddressCountry());
			item.setDeliveryAddressCity(userService.findByEmail(principal.getName()).getDeliveryAddressCity());
			item.setDeliveryHomeAddress(userService.findByEmail(principal.getName()).getDeliveryHomeAddress());
			itemService.save(item);
			status.setComplete();
		}
		return "redirect:/model/{id}";
	}
	
	@RequestMapping("/supportArea")
	public String supportArea(Model model, Principal principal){
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		model.addAttribute("types",bookService.findAll() );
		return "user-supportArea";
	}
	
	@RequestMapping("/warrantyAndUpgrade")
	public String warrantyAndUpgrade(Model model, Principal principal){
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		model.addAttribute("types",bookService.findAll() );
		return "user-warrantyAndUpgrade";
	}
	
	@RequestMapping("/giftCards")
	public String giftCards(Model model, Principal principal){
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		model.addAttribute("types",bookService.findAll() );
		return "user-giftCards";
	}
	
	
	@RequestMapping("/advReward")
	public String advReward(Model model, Principal principal){
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		model.addAttribute("types",bookService.findAll() );
		return "user-advReward";
	}
	
	
	@RequestMapping("/hustleDeals")
	public String hustleDeals(Model model, Principal principal){
		model.addAttribute("types", bookService.findAll());
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		return "user-hustleDeals";
	}
	
}
