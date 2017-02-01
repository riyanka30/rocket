package com.rocket.dao;

import java.util.List;
import com.rocket.minutes.model.Meeting;

public interface MeetingDAO {
	
	public Meeting getById(Long id);
	
	public List<Meeting> getAllMeetings();
}
