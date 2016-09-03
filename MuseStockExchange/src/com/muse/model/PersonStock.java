package com.muse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "person_stock")
public class PersonStock implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "person_stock_id")
	private String personStockId;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;
	
	
	@Column(name = "units")
	private long units;
	
	public PersonStock(){
		// TODO Auto-generated constructor stub
	}

	public PersonStock(Stock stock, long units) {
		this.stock = stock;
		this.units = units;
	}

	public void addUnits(long units){
		this.units += units;
	}
	
	public void subtractUnits(long units){
		this.units = units;
	}
	public String getPersonStockId() {
		return personStockId;
	}


	public void setPersonStockId(String personStockId) {
		this.personStockId = personStockId;
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


