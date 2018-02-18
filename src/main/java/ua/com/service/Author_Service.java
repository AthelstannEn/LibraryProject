package ua.com.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.dto.filter.TypeFilter;
import ua.com.entity.Author;

public interface Author_Service {

	void save (Author author);
	
	List<Author> findAll();
	
	Author findOne(int id);
	 
	void delete(int id);
	
	Author findByAuthor (String authorname);
	
	List<Author> findByBooksId(int id);
	
	Page<Author> findALl(TypeFilter filter, Pageable pageable);
	
	
}
