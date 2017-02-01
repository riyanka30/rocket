package com.rocket.minutes.services;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.dao.PersonDAO;
import com.rocket.minutes.MinutesUtil;
import com.rocket.minutes.model.Company;
import com.rocket.minutes.model.Person;

@Service
public class PersonService {

	@Autowired
	CompanyService companyService;

	public List<Person> getAllPersons(){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		List<Person> persons=null;
		try{
			persons=session.getMapper(PersonDAO.class).getAllPersons();
			for(int i=0;i<persons.size();i++){
				Person p = persons.get(i);
				Company c=companyService.getCompanyById((long) p.getCompany_id());
				p.setCompany(c);
				System.out.println(persons.get(i));
			}}finally{
				session.close();
			}
		return persons;
	}

	public Person getPersonById(Long id){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		Person person=null;
		try{
			person=session.getMapper(PersonDAO.class).getById(id);
			Company c=companyService.getCompanyById((long) person.getCompany_id());
			person.setCompany(c);
			System.out.println(person);
		}finally{
			session.close();
		}
		return person;
	}

	public List<Person> getPersonByIdList(List<Long> personIds){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		List<Person> persons=null;
		try{
			persons=session.getMapper(PersonDAO.class).getByPersonIds(personIds);
			for(int i=0;i<persons.size();i++){
				Person p = persons.get(i);
				Company c=companyService.getCompanyById((long) p.getCompany_id());
				p.setCompany(c);
				System.out.println(persons.get(i));
			}}finally{
				session.close();
			}
		return persons;
	}

}
