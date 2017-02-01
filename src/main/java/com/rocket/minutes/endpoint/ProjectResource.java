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
import com.rocket.minutes.services.ProjectService;

@Service
@Path("/project/")
public class ProjectResource {

	@Autowired
	private ProjectService projectService;
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllProjects(){
		List<Project> result=projectService.getAllProjects();
		ResponseBuilder response = Response.status(200);
		response.entity(result);
		response.header("Access-Control-Allow-Origin", "*");
		return response.build();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getProjectById(@PathParam("id")Long id){
		Project result=projectService.getProjectById(id);
		ResponseBuilder response = Response.status(200);
		response.entity(result);
		response.header("Access-Control-Allow-Origin", "*");
		return response.build();
	}
	
	@GET
	@Path("/{companyid}")
	@Produces("application/json")
	public Response getProjectByCompanyId(@PathParam("id")Long companyId){
		Project result=projectService.getProjectByCompanyId(companyId);
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
