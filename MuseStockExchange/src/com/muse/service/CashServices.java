package com.muse.service;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.muse.model.CashTransaction;
import com.muse.model.CashTransactionType;
import com.muse.model.Person;

@Stateless
public class CashServices {
	@PersistenceContext(unitName = "MuseStockExchange-JTA")
	private EntityManager em;
	
	@EJB
	PersonServices personServices;

	public BigDecimal getBalance(Person person) {
		// get transactions where this person is 'from_person' (debits)
		// get transaction where this person is 'to_person' (credits)
		// balance = credits - debits
		// Might want to create a balances table instead of calculating the balance from transactions
		
		BigDecimal debits = em.createQuery("select sum(ct.amount) from CashTransaction ct where ct.debit = :person", BigDecimal.class)
											.setParameter("person", person)
											.getSingleResult();
		BigDecimal credits = em.createQuery("select sum(ct.amount) from CashTransaction ct where ct.credit = :person", BigDecimal.class)
											.setParameter("person", person)
											.getSingleResult();
		
		
		debits = debits == null? new BigDecimal(0): debits;
		credits = credits == null? new BigDecimal(0): credits;
		
		return credits.subtract(debits);
	}

	public BigDecimal cashDeposit(String personId, double amount) {
		Person person = personServices.getByPersonId(personId);
		Person system = personServices.getSystemPerson();
		
		System.out.println(person);
		System.out.println(system);
		
		CashTransaction cashTransaction = new CashTransaction();
		cashTransaction.setCredit(person);
		cashTransaction.setDebit(system);
		cashTransaction.setType(CashTransactionType.DEPOSIT);
		cashTransaction.setAmount(new BigDecimal(amount));
		
		em.persist(cashTransaction);
		
		return getBalance(person);
	}

}
