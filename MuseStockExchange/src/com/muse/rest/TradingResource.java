package com.muse.rest;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.muse.model.Order;
import com.muse.service.TradingServices;

@Path("/trade")
public class TradingResource {
	
	@EJB
	TradingServices tradingServices;
	
	@Path("/order")
	@POST
	@RolesAllowed("USER")
	public void recieveOrder(OrderElement orderElement){
		System.out.println(orderElement);
		tradingServices.createOrder(orderElement);
	}
	
	@Path("/orders")
	@GET
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getAllOrders() throws Exception{
		System.out.println("#TradingResource#getAllOrders " + tradingServices.getAllOrders());
		return tradingServices.getAllOrders();
	}
}

