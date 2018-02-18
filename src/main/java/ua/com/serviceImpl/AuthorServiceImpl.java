package ua.com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.CRUD.dao.AuthorDao;
import ua.com.CRUD.dao.BookDao;
import ua.com.dto.filter.TypeFilter;
import ua.com.entity.Author;
import ua.com.service.Author_Service;


@Service
public class AuthorServiceImpl implements Author_Service{

	
	@Autowired
	private AuthorDao authorDao;
	
	@Autowired
	private BookDao bookDao;
	
	
	
	
	@Override
	public void save(Author author) {
		
		authorDao.save(author);
		
	}

	@Override
	public List<Author> findAll() {
		return authorDao.findAll();
	}

	@Override
	public Author findOne(int id) {
		return authorDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		authorDao.delete(id);
			
	}

	@Override
	public Author findByAuthor(String authorname) {
		return authorDao.findByAuthor(authorname);
	}

	@Override
	public List<Author> findByBooksId(int id) {
		return authorDao.findByBooksId(id);
	}

	@Override
	public Page<Author> findALl(TypeFilter filter, Pageable pageable) {
		return authorDao.findAll(findByTypeLike(filter), pageable);
	}

	
	private Specification<Author> findByTypeLike(TypeFilter filter){
		return(root, query,cb)->{
			if(filter.getSearch().isEmpty())return null;
			return cb.like(cb.lower(root.get("type")), filter.getSearch().toLowerCase()+"%");
		};
	}
	
}
