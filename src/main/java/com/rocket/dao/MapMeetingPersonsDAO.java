package com.rocket.dao;

import java.util.List;
import com.rocket.minutes.model.Company;

public interface MapMeetingPersonsDAO {
	
	public List<Long> getTasksByMeetingId(Long id);
	
	public List<Company> getAllCompanies();
}
