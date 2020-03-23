package com.mehmetpekdemir.petclinic;

import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mehmetpekdemir.petclinic.model.Pet;
import com.mehmetpekdemir.petclinic.service.PetClinicPetService;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = { "spring.profiles.active=dev" })
public class PetClinicPetIntegrationTests {

	@Autowired
	PetClinicPetService petClinicPetService;

	@Test
	public void testFindPet() {
		List<Pet> pets = petClinicPetService.findByOwnerId(1L);
		MatcherAssert.assertThat(pets.size(), Matchers.equalTo(2));
	}

}
