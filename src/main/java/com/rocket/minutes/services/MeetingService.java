package com.rocket.minutes.services;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.dao.MeetingDAO;
import com.rocket.minutes.MinutesUtil;
import com.rocket.minutes.model.Company;
import com.rocket.minutes.model.Meeting;
import com.rocket.minutes.model.Project;
import com.rocket.minutes.model.Task;

@Service
public class MeetingService {

	@Autowired
	CompanyService companyService;

	@Autowired
	ProjectService projectService;

	@Autowired
	TaskService taskService;

	public List<Meeting> getAllMeetings(){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		List<Meeting> meetings = null;
		try{
			meetings=session.getMapper(MeetingDAO.class).getAllMeetings();
			for(int i=0;i<meetings.size();i++){
				Meeting m = meetings.get(i);
				Company c=companyService.getCompanyById((long) m.getClient_id());
				m.setClient(c);
				Project p = projectService.getProjectById((long)m.getProject_id());
				m.setProject(p);
				System.out.println(m);
			}
		}finally{
			session.close();
		}
		return meetings;
	}

	public Meeting getMeetingById(Long id){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		Meeting m = null;
		try{
			m=session.getMapper(MeetingDAO.class).getById(id);
			Company c=companyService.getCompanyById((long) m.getClient_id());
			m.setClient(c);
			Project p = projectService.getProjectById((long)m.getProject_id());
			m.setProject(p);
			List<Task> tasks=taskService.getTasksByMeetingId(id);
			m.setTasks(tasks);
			System.out.println(m);
		}finally{
			session.close();
		}
		return m;
	}

	public MeetingService() {}

}
