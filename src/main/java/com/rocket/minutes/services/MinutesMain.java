package com.rocket.minutes.services;

public class MinutesMain {

	public static void main(String[] args) {
	
//		PersonService ps = new PersonService();
//		ps.getAllPersons();
//		ps.getPersonById(1l);
//		
//		CompanyService cs = new CompanyService();
//		cs.getAllCompanies();
//		cs.getCompanyById(1l);
//		
		TaskService ts = new TaskService();
		ts.getAllTasks();
		ts.getTaskById(1l);
		
/*		ProjectService ps = new ProjectService();
		ps.getAllProjects();
		ps.getProjectById(1l);*/
		
		MeetingService ms = new MeetingService();
		ms.getAllMeetings();
		ms.getMeetingById(1l);
	}

}
