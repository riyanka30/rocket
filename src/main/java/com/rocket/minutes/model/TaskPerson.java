package com.rocket.minutes.model;

public class TaskPerson {
	private Long task_id;
	private Long person_id;
	
	@Override
	public String toString() {
		return "TaskPerson [task_id=" + task_id + ", person_id=" + person_id + "]";
	}
	public Long getTask_id() {
		return task_id;
	}
	public void setTask_id(Long task_id) {
		this.task_id = task_id;
	}
	public Long getPerson_id() {
		return person_id;
	}
	public void setPerson_id(Long person_id) {
		this.person_id = person_id;
	}
	
}
