package com.muse.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.muse.model.CashTransaction;
import com.muse.model.CashTransactionType;
import com.muse.model.Order;
import com.muse.model.OrderStatus;
import com.muse.model.OrderType;
import com.muse.model.Person;
import com.muse.model.PersonStock;
import com.muse.model.Stock;
import com.muse.model.StockTransaction;
import com.muse.rest.OrderElement;

@Stateless
public class TradingServices {
	@PersistenceContext(unitName = "MuseStockExchange-JTA")
	private EntityManager em;
	
	@EJB
	private PersonServices personServices;
	
	@EJB
	private StockServices stockServices;
	
	@EJB
	private CashServices cashServices;
	
	public TradingServices() {
		
	}
	
	public Order getOrderById(String orderId) {
		return em.find(Order.class, orderId);
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
		order.setStatus(OrderStatus.OPEN);
		System.out.println("#TradingServices#createOrder " + order);
		em.persist(order);
	}
	
	public List<Order> getOpenOrders(){
		TypedQuery<Order> getAllOrdersQuery = em.createNamedQuery("Order.getOrdersFilterByStatus", Order.class)
				.setParameter("status", OrderStatus.OPEN);
		return getAllOrdersQuery.getResultList();
	}
	
	public void respondToOrder(String orderId, String responderId, String responseType, long units, double price){
		System.out.println("#TradingServices#respondToOrder");
		
		Order order = this.getOrderById(orderId);
		Person responder = personServices.getByPersonId(responderId);
		Person whoMadeOrder = order.getPerson();
		Stock stock = order.getStock();
		
		if(responseType.equals(OrderType.BUY.toString())){
			if(hasEnoughCashToPurchase(responder, order, units)== false){
				throw new RuntimeException();
			}
			this.addStock(responder, stock, units);
			this.subtractStock(whoMadeOrder, stock, units);
			this.recordStockTransaction(whoMadeOrder, responder, stock, units, new BigDecimal(price));
			this.recordCashTransaction(whoMadeOrder, responder, new BigDecimal(price).multiply(new BigDecimal(units)));
			
		}
		if (responseType.equals(OrderType.SELL.toString())){
			if(hasEnoughStockTosell(responder, stock, units) == false){
				System.out.println("#TradingServices#respondToOrder2");
				throw new RuntimeException();
			}
			this.addStock(whoMadeOrder, stock, units);
			this.subtractStock(responder, stock, units);
			this.recordStockTransaction(responder, whoMadeOrder, stock, units, new BigDecimal(price));
			this.recordCashTransaction(responder, whoMadeOrder, new BigDecimal(price).multiply(new BigDecimal(units)));
		}
		
		order.setUnits(order.getUnits() - units);
		if(order.getUnits() == 0){
			order.setStatus(OrderStatus.CLOSED);
		}
	}
	
	public void addStock(Person person, Stock stock, long units){
		System.out.println("#TradingServices#addStock");
		String jpql = "select ps from PersonStock ps where ps.person = :person and ps.stock = :stock";
		TypedQuery<PersonStock> personStockQuery = em.createQuery(jpql, PersonStock.class)
				.setParameter("person", person).setParameter("stock", stock);
		
		PersonStock personStock = null;
		try{
			personStock = personStockQuery.getSingleResult();
		}catch (NoResultException nre){
			// This means that person did not have that stock in the first place
			person.getPersonStockList().add(new PersonStock(stock, units));
			em.merge(person);
			return;
		}
		
		personStock.addUnits(units);
		em.merge(personStock);
	}
	
