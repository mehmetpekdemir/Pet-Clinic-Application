package com.mehmetpekdemir.petclinic;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mehmetpekdemir.petclinic.model.Vet;
import com.mehmetpekdemir.petclinic.service.PetClinicVetService;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.profiles.active=dev" })
public class PetClinicVetIntegrationTests {

	@Autowired
	private PetClinicVetService petClinicVetService;

	@Test
	public void testFindVets() {
		List<Vet> vets = petClinicVetService.findVets();
		MatcherAssert.assertThat(vets.size(), Matchers.equalTo(2));
	}

}
