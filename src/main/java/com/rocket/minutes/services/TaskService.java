package com.rocket.minutes.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.dao.MapMeetingTasksDAO;
import com.rocket.dao.TaskDAO;
import com.rocket.minutes.MinutesUtil;
import com.rocket.minutes.model.Person;
import com.rocket.minutes.model.Task;
import com.rocket.minutes.model.TaskPerson;

@Service
public class TaskService {

	@Autowired
	PersonService personService;

	@Autowired
	MapTaskPersonsService mtps;

	public List<Task> getAllTasks(){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		List<Task> tasks=null;
		try{
			tasks=session.getMapper(TaskDAO.class).getAllTasks();
			for(int i=0;i<tasks.size();i++){
				System.out.println(tasks.get(i));
			}}finally{
				session.close();
			}
		return tasks;
	}

	public Task getTaskById(Long id){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		Task task=null;
		try{
			task=session.getMapper(TaskDAO.class).getById(id);
			List<TaskPerson> tp=mtps.getTaskPersonsByTaskId(id);
			List<Person> owners = new ArrayList<Person>();
			for(int i=0;i<tp.size();i++){
				Person p=personService.getPersonById(tp.get(i).getPerson_id());
				owners.add(p);
			}
			task.setOwners(owners);
			System.out.println(task);
		}finally{
			session.close();
		}
		return task;
	}

	/*public List<Task> getTaskByIdList(List<Long> taskIds){
		SqlSession session = MinutesUtil.getSqlSessionFactory();
		List<TaskPerson> tp=mtps.getTaskPersonsByTaskIds(taskIds);
		List<Task> result = new ArrayList<Task>();
		Map<Long, List<Long>> taskVsPersons = new HashMap<Long, List<Long>>();
		for(int i=0;i<tp.size();i++){
			Long tid=tp.get(i).getTask_id();
			if(taskVsPersons.containsKey(tid)){
				List<Long> temp = taskVsPersons.get(tid);
				temp.add(tp.get(i).getPerson_id());
			}else{
				taskVsPersons.put(tid, Arrays.asList(tp.get(i).getPerson_id()));
			}
		}
		Set<Long> keys=taskVsPersons.keySet();
		Iterator<Long> it = keys.iterator();
		while(it.hasNext()){
			Long tid=it.next();
			Task task=session.getMapper(TaskDAO.class).getById(tid);

		}
		List<Person> owners = new ArrayList<Person>();
		Task task=session.getMapper(TaskDAO.class).getById(tp.get(i).getTask_id());
		Person p=personService.getPersonById(tp.get(i).getPerson_id());
		owners.add(p);
		task.setOwners(owners);
		System.out.println(task);
		return result;
	}*/

	public List<Task> getTasksByMeetingId(Long id){
		SqlSession session = MinutesUtil.getSqlSessionFactory().openSession();
		List<Task> result =null;
		try{
			List<Long> taskIds=session.getMapper(MapMeetingTasksDAO.class).getTasksByMeetingId(id);
			result = new ArrayList<Task>();
			for(int i=0;i<taskIds.size();i++){
				Task task = getTaskById(taskIds.get(i));
				result.add(task);
			}
			System.out.println(result);
		}finally{
			session.close();
		}
		return result;
	}


}
