package com.rocket.minutes.model;

import java.util.Date;
import java.util.List;

public class Meeting {
	private int id;
	private String title;
	private Date schedule;
	private String location;
	private boolean minTaken;
	private int client_id;
	private int project_id;
	private Company client;
	private Project project;
	private List<Task> tasks;
	
	public List<Task> getTasks() {
		return tasks;
	}
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Meeting [id=" + id + ", title=" + title + ", schedule=" + schedule + ", location=" + location
				+ ", minTaken=" + minTaken + ", client_id=" + client_id + ", project_id=" + project_id + ", client="
				+ client + ", project=" + project + ", tasks=" + tasks + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getSchedule() {
		return schedule;
	}
	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isMinTaken() {
		return minTaken;
	}
	public void setMinTaken(boolean minTaken) {
		this.minTaken = minTaken;
	}
	public int getClient_id() {
		return client_id;
	}
	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}
	public int getProject_id() {
		return project_id;
	}
	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}
	public Company getClient() {
		return client;
	}
	public void setClient(Company client) {
		this.client = client;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
}
