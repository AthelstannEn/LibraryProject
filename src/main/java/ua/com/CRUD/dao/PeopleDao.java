package ua.com.CRUD.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.entity.People;

public interface PeopleDao extends JpaRepository<People, Integer> {

}
