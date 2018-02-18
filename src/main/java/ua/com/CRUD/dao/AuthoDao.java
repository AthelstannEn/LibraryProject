package ua.com.CRUD.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.entity.Author;

public interface AuthoDao extends JpaRepository<Author, Integer>, JpaSpecificationExecutor<Author> {
	
	
	Author findByAuthor(String authorname);
	
	@Query("select i from Books i join i.book m where m.id=?1")
	List<Author> findByBooksId(int id);

}
