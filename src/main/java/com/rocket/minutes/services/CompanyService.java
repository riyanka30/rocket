package com.rocket.minutes.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.rocket.dao.CompanyDAO;
import com.rocket.minutes.MinutesUtil;
import com.rocket.minutes.model.Company;

@Service
public class CompanyService {

	public List<Company> getAllCompanies(){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		List<Company> companies = new ArrayList<Company>();
		try{
			companies=session.getMapper(CompanyDAO.class).getAllCompanies();
			for(int i=0;i<companies.size();i++){
				System.out.println(companies.get(i));
			}}finally{
				session.close();
			}
		return companies;
	}

	public Company getCompanyById(Long id){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		Company company = new Company();
		try{
			company=session.getMapper(CompanyDAO.class).getById(id);
			System.out.println(company);
		}finally{
			session.close();
		}
		return company;
	}

}
