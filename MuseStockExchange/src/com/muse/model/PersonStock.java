package com.muse.model;

import java.io.Serializable;

public class PersonStock implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Person person;
	private Stock stock;
	private long units;
	
	public PersonStock(){
		// TODO Auto-generated constructor stub
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
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
	
}
