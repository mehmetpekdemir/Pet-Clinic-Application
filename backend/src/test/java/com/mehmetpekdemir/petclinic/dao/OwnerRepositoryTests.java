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

import com.mehmetpekdemir.petclinic.model.Owner;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class OwnerRepositoryTests {

	@Autowired
	private OwnerRepository ownerRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test(expected = DataIntegrityViolationException.class)
	public void testCreateOwner() {
		Owner owner = new Owner();
		owner.setFirstName(null);
		owner.setLastName(null);

		ownerRepository.createOwner(owner);

		entityManager.flush();
	}
}
