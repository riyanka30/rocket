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

import com.rocket.minutes.model.Company;
import com.rocket.minutes.services.CompanyService;

@Service
@Path("/company/")
public class CompanyResource {

	@Autowired
	private CompanyService companyService;
	
	@GET
	@Path("/all")
	@Produces("application/json")
	public Response getAllCompanies(){
		List<Company> result=companyService.getAllCompanies();
		ResponseBuilder response = Response.status(200);
		response.entity(result);
		response.header("Access-Control-Allow-Origin", "*");
		return response.build();
	}
	
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response getCompanyById(@PathParam("id")Long id){
		Company result=companyService.getCompanyById(id);
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
