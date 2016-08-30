package com.muse.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.muse.model.Stock;

public class StockTests {
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MuseStockExchange");
		EntityManager em = emf.createEntityManager();
		
		new TestGetAllStocks().getAllStocks(em);
	}
}

class TestGetAllStocks{
	public void getAllStocks(EntityManager em){
		TypedQuery<Stock> getAllStocksQuery = em.createQuery("select s from Stock s ", Stock.class);
		
		for(Stock stock : getAllStocksQuery.getResultList()){
			System.out.println(stock);
		}
	}
}
