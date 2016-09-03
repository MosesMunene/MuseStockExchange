package com.muse.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.muse.model.AuthAccessElement;
import com.muse.model.AuthLoginElement;
import com.muse.model.Person;
import com.muse.model.Role;
import com.muse.service.PersonServices;

@Path("/auth")
public class AuthResource {

	@EJB
	private PersonServices personServices;
	final static Logger logger = Logger.getLogger(AuthResource.class);

	public AuthResource() {
		// TODO Auto-generated constructor stub
	}

	@POST
	@Path("login")
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AuthAccessElement login(@Context HttpServletRequest request, AuthLoginElement loginElement) {
		
		return personServices.authenticate(loginElement);
	}

	@POST
	@Path("register")
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public AuthAccessElement createAccount(@Context HttpServletRequest request, AuthLoginElement element) {
		AuthAccessElement accessElement = personServices.createUser(element);
		return accessElement;
	}

	@POST
	@Path("checkLoginStatus")
	@RolesAllowed("USER")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void checkLogInStatus(@Context HttpHeaders headers) {
		// String token = headers.getRequestHeader("token").get(0);
		// System.out.println(token);
		// AuthAccessElement accessElement = authService.createUser(element);
		return;
	}

	@POST
	@Path("logout")
	@Consumes(MediaType.APPLICATION_JSON)
	public void logout(@Context HttpServletRequest request, AuthAccessElement accessElement) {	
		personServices.deauthenticate(accessElement);
		return;
	}

	// @Path("/persons")
	// @GET
	// @Produces(MediaType.APPLICATION_JSON)
	// public Person getPersons() {
	// List<Person> personsList = new ArrayList<>();
	// personsList.add(new Person("moses@foo.com", "moses", "munene"));
	// personsList.add(new Person("moses@foo.com", "moses", "munene"));
	// personsList.add(new Person("moses@foo.com", "moses", "munene"));
	// return new Person("moses@foo.com", "moses", "munene");
	// }
}