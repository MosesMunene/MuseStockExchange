package com.muse.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.muse.model.Stock;

@Stateless
public class StockServices {
	@PersistenceContext(unitName = "MuseStockExchange-JTA")
	EntityManager em;
	
	public StockServices() {
		// Auto-generated constructor stub
	}
	
	public Stock getStockById(String stockId){
		return em.find(Stock.class, stockId);
	}
}
