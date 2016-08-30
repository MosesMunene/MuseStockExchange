package com.muse.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
@Table(name = "person")
@NamedQueries	({@NamedQuery(	name = "person.findByEmail",
							query = "select p from Person p where p.credential.userName = :email")
				})
public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( generator = "system-uuid")
	@Column(name = "person_id")
	 @XmlElement
	private String personId;
	
	@OneToOne
	@JoinColumn(name = "credential_id")
	 @XmlElement
	private Credential credential;
	
	@OneToOne(mappedBy="person", cascade = CascadeType.ALL)
	@JsonBackReference
	private AuthAccessElement authAccessElement;
	
	@OneToMany(mappedBy = "person")
	List<Order> orderList  = new ArrayList<>();
	
	
	public Person() {
		
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public Credential getCredential() {
		return credential;
	}

	public void setCredential(Credential credential) {
		this.credential = credential;
	}

	public AuthAccessElement getAuthAccessElement() {
		return authAccessElement;
	}
	
	public void setAuthAccessElement(AuthAccessElement authAccessElement) {
		this.authAccessElement = authAccessElement;
	}
	
	@Override
	public String toString() {
		return "Person [personId=" + personId + ", credential=" + credential + "]";
	}
	
}
