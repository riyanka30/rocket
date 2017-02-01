package com.rocket.minutes.RequestBeans;

import java.util.List;

public class MinutesRequestBean {
	
	private String title;
	private String time;
	private String location;
	private String client;
	private String project;
	private String chairP;
	private String attendees;
	private List<TaskRequestBean> topics;
	
	public List<TaskRequestBean> getTopics() {
		return topics;
	}
	public void setTopics(List<TaskRequestBean> topics) {
		this.topics = topics;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getChairP() {
		return chairP;
	}
	public void setChairP(String chairP) {
		this.chairP = chairP;
	}
	public String getAttendees() {
		return attendees;
	}
	public void setAttendees(String attendees) {
		this.attendees = attendees;
	}
	
}
