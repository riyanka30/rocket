package com.rocket.minutes.services;

import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.dao.MapTaskPersonsDAO;
import com.rocket.minutes.MinutesUtil;
import com.rocket.minutes.model.TaskPerson;

@Service
public class MapTaskPersonsService {

	@Autowired
	TaskService taskSerice;

	@Autowired
	PersonService personService;

	public List<TaskPerson> getTaskPersonsByTaskId(Long taskId){
		List<Long> taskIds = Arrays.asList(taskId);
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		List<TaskPerson> taskPersons = null;
		try{
			taskPersons=session.getMapper(MapTaskPersonsDAO.class).getPersonsIdByTaskIds(taskIds);
		}finally{
			session.close();
		}
		return taskPersons;
	}

	public List<TaskPerson> getTaskPersonsByTaskIds(List<Long> taskIds){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		List<TaskPerson> taskPersons = null;
		try{
			taskPersons=session.getMapper(MapTaskPersonsDAO.class).getPersonsIdByTaskIds(taskIds);
		}finally{
			session.close();
		}
		return taskPersons;
	}


}
