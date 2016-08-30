package com.muse.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "credential")
public class Credential implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "credential_id")
	private String userName;
	
	@Column(name = "password")
	@JsonIgnore
	private String password;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public Credential() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	public Role getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Credential [userName=" + userName + ", password=" + "xxxxxxxxxxx" + ", role=" + role + "]";
	}
	
}