	public void subtractStock(Person person, Stock stock, long units){
		System.out.println("#TradingServices#subtractStock");
		String jpql = "select ps from PersonStock ps where ps.person = :person and ps.stock = :stock";
		TypedQuery<PersonStock> personStockQuery = em.createQuery(jpql, PersonStock.class)
				.setParameter("person", person).setParameter("stock", stock);
		PersonStock personStock = personStockQuery.getSingleResult();
		
		if(personStock != null){
			personStock.subtractUnits(units);		
			if(personStock.getUnits() <= 0){
				person.getPersonStockList().remove(personStock);
				em.merge(person);
			}
		}	
	}

	
	public void recordStockTransaction(Person seller, Person buyer, Stock stock, long units, BigDecimal price){
		System.out.println("#TradingServices#recordStockTransaction");
		StockTransaction stockTransaction = new StockTransaction();
		stockTransaction.setBuyer(buyer);
		stockTransaction.setSeller(seller);
		stockTransaction.setStock(stock);
		stockTransaction.setUnits(units);
		stockTransaction.setPrice(price);
		em.persist(stockTransaction);
	}
	
	public void recordCashTransaction(Person credit, Person debit, BigDecimal amount){
		System.out.println("#TradingServices#recordCashTransaction");
		CashTransaction cashTransaction = new CashTransaction();
		cashTransaction.setCredit(credit);
		cashTransaction.setDebit(debit);
		cashTransaction.setAmount(amount);
		cashTransaction.setType(CashTransactionType.TRANSFER);
		em.persist(cashTransaction);
	}
	
	public boolean hasEnoughCashToPurchase(Person person, Order order, long units){
		System.out.println("#TradingServices#hasEnoughToPurchase");
		boolean hasEnough = false;
		
		BigDecimal orderValue = order.getPrice().multiply(new BigDecimal(units));
		BigDecimal currentBalance = cashServices.getBalance(person);
		
		hasEnough = orderValue.compareTo(currentBalance) <= 0;
		
		return hasEnough;
	}
	
	public boolean hasEnoughStockTosell(Person person, Stock stock, long units){
		System.out.println("#TradingServices#hasEnoughStockToSell");
		boolean hasEnough = false;
		
		String jpql = "select ps from PersonStock ps where ps.person = :person and ps.stock = :stock";
		TypedQuery<PersonStock> personStockQuery = em.createQuery(jpql, PersonStock.class)
				.setParameter("person", person).setParameter("stock", stock);
		PersonStock personStock = personStockQuery.getSingleResult();
		
		Long desiredUnits = units;
		Long ownedUnits = personStock.getUnits();
		
		hasEnough = desiredUnits <= ownedUnits;
		
		return hasEnough;
	}
	
	public List<Stock> getAllStocks(){
		TypedQuery<Stock> query = em.createQuery("select s from Stock s", Stock.class);
		return query.getResultList();
	}
	
	public long getTotalDemand(Stock stock){
		String jpql = "select sum(o.units) from Order o where o.stock = :stock and o.type = :type";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class)
				.setParameter("stock", stock)
				.setParameter("type", OrderType.BUY);
		
