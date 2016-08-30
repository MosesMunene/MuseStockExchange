package com.muse.service;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.muse.model.AuthAccessElement;
import com.muse.model.AuthLoginElement;
import com.muse.model.Credential;
import com.muse.model.Person;
import com.muse.model.Role;



@Stateless
public class PersonServices{

	@PersistenceContext(unitName = "MuseStockExchange-JTA")
	EntityManager em;

	public Person savePerson(Person person) {
		System.out.println(person.getCredential());
		em.persist(person.getCredential());
		System.out.println("After persist(person.getCredential)");
		em.persist(person);
		System.out.println("After persist: " + person);
		return em.createNamedQuery("person.findByEmail", Person.class).setParameter("email", person.getCredential().getUserName()).getSingleResult();
		// A ton of exceptions may be thrown here. Testing will reveal them.
	}
	
	public Person getPersonByCredentialId(String credentialId) throws NoResultException{
		return em.createNamedQuery("person.findByEmail", Person.class).setParameter("email", credentialId).getSingleResult();
	}

//	public AuthAccessElement login(AuthLoginElement loginElement) {
//		User user = userService.findByUsernameAndPassword(loginElement.getUsername(), loginElement.getPassword());
//		if (user != null) {
//			user.setAuthToken(UUID.randomUUID().toString());
//			userService.save(user);
//			return new AuthAccessElement(loginElement.getUsername(), user.getAuthToken(), user.getAuthRole());
//		}
//		return null;
//	}

	public AuthAccessElement createUser(AuthLoginElement element) {
		Credential credential = new Credential();
		credential.setUserName(element.getUsername());
		credential.setPassword(element.getPassword());
		credential.setRole(Role.USER);

		Person person = new Person();
		person.setCredential(credential);
		person = this.savePerson(person);
		if (person != null) {
			person.setAuthAccessElement(new AuthAccessElement(person));
			this.updateUser(person);
			return person.getAuthAccessElement();
		}
		return null;
	}

	public void deleteAccessElement(AuthAccessElement accessElement) {
		//http://stackoverflow.com/questions/9338999/entity-must-be-managed-to-call-remove
		accessElement = em.getReference(AuthAccessElement.class, accessElement.getTokenId());
		em.remove(accessElement);
		
	}

	public void updateUser(Person person) {
		em.merge(person);
		em.flush();
		em.refresh(person);
	}
	
	public AuthAccessElement findAccessElementByTokenId(String tokenId){
		TypedQuery<AuthAccessElement> findByTokenIdQuery = em.createNamedQuery("AccessElement.findByTokenID", AuthAccessElement.class);
		findByTokenIdQuery.setParameter("tokenId", tokenId);
		return findByTokenIdQuery.getSingleResult();
	}

	public AuthAccessElement authenticate(AuthLoginElement loginElement) {
		Person person = getPersonByCredentialId(loginElement.getUsername());
		AuthAccessElement accessElement = new AuthAccessElement(person);
		person.setAuthAccessElement(accessElement);
		em.merge(person);
		em.flush();
		em.refresh(person);
		accessElement = person.getAuthAccessElement();
		System.out.println("PersonServices#login: AccessElement created " + accessElement);
		return accessElement;
	}

	public void deauthenticate(AuthAccessElement accessElement) {
		Person person = getPersonByCredentialId(accessElement.getPerson().getCredential().getUserName());
		em.remove(person.getAuthAccessElement());
		person.setAuthAccessElement(null);		
		em.flush();
		System.out.println("PersonServices#login: AccessElement deleted " + accessElement);
	}
}
