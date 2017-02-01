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

import com.rocket.minutes.model.Meeting;
import com.rocket.minutes.services.MeetingService;

@Service
@Path("/meeting/")
public class MeetingsResource {

	@Autowired
	private MeetingService meetingService;
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllMeetings(){
		List<Meeting> result=meetingService.getAllMeetings();
		ResponseBuilder response = Response.status(200);
		response.entity(result);
		response.header("Access-Control-Allow-Origin", "*");
		return response.build();
	}
	
	@GET
	@Path("/full/{id}")
	@Produces("application/json")
	public Response getMeetingFullInfoById(@PathParam("id")Long id){
		Meeting result=meetingService.getMeetingById(id);
		ResponseBuilder response = Response.status(200);
		response.entity(result);
		response.header("Access-Control-Allow-Origin", "*");
		return response.build();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getMeetingById(@PathParam("id")Long id){
		Meeting result=meetingService.getMeetingById(id);
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
