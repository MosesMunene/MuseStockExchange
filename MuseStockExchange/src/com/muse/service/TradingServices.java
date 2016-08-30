package com.muse.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.muse.model.Order;
import com.muse.model.OrderType;
import com.muse.model.Person;
import com.muse.model.Stock;
import com.muse.rest.OrderElement;

@Stateless
public class TradingServices {
	@PersistenceContext(unitName = "MuseStockExchange-JTA")
	EntityManager em;
	
	public TradingServices() {
		
	}
	
	public void createOrder(OrderElement orderElement ){
		// TODO Manage exceptions 
		
		//search for relevant entities from db
		Person person = em.find(Person.class, orderElement.getPersonId());
		Stock stock = em.find(Stock.class, orderElement.getStock());
		
		Order order = new Order();
		order.setPerson(person);
		order.setType(orderElement.getType().equals(OrderType.BUY.toString())? OrderType.BUY: OrderType.SELL);
		order.setStock(stock);
		order.setUnits(orderElement.getUnits());
		order.setPrice(new BigDecimal(orderElement.getPrice()));
		System.out.println("#TradingServices#createOrder " + order);
		em.persist(order);
	}
	
	public List<Order> getAllOrders(){
		TypedQuery<Order> getAllOrdersQuery = em.createNamedQuery("Order.getAllAorders", Order.class);
		return getAllOrdersQuery.getResultList();
	}
}
