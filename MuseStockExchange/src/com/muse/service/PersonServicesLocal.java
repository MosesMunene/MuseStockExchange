package com.muse.service;

import javax.ejb.Local;

import com.muse.model.AuthAccessElement;
import com.muse.model.AuthLoginElement;
import com.muse.model.Person;

@Local
public interface PersonServicesLocal {
	public AuthAccessElement createUser(AuthLoginElement element);
	public void deleteExpiredAccessElement(AuthAccessElement accessElement);
	public Person getPersonByCredentialId(String credentialId);
	public void updateUser(Person person);
}
