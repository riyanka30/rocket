package com.rocket.minutes.endpoint;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.minutes.model.Project;
import com.rocket.minutes.model.Task;
import com.rocket.minutes.services.ProjectService;
import com.rocket.minutes.services.TaskService;

@Service
@Path("/task/")
public class TaskResource {

	@Autowired
	private TaskService taskService;
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllTasks(){
		List<Task> result=taskService.getAllTasks();
		ResponseBuilder response = Response.status(200);
		response.entity(result);
		response.header("Access-Control-Allow-Origin", "*");
		return response.build();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getTaskById(@PathParam("id")Long id){
		Task result=taskService.getTaskById(id);
		ResponseBuilder response = Response.status(200);
		response.entity(result);
		response.header("Access-Control-Allow-Origin", "*");
		return response.build();
	}
	
	@GET
	@Path("/health")
	@Produces("application/json")
	public Response getHealth(){
		ResponseBuilder response = null;
		response = Response.status(200);
		response.entity("{\"alive\" : true}");
		response.header("Access-Control-Allow-Origin", "*");
		return response.build();
	}
	
	
}
