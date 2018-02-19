package ua.com.service;

import java.util.List;

import ua.com.entity.User;

public interface User_Service {

	
	void save (User user);
	
	List<User> findAll();
	
	User findOne(int id);
	
	void delete(int id);
	
	
	User findByEmail(String email);
	
	User findByMobilePhone(String mobilePhone);
	
	User findByPostCode(int postCode);
	
	void sendMail(String content, String email, String mailBody);
	
	
	
	
}
