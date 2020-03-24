package com.mehmetpekdemir.petclinic.security;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.mehmetpekdemir.petclinic.model.Owner;
import com.mehmetpekdemir.petclinic.service.PetClinicOwnerService;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
public class PetClinicWithValidAuthTokenTests {

	@Autowired
	private PetClinicOwnerService petClinicOwnerService;

	@Before
	public void setUp() {
		TestingAuthenticationToken authenticationToken = new TestingAuthenticationToken("user", "secret", "ROLE_USER");
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}

	@After
	public void tearDown() {
		SecurityContextHolder.clearContext();
	}

	@Test
	public void testFindOwners() {
		List<Owner> owners = petClinicOwnerService.findOwners();
		MatcherAssert.assertThat(owners.size(), Matchers.equalTo(3));
	}
}
