package com.rocket.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.rocket.minutes.model.TaskPerson;

public interface MapTaskPersonsDAO {
	
	public List<TaskPerson> getPersonsIdByTaskIds(@Param("taskIds")List<Long> taskIds);
	
}
