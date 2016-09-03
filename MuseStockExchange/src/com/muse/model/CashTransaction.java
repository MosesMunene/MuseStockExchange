package com.muse.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cash_transaction")
public class CashTransaction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "id")
	private String id;
	
	@Column(name = "timestamp")
	private LocalDateTime timestamp = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private CashTransactionType type;
	
	@ManyToOne
	@JoinColumn(name = "credit")
	private Person credit;
	
	@ManyToOne
	@JoinColumn(name = "debit")
	private Person debit;
	
	@Column(name = "amount")
	private BigDecimal amount;
	
	public CashTransaction() {
		// For JPA
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public CashTransactionType getType() {
		return type;
	}

	public void setType(CashTransactionType type) {
		this.type = type;
	}
	
	public Person getCredit() {
		return credit;
	}

	public void setCredit(Person credit) {
		this.credit = credit;
	}

	public Person getDebit() {
		return debit;
	}

	public void setDebit(Person debit) {
		this.debit = debit;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
