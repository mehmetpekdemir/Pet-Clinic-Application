package com.mehmetpekdemir.petclinic.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mehmetpekdemir.petclinic.dao.PetRepository;
import com.mehmetpekdemir.petclinic.model.Pet;

/**
 * Pet için persistance katmanım. Veritabanı islemlerinin yapıldığı kısım.
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@Repository("petRepository")
public class PetRepositoryImpl implements PetRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Pet findById(Long id) {
		return entityManager.find(Pet.class, id);
	}

	@Override
	public List<Pet> findByOwnerId(Long ownerId) {
		return entityManager.createQuery("from Pet where owner.id = :ownerId", Pet.class)
				.setParameter("ownerId", ownerId).getResultList();
	}

	@Override
	public void createPet(Pet pet) {
		entityManager.persist(pet);
	}

	@Override
	public Pet updatePet(Pet pet) {
		return entityManager.merge(pet);
	}

	@Override
	public void deletePet(Long id) {
		entityManager.remove(entityManager.getReference(Pet.class, id));
	}

	@Override
	public void deleteByOwnerId(Long id) {
		entityManager.createQuery("delete from Pet where owner.id = :ownerId").setParameter("ownerId", id)
				.executeUpdate();
	}

}
