package ua.com.specifications;

import java.util.ArrayList;
import java.util.List;



import java.util.regex.Pattern;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ua.com.dto.filter.BookFilter;
import ua.com.entity.Book;
import ua.com.entity.Model;


public class BookSpecifications  implements Specification<Book>{

	private final BookFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	private static final Pattern REG = Pattern.compile("^[0-9]+$");

	
	public BookSpecifications(BookFilter filter){
		super();
		this.filter = filter;
		if (REG.matcher(filter.getMax()).matches()) {
			filter.setMaxValue(new Integer(filter.getMax()));
		}
		if (REG.matcher(filter.getMin()).matches()) {
			filter.setMinValue(new Integer(filter.getMin()));
		}
	}
	
	private void findByPrice(Root<Book> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if (filter.getMaxValue() != 0) {
			predicates.add(cb.le(root.get("price"), filter.getMaxValue()));
		}
		if (filter.getMinValue() != 0) {
			predicates.add(cb.ge(root.get("price"), filter.getMinValue()));
		}
	}

	
	private void findByBookName(Root<Book> root,
			CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (!filter.getSearch().isEmpty()) {
			predicates.add(cb.like(root.get("bookName"), filter.getSearch()
					.toLowerCase() + "%"));
		}
	}
	
	private void findByAuthor(Root<Book> root, CriteriaQuery<?> query){
		
		if(!filter.getAuthorId().isEmpty()){
			predicates.add(root.get("author").in(filter.getAuthorId()));
		}
		
	}
	
	
	private void fetch(Root<Book> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			query.distinct(true);
			root.fetch("author");
		}
		
	}
	
	@Override
	public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		
		fetch(root, query);
		
		findByPrice(root,  query, cb);
		findByBookName(root, query, cb);
		findByAuthor(root, query);
		
		if(predicates.isEmpty())
			return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}
	
	
	
	
}
