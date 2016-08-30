package com.muse.rest;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import com.muse.model.AuthAccessElement;
import com.muse.model.Person;
import com.muse.service.PersonServices;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {
	@Context
	private ResourceInfo resourceInfo;

	private static final Response ACCESS_DENIED = Response.status(Response.Status.UNAUTHORIZED)
			.entity("You cannot access this resource").build();
	private static final Response ACCESS_FORBIDDEN = Response.status(Response.Status.FORBIDDEN)
			.entity("Access blocked for all users !!").build();

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		System.out.println("Request filtered");
		Method method = resourceInfo.getResourceMethod();
		if (!method.isAnnotationPresent(PermitAll.class)) {
			// Access denied for all
			if (method.isAnnotationPresent(DenyAll.class)) {
				throw new NotAuthorizedException("Bearer error=\"invalid_token\"");
			}

			// Get request headers
			final MultivaluedMap<String, String> headers = requestContext.getHeaders();

			// Fetch authorization header
			final List<String> authToken = headers.get("token");
			// If no authorization information present; block access
			if (authToken == null || authToken.isEmpty()) {
				System.out.println("denied Access: no token. User not logged in");
				throw new NotAuthorizedException("Bearer error=\"invalid_token\"");

			}

			AuthAccessElement authAccessElement = null;
			try {
				JAXBContext jc = JAXBContext.newInstance(AuthAccessElement.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
				unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
				StreamSource json = new StreamSource(new StringReader(authToken.get(0)));
				authAccessElement = unmarshaller.unmarshal(json, AuthAccessElement.class).getValue();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Verify user access
			if (method.isAnnotationPresent(RolesAllowed.class)) {
				RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
				Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));

				// Is user valid?
				if (!isUserAllowed(authAccessElement, rolesSet)) {
					System.out.println("denied Access: user name does not match or role not authorized");
					throw new NotAuthorizedException("Bearer error=\"invalid_token\"");
				}
			}
		}
	}

	@EJB
	private PersonServices personServices;

	private boolean isUserAllowed(AuthAccessElement authAccessElement, final Set<String> rolesSet) {
		
		boolean isAllowed = false;
		
		if (authAccessElement.getExpiry() != null && authAccessElement.getExpiry().isAfter(LocalDateTime.now())) {

			String userRole = authAccessElement.getPerson().getCredential().getRole().toString();

			// Step 2. Verify user role
			if (rolesSet.contains(userRole)) {
				isAllowed = true;
			}
		} else {
			String userName = authAccessElement.getPerson().getCredential().getUserName();
			String password = authAccessElement.getPerson().getCredential().getPassword();
			Person personFromDb = personServices.getPersonByCredentialId(userName);
			
			if (password.equals(personFromDb.getCredential().getPassword())) {
				
				String userRole = authAccessElement.getPerson().getCredential().getRole().toString();
				// Step 2. Verify user role
				if (rolesSet.contains(userRole)) {
					isAllowed = true;
				}
			}
		}
		return isAllowed;
	}

}
