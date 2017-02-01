package com.rocket.dao;

import java.util.List;

public interface MapMeetingTasksDAO {
	
	public List<Long> getTasksByMeetingId(Long meetingId);
}