		long result = 0;
		try{
			result =  query.getSingleResult();
		}catch (NullPointerException npe){
			//Turns out the sum function returns null and not zero when there is no match
			//I read somewhere that catching exceptions is expensive; research more on this
			//Swalowing the npe
			result = 0;
		}
		return result;
	}
	
	public long getTotalSupply(Stock stock){
		String jpql = "select sum(o.units) from Order o where o.stock = :stock and o.type = :type";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class)
				.setParameter("stock", stock)
				.setParameter("type", OrderType.SELL);
		
		long result = 0;
		try{
			result =  query.getSingleResult();
		}catch (NullPointerException npe){
			//Turns out the sum function returns null and not zero when there is no match
			//I read somewhere that catching exceptions is expensive; research more on this
			//Swalowing the npe
			result = 0;
		}
		return result;
	}
	
	public BigDecimal getBestBid(Stock stock){
		String jpql = "select max(o.price) from Order o where o.stock = :stock and o.type = :type";
		TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class)
				.setParameter("stock", stock)
				.setParameter("type", OrderType.BUY);
		
		BigDecimal result = query.getSingleResult();
		return result = result != null? result : new BigDecimal(0);
	}
	
	public BigDecimal getBestAsk(Stock stock){
		String jpql = "select max(o.price) from Order o where o.stock = :stock and o.type = :type";
		TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class)
				.setParameter("stock", stock)
				.setParameter("type", OrderType.SELL);
		
		BigDecimal result = query.getSingleResult();
		return result = result != null? result : new BigDecimal(0);
	}
	
	public BigDecimal getPrevClose(Stock stock){
		// The price of yesterdays closing trade
		String jpql = "select st.price from  StockTransaction st "
				+ "where st.stock = :stock "
				+ " and st.timeStamp between :yesterdayOpen and :yesterdayClose"
				+ " order by st.timeStamp desc";
		
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterdayOpen = LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 9,0,0)
				.minus(Duration.ofDays(1)); 
		LocalDateTime yesterdayClose = LocalDateTime.of(today.getYear(), today.getMonth(), today.getDayOfMonth(), 15,0,0)
				.minus(Duration.ofDays(1)); 
		
		TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class)
				.setParameter("stock", stock)
				.setParameter("yesterdayOpen", yesterdayOpen)
				.setParameter("yesterdayClose", yesterdayClose);
		
		List<BigDecimal> results = query.getResultList();
		
		BigDecimal prevClose = results.isEmpty()? new BigDecimal(0): results.get(0);
		 
		return prevClose;
	}
	
	public StockTransaction getLastestTransaction(Stock stock){
		// Get the price of the last trade
		String jpql = "select st from  StockTransaction st "
				+ "where st.stock = :stock "
				+ " and st.timeStamp between :todayOpen and :now" // Limit before sorting. No need to sort entrie table
				+ " order by st.timeStamp desc";
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime todayOpen = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 9,0,0);
		
		TypedQuery<StockTransaction> query = em.createQuery(jpql, StockTransaction.class)
				.setParameter("stock", stock)
				.setParameter("todayOpen", todayOpen)
				.setParameter("now", now);
		
		List<StockTransaction> results = query.getResultList();
		if (!results.isEmpty()){
			return results.get(0);
		}
		return null;
	}
	
	public BigDecimal getChange(Stock stock){
		// Get the price of the last trade
		String jpql = "select st.price from  StockTransaction st "
				+ "where st.stock = :stock "
				+ " and st.timeStamp between :todayOpen and :now" // Limit before sorting. No need to sort entrie table
				+ " order by st.timeStamp desc";
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime todayOpen = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 9,0,0);
		
		TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class)
				.setParameter("stock", stock)
				.setParameter("todayOpen", todayOpen)
				.setParameter("now", now);
		
		List<BigDecimal> results = query.getResultList();
		
		BigDecimal lastClose = results.isEmpty()? new BigDecimal(0): results.get(0);
		BigDecimal lastLastClose = results.isEmpty()? new BigDecimal(0): results.get(1);
		 
		return lastClose.subtract(lastLastClose);
	}
	
	public Long getTurnOver(Stock stock){
		// number of shares traded today
		String jpql = "select sum(st.units) from  StockTransaction st "
				+ "where st.stock = :stock "
				+ " and st.timeStamp between :todayOpen and :now";
				
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime todayOpen = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 9,0,0);
		
		TypedQuery<Long> query = em.createQuery(jpql, Long.class)
				.setParameter("stock", stock)
				.setParameter("todayOpen", todayOpen)
				.setParameter("now", now);
		Long result = query.getSingleResult();
		return result = result != null? result : new Long(0);
	}
	
	public long getDeals(Stock stock){
		String jpql = "select count(st) from  StockTransaction st "
				+ "where st.stock = :stock "
				+ " and st.timeStamp between :todayOpen and :now";
				
		
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime todayOpen = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 9,0,0);
		
		TypedQuery<Long> query = em.createQuery(jpql, Long.class)
				.setParameter("stock", stock)
				.setParameter("todayOpen", todayOpen)
				.setParameter("now", now);
		Long result = query.getSingleResult();
		return result = result != null? result : new Long(0);
	}
}
