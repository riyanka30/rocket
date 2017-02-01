package com.rocket.minutes.model;

import java.util.Date;
import java.util.List;

public class Task {

	private int id;
	private String title;
	private String description;
	private Date target;
	private int status;
	private String minutes;
	private List<Person> owners;
	
	public List<Person> getOwners() {
		return owners;
	}
	public void setOwners(List<Person> owners) {
		this.owners = owners;
	}
	public int getId() {
		return id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getTarget() {
		return target;
	}
	public void setTarget(Date target) {
		this.target = target;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", description=" + description + ", target=" + target
				+ ", status=" + status + ", minutes=" + minutes + ", owners=" + owners + "]";
	}
}
