package com.rocket.dao;

import java.util.List;
import com.rocket.minutes.model.Project;

public interface ProjectDAO {
	
	public Project getById(Long id);
	
	public List<Project> getAllProjects();
	
	public Project getByCompanyId(Long companyId);
}
