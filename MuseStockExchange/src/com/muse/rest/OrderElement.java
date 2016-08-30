package com.muse.rest;

public class OrderElement {
	private String personId;
	private String stock;
	private String type;
	private long units;
	private double price;

	public OrderElement() {
		// TODO Auto-generated constructor stub
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getUnits() {
		return units;
	}

	public void setUnits(long units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "OrderElement [personId=" + personId + ", stock=" + stock + ", type=" + type + ", units=" + units
				+ ", price=" + price + "]";
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}
