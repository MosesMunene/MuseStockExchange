package com.muse.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "order_tbl")
@NamedQueries({ @NamedQuery(name = "Order.getAllAorders", query = "select o from Order o") })
public class Order {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "order_id")
	private String orderId;

	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;

	@ManyToOne
	@JoinColumn(name = "stock_id")
	private Stock stock;

	@Column(name = "units")
	private long units;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "created")
	private LocalDateTime created = LocalDateTime.now();

	@Column(name = "expiry")
	private LocalDateTime expiry = this.created.plus(Duration.ofDays(5));

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private OrderType type;

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getExpiry() {
		return expiry;
	}

	public void setExpiry(LocalDateTime expiry) {
		this.expiry = expiry;
	}

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", person=" + person + ", stock=" + stock + ", units=" + units + ", price="
				+ price + ", created=" + created + ", expiry=" + expiry + ", type=" + type + "]";
	}

}
