package com.muse.test;

import static org.junit.Assert.assertNotNull;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.muse.model.AuthLoginElement;
import com.muse.service.PersonServicesRemote;

public class AuthSericeTest {
	private static InitialContext ic;
	private static PersonServicesRemote personServicesRemote;

	@BeforeClass
	public static void setUp() throws NamingException {
		ic = new InitialContext();
		personServicesRemote = (PersonServicesRemote) ic
				.lookup("com.muse.service.PersonServicesRemote#com.muse.service.PersonServicesRemote");
	}

	@Test
	public void testUserCreatePerson() {
		AuthLoginElement authLoginElement = new AuthLoginElement();
		authLoginElement.setUsername("moses2@domain.com");
		authLoginElement.setPassword("password");
		assertNotNull(personServicesRemote.createUser(authLoginElement));

	}
	
	@After
	public void tearDown(){
		
	}
}
