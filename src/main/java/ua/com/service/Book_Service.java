package ua.com.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.dto.filter.BookFilter;
import ua.com.dto.filter.TypeFilter;
import ua.com.dto.form.Book_Form;
import ua.com.entity.Author;
import ua.com.entity.Book;

public interface Book_Service {

	void save(Book_Form form);
	
	List<Book> findAll();
	
	Book findOne(int id);
	
	void delete(int id);
	
	Book findBybookName(String bookName);
	
	Book findBybookPrice(int price);
	
	List<Book> findByAuthorId(int id);
	
	Book_Form findForm(int id);
	
	Book findUnique(String bookName, String price, Author author);
	
	Page<Book> findAll(Pageable pageable,BookFilter filter);
	
	Page<Book> findAll(TypeFilter filter, Pageable pageable);
	
}
