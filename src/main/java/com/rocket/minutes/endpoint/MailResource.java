package com.rocket.minutes.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocket.minutes.RequestBeans.MinutesRequestBean;
import com.rocket.minutes.services.MailerService;

@Service
@Path("/mail/")
public class MailResource {

	@Autowired
	private MailerService mailerService;
	
	@POST
	@Path("/")
	@Produces("application/json")
    @Consumes("application/json")
	public Response sendMinutesMail(MinutesRequestBean mrb){
		mailerService.sendMail(mrb);
		ResponseBuilder response = Response.status(200);
		response.entity("ok");
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
