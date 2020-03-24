package com.mehmetpekdemir.petclinic.security;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.mehmetpekdemir.petclinic.service.PetClinicOwnerService;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
public class PetClinicWithInvalidAuthTokenTests {

	@Autowired
	private PetClinicOwnerService petClinicOwnerService;

	@Before
	public void setUp() {
		TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken("user", "secret", "ROLE_XXX");
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}

	@After
	public void tearDown() {
		SecurityContextHolder.clearContext();
	}

	@Test(expected = AccessDeniedException.class)
	public void testFindOwners() {
		petClinicOwnerService.findOwners();
	}
}
