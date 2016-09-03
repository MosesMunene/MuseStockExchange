package com.muse.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.muse.model.Person;
import com.muse.model.PersonStock;
import com.muse.model.Stock;

public class PersonStockTests {
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("MuseStockExchange");
		EntityManager em = emf.createEntityManager();
	
		new TestSavePersonStock().savePersonStock(em);
	}
}

class TestSavePersonStock{
	
	public void savePersonStock(EntityManager em){
		Stock kegn = em.find(Stock.class, "KEGN");
		Person person = em.createNamedQuery("person.findByEmail", Person.class)
							.setParameter("email", "moses2@domain.com").getSingleResult();
		
		
		PersonStock personStock = new PersonStock();
		personStock.setPerson(person);
		personStock.setStock(kegn);
		personStock.setUnits(1000);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(personStock);
		tx.commit();
	}
}