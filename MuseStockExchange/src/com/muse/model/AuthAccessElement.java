package com.muse.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.muse.rest.LocalDateTimeDeserializer;
import com.muse.rest.LocalDateTimeSerializer;

@XmlRootElement
@Entity
@Table(name = "access_element")
@XmlAccessorType(XmlAccessType.NONE)
@NamedQueries({
		@NamedQuery(name = "AccessElement.findByTokenID", query = "select a from AuthAccessElement a where a.tokenId = :tokenId") })
public class AuthAccessElement implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String PARAM_AUTH_ID = "auth-id";
	public static final String PARAM_AUTH_TOKEN = "auth-token";

	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "token_id")
	@XmlElement
	private String tokenId;

	@OneToOne
	@JoinColumn(name = "person_id")
	@NotNull
	@XmlElement
	@JsonManagedReference
	private Person person;

	@Column(name = "created")
	@XmlElement
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	LocalDateTime created = LocalDateTime.now();

	@Column(name = "expiry")
	@XmlElement
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	LocalDateTime expiry = LocalDateTime.now().plus(Duration.ofMinutes(30));

	@Transient
	private boolean expired;

	public AuthAccessElement() {

	}

	public AuthAccessElement(Person person) {
		this.person = person;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public LocalDateTime getExpiry() {
		return expiry;
	}

	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expiry);
	}

	@Override
	public String toString() {
		return "AuthAccessElement [tokenId=" + tokenId + ", person=" + person + ", created=" + created + ", expiry="
				+ expiry + ", expired=" + expired + "]";
	}
}
