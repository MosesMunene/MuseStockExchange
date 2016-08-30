package com.muse.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.muse.model.Credential;
import com.muse.model.Person;

public class UserManagementTests {
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	
	public static void main(String[] args){
		entityManagerFactory = Persistence.createEntityManagerFactory("MuseStockExchange");
		entityManager = entityManagerFactory.createEntityManager();
		
		testPersonCreation(entityManager);
		
		entityManager.close();
	}
	
	public static void testPersonCreation(EntityManager em){
		Credential credential = new Credential();
		credential.setUserName("test@domain.com");
		credential.setPassword("password");
		
		Person person = new Person();
		person.setCredential(credential);
	
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(credential);
		em.persist(person);
		tx.commit();
	}
}
