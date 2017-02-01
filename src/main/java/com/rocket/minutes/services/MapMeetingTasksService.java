package com.rocket.minutes.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.dao.MapMeetingTasksDAO;
import com.rocket.minutes.MinutesUtil;
import com.rocket.minutes.model.Task;

@Service
public class MapMeetingTasksService {

	@Autowired
	TaskService taskService;

	/*public List<Company> getAllCompanies(){
		SqlSession session = MinutesUtil.getSqlSessionFactory();
		List<Company> companies=session.getMapper(CompanyDAO.class).getAllCompanies();
		for(int i=0;i<companies.size();i++){
			System.out.println(companies.get(i));
		}
		return companies;
	}*/

	public List<Task> getTasksByMeetingId(Long id){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		List<Task> result = new ArrayList<Task>();
		try{
			List<Long> tasks=session.getMapper(MapMeetingTasksDAO.class).getTasksByMeetingId(id);

			for(int i=0;i<tasks.size();i++){
				Task t = taskService.getTaskById(tasks.get(i));
				result.add(t);
			}
		}finally{
			session.close();
		}
		System.out.println(result);
		return result;
	}

}
