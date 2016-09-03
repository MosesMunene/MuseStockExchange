package com.muse.rest;

import java.math.BigDecimal;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.muse.model.Person;
import com.muse.service.CashServices;
import com.muse.service.PersonServices;

/*
 * Following these principles http://racksburg.com/choosing-an-http-status-code/
 */

@Path("/account")
public class AccountResource {
	@EJB
	CashServices cashServices;
	
	@EJB
	PersonServices personServices;
	
	public AccountResource() {
		// Auto-generated constructor stub
	}

	@Path("/balance")
	@PermitAll
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBalance(@QueryParam("personId") String personId){
		System.out.println("#AccountResource#getBalance" + personId);
		ResponseBuilder builder = null;
		Person person = personServices.getByPersonId(personId);
		BigDecimal balance = cashServices.getBalance(person);
		
		if (balance == null) {
			builder = Response.noContent();
			return builder.build();
		}
		JsonObject repsonseObj = Json.createObjectBuilder()
				.add("personId", personId)
				.add("balance", balance.toString())
				.build();
		builder = Response.ok(repsonseObj, MediaType.APPLICATION_JSON_TYPE);
		return builder.build();
	}
	
	@Path("/deposit")
	@PermitAll
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cashDeposit(CashDepositElement cashDepositElement) {
		ResponseBuilder builder = null;

		BigDecimal balance = cashServices.cashDeposit(cashDepositElement.getPersonId(), cashDepositElement.getAmount());

		if (balance == null) {
			builder = Response.noContent();
			return builder.build();
		}
		JsonObject repsonseObj = Json.createObjectBuilder()
				.add("personId", cashDepositElement.getPersonId())
				.add("balance", balance.toString())
				.build();
		builder = Response.ok(repsonseObj, MediaType.APPLICATION_JSON_TYPE);
		return builder.build();
	}
}

class CashDepositElement {
	private String personId;
	private double amount;

	public double getAmount() {
		return amount;
	}

	public String getPersonId() {
		return personId;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
}
