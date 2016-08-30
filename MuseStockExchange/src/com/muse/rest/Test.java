package com.muse.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.muse.model.Person;


// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/")
public class Test {

	// This method is called if TEXT_PLAIN is request
	@Path("/persons")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response getPersons() {
		List<Person> personsList = new ArrayList<>();
		personsList.add(new Person());
		personsList.add(new Person());
		personsList.add(new Person());
		
		GenericEntity<List<Person>> genericPersonList = new GenericEntity<List<Person>>(personsList){};
		Response.ResponseBuilder responseBuilder = Response.ok(genericPersonList);
		return responseBuilder.build();
	}
	
	@Path("/person")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Response getPerson() {
		Person person = new Person();
		Response.ResponseBuilder responseBuilder = Response.ok(person);
		return responseBuilder.build();
	}

	// This method is called if XML is request
	@GET
	@Produces(MediaType.TEXT_XML)
	@PermitAll
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
	}

	// This method is called if HTML is request
	@GET
	@Produces(MediaType.TEXT_HTML)
	@PermitAll
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body><h1>" + "Hello Jersey" + "</body></h1>"
				+ "</html> ";
	}
}