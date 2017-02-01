package com.rocket.dao;

import java.util.List;

import com.rocket.minutes.model.Person;

public interface PersonDAO {
	
	public Person getById(Long id);
	
	public List<Person> getAllPersons();
	
	public List<Person> getByPersonIds(List<Long> personIds);
}
