package ua.com.service;

import java.util.List;

import ua.com.entity.People;

public interface PeopleService {
	List<People> findAll();
	
	People findOne(int id);
	
	People save(People people);
	
	void delete(int id);
}
