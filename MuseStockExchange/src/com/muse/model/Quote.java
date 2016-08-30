package com.muse.model;

import java.io.Serializable;
import java.time.LocalTime;

public class Quote implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stock stock;
	private long totalDemand;
	private long totalSupply;
	private double bestBid;
	private double bestAsk;
	private double prevClose;
	private double lastPrice;
	private double change;
	private long turnOver;
	private long deals;
	private LocalTime time;
	
	public Quote() {
		// TODO Auto-generated constructor stub
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public long getTotalDemand() {
		return totalDemand;
	}

	public void setTotalDemand(long totalDemand) {
		this.totalDemand = totalDemand;
	}

	public long getTotalSupply() {
		return totalSupply;
	}

	public void setTotalSupply(long totalSupply) {
		this.totalSupply = totalSupply;
	}

	public double getBestBid() {
		return bestBid;
	}

	public void setBestBid(double bestBid) {
		this.bestBid = bestBid;
	}

	public double getBestAsk() {
		return bestAsk;
	}

	public void setBestAsk(double bestAsk) {
		this.bestAsk = bestAsk;
	}

	public double getPrevClose() {
		return prevClose;
	}

	public void setPrevClose(double prevClose) {
		this.prevClose = prevClose;
	}

	public double getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public long getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(long turnOver) {
		this.turnOver = turnOver;
	}

	public long getDeals() {
		return deals;
	}

	public void setDeals(long deals) {
		this.deals = deals;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
	
	
}
