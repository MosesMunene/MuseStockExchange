package com.muse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "stock")
@XmlRootElement
public class Stock implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "stock_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String stockId;
	
	@OneToOne
	@JoinColumn(name = "company_id")
	@JsonManagedReference
	private Company company;
	
	@Column(name = "shares_listed")
	private long sharesListed;
	
	@Column(name = "par_value")
	private double parValue;
	
	@Column(name = "current_price")
	private double currentPrice;
	
	public Stock() {
		// TODO Auto-generated constructor stub
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getSymbol() {
		return stockId;
	}

	public void setSymbol(String symbol) {
		this.stockId = symbol;
	}
	
	

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public long getSharesListed() {
		return sharesListed;
	}

	public void setSharesListed(long sharesListed) {
		this.sharesListed = sharesListed;
	}

	public double getParValue() {
		return parValue;
	}

	public void setParValue(double parValue) {
		this.parValue = parValue;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "Stock [stockId=" + stockId + ", company=" + company.getName() + ", sharesListed=" + sharesListed + ", parValue="
				+ parValue + ", currentPrice=" + currentPrice + "]";
	}
}
