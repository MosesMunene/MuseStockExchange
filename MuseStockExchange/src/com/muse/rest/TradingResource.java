package com.muse.rest;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import org.jboss.weld.context.ejb.Ejb;

import com.muse.model.Order;
import com.muse.model.Person;
import com.muse.model.Stock;
import com.muse.model.StockTransaction;
import com.muse.service.PersonServices;
import com.muse.service.TradingServices;

@Path("/trade")
public class TradingResource {

	@EJB
	TradingServices tradingServices;

	@Ejb
	PersonServices personServices;

	@Path("/order")
	@POST
	@RolesAllowed("USER")
	public void recieveOrder(OrderElement orderElement) {
		System.out.println(orderElement);
		tradingServices.createOrder(orderElement);
	}

	@Path("/orders")
	@GET
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<Order> getAllOrders() throws Exception {
		System.out.println("#TradingResource#getAllOrders " + tradingServices.getOpenOrders());
		return tradingServices.getOpenOrders();
	}

	@Path("/orderResponse")
	@POST
	@PermitAll
	@Consumes(MediaType.APPLICATION_JSON)
	public void responseToOrOrder(OrderResponseElement orderResponseElement) {
		System.out.println(orderResponseElement);
		
		tradingServices.respondToOrder(orderResponseElement.getOrderId(), orderResponseElement.getPersonId(),
				orderResponseElement.getType(), orderResponseElement.getUnits(), orderResponseElement.getPrice());
	}
	
	@Path("/quotes")
	@GET
	@PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public List<Quote> getQuotes(){
		List<Quote> quotes = new ArrayList<>();
		
		for(Stock stock : tradingServices.getAllStocks()){
			Quote quote = new Quote();
			StockTransaction latestTransaction = tradingServices.getLastestTransaction(stock);
			quote.setStockId(stock.getStockId());
			quote.setTotalDemand(tradingServices.getTotalDemand(stock));
			quote.setTotalSupply(tradingServices.getTotalSupply(stock));
			quote.setBestBid(tradingServices.getBestBid(stock).doubleValue());
			quote.setBestAsk(tradingServices.getBestAsk(stock).doubleValue());
			quote.setPrevClose(tradingServices.getPrevClose(stock).doubleValue());
			quote.setLastPrice(latestTransaction == null? 0 :latestTransaction.getPrice().doubleValue());
			quote.setChange(tradingServices.getChange(stock).doubleValue());
			quote.setTurnOver(tradingServices.getTurnOver(stock));
			quote.setDeals(tradingServices.getDeals(stock));
			quote.setTime(latestTransaction == null? null : latestTransaction.getTimeStamp());
			
			quotes.add(quote);
		}
		
		return quotes;
	}
}

class OrderResponseElement {
	private String personId;
	private String orderId;
	private String type;
	private String stockId;
	private long units;
	private double price;

	public OrderResponseElement() {
		// TODO Auto-generated constructor stub
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public long getUnits() {
		return units;
	}

	public void setUnits(long units) {
		this.units = units;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "OrderResponseElement [personId=" + personId + ", orderId=" + orderId + ", type=" + type + ", stockId="
				+ stockId + ", units=" + units + ", price=" + price + "]";
	}
}
