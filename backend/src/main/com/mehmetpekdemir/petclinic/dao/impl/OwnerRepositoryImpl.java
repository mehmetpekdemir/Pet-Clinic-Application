package com.mehmetpekdemir.petclinic.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mehmetpekdemir.petclinic.dao.OwnerRepository;
import com.mehmetpekdemir.petclinic.model.Owner;

/**
 * Owner için persistance katmanım. Veritabanı islemlerinin yapıldığı kısım.
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@Repository("ownerRepository")
public class OwnerRepositoryImpl implements OwnerRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Owner> findAll() {
		return entityManager.createQuery("from Owner", Owner.class).getResultList();
	}

	@Override
	public Owner findById(Long id) {
		return entityManager.find(Owner.class, id);
	}

	@Override
	public List<Owner> findByLastName(String lastName) {
		return entityManager.createQuery("from Owner where lastName = :lastName", Owner.class)
				.setParameter("lastName", lastName).getResultList();
	}

	@Override
	public void createOwner(Owner owner) {
		entityManager.persist(owner);
	}

	@Override
	public Owner updateOwner(Owner owner) {
		return entityManager.merge(owner);
	}

	@Override
	public void deleteOwner(Long id) {
		entityManager.remove(entityManager.getReference(Owner.class, id));
	}

}
