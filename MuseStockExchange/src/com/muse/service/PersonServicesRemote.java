package com.muse.service;

import javax.ejb.Remote;

import com.muse.model.AuthAccessElement;
import com.muse.model.AuthLoginElement;
import com.muse.model.Person;

@Remote
public interface PersonServicesRemote {
	public AuthAccessElement createUser(AuthLoginElement element);
	public Person getPersonByCredentialId(String credentialId);
	
}

