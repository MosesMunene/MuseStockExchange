package com.muse.model;

import java.io.Serializable;

public class AuthLoginElement implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	
	public AuthLoginElement() {
		// TODO Auto-generated constructor stub
	}

	public AuthLoginElement(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "AuthLoginElement [username=" + username + ", password=" + password + "]";
	}

}