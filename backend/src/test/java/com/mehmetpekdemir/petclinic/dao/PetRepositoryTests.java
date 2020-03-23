package com.mehmetpekdemir.petclinic.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mehmetpekdemir.petclinic.model.Pet;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class PetRepositoryTests {

	@Autowired
	private PetRepository petRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test(expected = DataIntegrityViolationException.class)
	public void testCreatePet() {
		Pet pet = new Pet();
		pet.setName(null);
		pet.setBirthDate(null);
		pet.setOwner(null);

		petRepository.createPet(pet);

		entityManager.flush();
	}
}
