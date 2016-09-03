package com.muse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stock_transaction")
public class StockTransaction implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id")
	private String id;
	
	@ManyToOne
	@JoinColumn(name = "buyer")
	private Person buyer;
	
	@ManyToOne
	@JoinColumn(name = "seller")
	private Person seller;
	
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;
	
	@Column(name = "units")
	private long units;
	
	@Column(name = "price")
	private BigDecimal price;
	
	@Column(name = "timestamp")
	private LocalDateTime timeStamp = LocalDateTime.now();
	
	public StockTransaction() {
		// For jpa
	}

	public Person getBuyer() {
		return buyer;
	}

	public void setBuyer(Person buyer) {
		this.buyer = buyer;
	}

	public Person getSeller() {
		return seller;
	}

	public void setSeller(Person seller) {
		this.seller = seller;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public long getUnits() {
		return units;
	}

	public void setUnits(long units) {
		this.units = units;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
