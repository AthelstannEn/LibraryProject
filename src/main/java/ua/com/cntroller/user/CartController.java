package ua.com.cntroller.user;

import static ua.com.util.ParamBuilder.getParams;







import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.dto.filter.BookFilter;
import ua.com.dto.filter.TypeFilter;
import ua.com.entity.Items;
import ua.com.service.Author_Service;
import ua.com.service.Book_Service;
import ua.com.service.ItemsService;
import ua.com.service.User_Service;

@Controller
@SessionAttributes("proceedItems")
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private Book_Service goodModelService;
	
	@Autowired
	private Author_Service goodService;
	
	@Autowired
	private ItemsService itemsService;
	
	@Autowired
	private User_Service userService;

	
	
	@ModelAttribute("filter")
	public BookFilter getFilter(){
		return new BookFilter();
	}
	
	@ModelAttribute("purchaseInfo")
	public Items getForm(){
		return new Items();
	}
	
	@ModelAttribute("submit")
	public Items getSubForm(){
		return new Items();
	}
	
	@GetMapping
	public String cartShow(Model model, Principal principal,@ModelAttribute("purchaseInfo") Items item){
		int totalPrice = 0;
		model.addAttribute("types", goodService.findAll());
		if(principal!=null){
			model.addAttribute("purchInfo", itemsService.findByUserNameAndPurchaseStatus(principal.getName(), 0));
			for(Items itemPrice: itemsService.findByUserNameAndPurchaseStatus(principal.getName(), 0)){
				totalPrice = totalPrice + itemPrice.getTotalPrice();
			}
			if(principal!=null){
				System.out.println(principal.getName());
				model.addAttribute("username", principal.getName());
			}
			model.addAttribute("totalPrice", totalPrice);
		}
			
			
		return "user-cart";
	}
	
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") ModelFilter filter){
		
		model.addAttribute("page", goodModelService.findAll(pageable, filter));
		model.addAttribute("goods", goodService.findAll());
	
		return "user-cart";
	}
	
	
	
	@GetMapping("/remove/{id}")
	public String remove(@PathVariable int id,Model model, Principal principal,@ModelAttribute("purchaseInfo") Items item){
		itemsService.delete(id);
		return "redirect:/cart";
	}
	
 
		@GetMapping("/submittion")
		public String submit(@ModelAttribute("submit") Items item, Principal principal){
			String orderedItems = " ";
			if(principal!=null){
		
				for (Items itemStatus : itemsService.findByUserName(principal.getName())) {
					itemStatus.setPurchaseStatus(1);
					itemsService.save(itemStatus);
					orderedItems = orderedItems+itemStatus.getGoodName()+"; ";
				}
				userService.sendMail("Order status", principal.getName(), "Hello, "+userService.findByEmail(principal.getName()).getFullName() +"! Your order: "+orderedItems+" has beed received and soon will be handled! Thx for cooperation with us!");
			}
			return "redirect:/hustleMain";
				}
		
		}

	

