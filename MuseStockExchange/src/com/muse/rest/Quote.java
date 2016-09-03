package com.muse.rest;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.muse.model.Stock;

public class Quote implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stockId;
	private long totalDemand;
	private long totalSupply;
	private double bestBid;
	private double bestAsk;
	private double prevClose;
	private double lastPrice;
	private double change;
	private long turnOver;
	private long deals;
	
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime time;
	
	public Quote() {
		// TODO Auto-generated constructor stub
	}

	

	public String getStockId() {
		return stockId;
	}



	public void setStockId(String stockId) {
		this.stockId = stockId;
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

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	
}
