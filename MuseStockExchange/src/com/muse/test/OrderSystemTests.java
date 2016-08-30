package com.muse.test;

import java.time.Period;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.muse.model.Order;
import com.muse.model.OrderType;
import com.muse.model.Person;
import com.muse.model.Stock;

public class OrderSystemTests {
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MuseStockExchange");
		EntityManager em = emf.createEntityManager();
		
		new TestCreateBuyOrder().createBuyOrder(em);
	}
}

class TestCreateBuyOrder{
	
	public void createBuyOrder(EntityManager em){
		//find a favourite stock KENGEN!!
		Stock kegn = em.find(Stock.class, "KEGN");
		Person person = em.createNamedQuery("person.findByEmail", Person.class)
							.setParameter("email", "moses2@domain.com").getSingleResult();
		
		Order buyOrder = new Order();
		buyOrder.setType(OrderType.BUY);
		buyOrder.setStock(kegn);
		buyOrder.setPerson(person);
		buyOrder.setUnits(6000);
		buyOrder.setExpiry(buyOrder.getCreated().plus(Period.ofDays(5)));
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(buyOrder);
		tx.commit();
	}
}
