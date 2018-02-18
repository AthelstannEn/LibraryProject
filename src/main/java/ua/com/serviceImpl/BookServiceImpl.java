package ua.com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.CRUD.dao.BookDao;
import ua.com.dto.filter.BookFilter;
import ua.com.dto.filter.TypeFilter;
import ua.com.dto.form.Book_Form;
import ua.com.entity.Author;
import ua.com.entity.Book;
import ua.com.service.Book_Service;
import ua.com.service.FileWriter;
import ua.com.service.FileWriter.Folder;
import ua.com.specifications.BookSpecifications;



@Service
public class BookServiceImpl implements Book_Service {

	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private FileWriter fileWriter;
	
	@Override
	public void save(Book_Form form) {

	Book entity = new Book();
	
	entity.setId(form.getId());
	
	entity.setBookName(form.getBookName());
	
	entity.setPrice(new Integer(form.getPrice()));
	
	entity.setAuthor(form.getAuthor());
	
	bookDao.save(entity);
	
	entity = bookDao.saveAndFlush(entity);
	
	if(fileWriter.write(Folder.Books, form.getFile(), entity.getId())){
		
		entity.setVersion(form.getVersion()+1);
		
		bookDao.save(entity);
		
	}
	}

	@Override
	public List<Book> findAll() {
		return bookDao.findAll();
	}

	@Override
	public Book findOne(int id) {
		return bookDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		bookDao.delete(id);
		
	}

	@Override
	public Book findBybookName(String bookName) {
		return bookDao.findBybookName(bookName);
	}

	@Override
	public Book findBybookPrice(int price) {
		return bookDao.findBybookPrice(price);
	}

	@Override
	public List<Book> findByAuthorId(int id) {
		return bookDao.findByAuthorId(id);
	}

	@Override
	public Book findUnique(String bookName, String price, Author author) {
		return bookDao.findUnique(bookName,new Integer( price), author.getId());
	}

	@Override
	public Page<Book> findAll(Pageable pageable, BookFilter filter) {
		return bookDao.findAll(new BookSpecifications(filter), pageable);
	}

	@Override
	public Page<Book> findAll(TypeFilter filter, Pageable pageable) {
		return bookDao.findAll(findByModelNameLike(filter), pageable);
	}

	private Specification<Book> findByModelNameLike(TypeFilter filter){
		return(root, query,cb)->{
			if(filter.getSearch().isEmpty())return null;
			return cb.like(cb.lower(root.get("bookName")), filter.getSearch().toLowerCase()+"%");
		};
	}

	@Override
	public Book_Form findForm(int id) {
		
		Book entity = findOne(id);
		
		Book_Form  form = new Book_Form();
		
		form.setPrice(String.valueOf(entity.getPrice()));
		
		form.setId(entity.getId());
		
		form.setBookName(entity.getBookName());
		
		form.setAuthor(entity.getAuthor());
		
		return form;
	}

	
	
}
