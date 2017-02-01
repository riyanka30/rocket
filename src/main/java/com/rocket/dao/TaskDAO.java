package com.rocket.dao;

import java.util.List;
import com.rocket.minutes.model.Task;

public interface TaskDAO {
	
	public Task getById(Long id);
	
	public List<Task> getAllTasks();
}
