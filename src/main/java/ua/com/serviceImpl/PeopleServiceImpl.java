package ua.com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.CRUD.dao.PeopleDao;
import ua.com.entity.People;


@Service
public class PeopleServiceImpl {
	@Autowired
	private PeopleDao peopleDao;
	
	public List<People> findAll() {
		return peopleDao.findAll();
	}

	
	public People findOne(int id) {
		return peopleDao.findOne(id);
	}

	
	public People save(People people) {
		return peopleDao.saveAndFlush(people);
	}

	
	public void delete(int id) {
		peopleDao.delete(id);
	}

	
}
