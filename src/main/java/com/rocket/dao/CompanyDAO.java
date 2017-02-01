package com.rocket.dao;

import java.util.List;
import com.rocket.minutes.model.Company;

public interface CompanyDAO {
	
	public Company getById(Long id);
	
	public List<Company> getAllCompanies();
}
