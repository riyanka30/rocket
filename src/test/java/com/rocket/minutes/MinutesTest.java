package com.rocket.minutes;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.rocket.minutes.RequestBeans.MinutesRequestBean;
import com.rocket.minutes.RequestBeans.TaskRequestBean;
import com.rocket.minutes.model.Meeting;
import com.rocket.minutes.services.CompanyService;
import com.rocket.minutes.utils.MailerService;
import com.rocket.minutes.services.MeetingService;
import com.rocket.minutes.services.PersonService;
import com.rocket.minutes.services.ProjectService;
import com.rocket.minutes.services.TaskService;

@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-config.xml" })
public class MinutesTest
{
	@Autowired
	PersonService personService;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	MeetingService meetingService;
	
	@Autowired
	MailerService mailerService;
	
	//@Test
    public void testApp()
    {
//		PersonService ps = new PersonService();
//		ps.getAllPersons();
//		ps.getPersonById(1l);
//		
//		CompanyService cs = new CompanyService();
//		cs.getAllCompanies();
//		cs.getCompanyById(1l);
//		
//		TaskService ts = new TaskService();
//		ts.getAllTasks();
//		ts.getTaskById(1l);
		
	//	projectService.getAllProjects();
	//	projectService.getProjectById(1l);
	//	projectService.getProjectByCompanyId(1l);
/*	
		meetingService.getAllMeetings();*/
		Meeting m=meetingService.getMeetingById(1l);
	//	List<Task> res=taskService.getTasksByMeetingId(1l);
		System.out.println("-----------------result\n"+m);
    }
	
	@Test
	public void testMail(){
		MinutesRequestBean mrb = new MinutesRequestBean();
		mrb.setTitle("Discussion on SRR3");
		mrb.setClient("Intel");
		mrb.setProject("SRR3");
		mrb.setTime("Wed 25th January, 2017");
		mrb.setLocation("Intel office, Bangalore");
		mrb.setChairP("Ranjeet Pundlik");
		mrb.setAttendees("rdagaonk@akamai.com,sunny.pundlik@gmail.com");
		
		List<TaskRequestBean> trb = new ArrayList<TaskRequestBean>();
		TaskRequestBean t1=new TaskRequestBean();
		t1.setTitle("canteen work");
		t1.setDescription("flooring work description");
		t1.setOwners("Ranjeet");
		t1.setTarget("2 Feb 2017");
		t1.setStatus("complete");
		t1.setMinutes("sample minutes kjksdjfkfsjkjshfksksfksfkfk");
		
		TaskRequestBean t2=new TaskRequestBean();
		t2.setTitle("canteen work");
		t2.setDescription("flooring work description");
		t2.setOwners("Ranjeet");
		t2.setTarget("2 Feb 2017");
		t2.setStatus("pending");
		t2.setMinutes("sample minutes kjksdjfkfsjkjshfksksfksfkfk");
		
		TaskRequestBean t3=new TaskRequestBean();
		t3.setTitle("canteen work");
		t3.setDescription("flooring work description");
		t3.setOwners("Ranjeet");
		t3.setTarget("2 Feb 2017");
		t3.setStatus("in progress");
		t3.setMinutes("sample minutes kjksdjfkfsjkjshfksksfksfkfk");
		
		trb.add(t1);trb.add(t2);trb.add(t3);
		mrb.setTopics(trb);
		mailerService.sendMail(mrb);
	}
}
