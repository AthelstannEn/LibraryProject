package ua.com.CRUD.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.entity.Author;
import ua.com.entity.Book;

public interface BookDao extends JpaRepository<Book, Integer>, JpaSpecificationExecutor<Book>{

	Book findBybookName(String bookName);
	
	
	Book findBybookPrice(int price);
	
	
	@Query("selkect i from Auhro i where i.author.id=?1")
	List<Author> findByAuthorId(int id);
	
	
	
	@Query("select  a from Books a LEFT JOIN FETCH a.author")
	List<Book> findAll();
	
	
	@Query("select a from Books a LEFT JOIN FETCH a.author where a.id=?1 ")
	Book findOne(int id);
	
	@Query("select a FROM Books a WHERE a.bookName=?1 "
			+"  and  a.price=?2 "
			+" and a.author.id=?3")
	
	Book findUnique(String bookName,  int price, int authorId );
	
	
	
}
