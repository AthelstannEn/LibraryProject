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
import ua.com.service.ItemsService;
import ua.com.service.User_Service;


public class IndexController {

	@Autowired
	private Book_Service bookService;
	
	@Autowired
	private Author_Service authorService;
	
	@Autowired
	private User_Service userService;

	@Autowired
	private ItemsService itemService;
	
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
		model.addAttribute("types",authorService.findAll() );
		return "user-hustleMain";
	}
	
	
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
		model.addAttribute("types",authorService.findAll() );
		return "user-hustleMain";
	}	
	
	
	@RequestMapping("/type/{id}")
	public String typeModelSearch(@PathVariable int id, Model model , Principal principal){
		model.addAttribute("types", authorService.findAll());
		model.addAttribute("type", authorService.findOne(id));
		model.addAttribute("models",  bookService.findByAuthorId(id));
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		return "user-fromTypeToModels";
	}
	
	@RequestMapping("//model/")
	public String goodModel(Model model, Principal principal){
		model.addAttribute("models", bookService.findAll());
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		return "user-ModelsDescript";
	}
	
	@RequestMapping("/model/{id}")
	public String modelGoodSearch(@PathVariable int id, Model model, Principal principal){
		model.addAttribute("types", authorService.findAll());
		model.addAttribute("model", bookService.findOne(id));
	
	//	model.addAttribute("goods", goodService.findByModelId(id));
	
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
			item.setGoodName(bookService.findOne(id).getBookName());
			item.setTotalPrice(bookService.findOne(id).getPrice()*item.getQuantity());
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
		model.addAttribute("types",authorService.findAll() );
		return "user-supportArea";
	}
	
	@RequestMapping("/warrantyAndUpgrade")
	public String warrantyAndUpgrade(Model model, Principal principal){
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		model.addAttribute("types",authorService.findAll() );
		return "user-warrantyAndUpgrade";
	}
	
	@RequestMapping("/giftCards")
	public String giftCards(Model model, Principal principal){
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		model.addAttribute("types",authorService.findAll() );
		return "user-giftCards";
	}
	
	
	@RequestMapping("/advReward")
	public String advReward(Model model, Principal principal){
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		model.addAttribute("types",authorService.findAll() );
		return "user-advReward";
	}
	
	
	@RequestMapping("/hustleDeals")
	public String hustleDeals(Model model, Principal principal){
		model.addAttribute("types", authorService.findAll());
		if(principal!=null){
			System.out.println(principal.getName());
			model.addAttribute("username", principal.getName());
		}
		return "user-hustleDeals";
	}
}
