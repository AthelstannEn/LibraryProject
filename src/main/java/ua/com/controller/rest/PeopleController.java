package ua.com.controller.rest;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ua.com.entity.People;
import ua.com.service.PeopleService;


	

@RestController
@RequestMapping("/people")
public class PeopleController {

	@Autowired
	private PeopleService peopleService;
	
	@GetMapping
	public List<People> findAll(){
		return peopleService.findAll();
	}
	
	@GetMapping("/{id}")
	public People findOne(@PathVariable int id){
		return peopleService.findOne(id);
	}
	
	@PutMapping
	public People save(@RequestBody People people){
		return peopleService.save(people);
	}
	
	@DeleteMapping("/{id}")
	public HttpStatus delete(@PathVariable int id){
		peopleService.delete(id);
		return HttpStatus.OK;
	}
	
	
}
