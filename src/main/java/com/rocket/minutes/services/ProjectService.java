package com.rocket.minutes.services;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.dao.ProjectDAO;
import com.rocket.minutes.MinutesUtil;
import com.rocket.minutes.model.Company;
import com.rocket.minutes.model.Project;

@Service
public class ProjectService {

	@Autowired
	CompanyService companyService;

	public List<Project> getAllProjects(){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		List<Project> projects = null;
		try{
			projects=session.getMapper(ProjectDAO.class).getAllProjects();
			for(int i=0;i<projects.size();i++){
				Project p = projects.get(i);
				Company c=companyService.getCompanyById((long) p.getCompany_id());
				p.setCompany(c);
				System.out.println(p);
			}}finally{
				session.close();
			}
		return projects;
	}

	public Project getProjectById(Long id){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		Project project=null;
		try{
			project=session.getMapper(ProjectDAO.class).getById(id);
			Company c=companyService.getCompanyById((long) project.getCompany_id());
			project.setCompany(c);
			System.out.println(project);
		}finally{
			session.close();
		}
		return project;
	}

	public Project getProjectByCompanyId(Long companyId){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		Project project=null;
		try{
			project=session.getMapper(ProjectDAO.class).getByCompanyId(companyId);
			Company c=companyService.getCompanyById((long) project.getCompany_id());
			project.setCompany(c);
			System.out.println(project);
		}finally{
			session.close();
		}
		return project;
	}

}
