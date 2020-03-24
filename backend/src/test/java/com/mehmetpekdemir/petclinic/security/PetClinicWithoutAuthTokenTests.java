package com.mehmetpekdemir.petclinic.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.mehmetpekdemir.petclinic.service.PetClinicOwnerService;
/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=dev")
public class PetClinicWithoutAuthTokenTests {

	@Autowired
	private PetClinicOwnerService petClinicOwnerService;

	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void testFindOwners() {
		petClinicOwnerService.findOwners();
	}
}
