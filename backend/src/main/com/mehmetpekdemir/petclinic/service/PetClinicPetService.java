package com.mehmetpekdemir.petclinic.service;

import java.util.List;

import com.mehmetpekdemir.petclinic.exception.PetNotFoundException;
import com.mehmetpekdemir.petclinic.model.Pet;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
public interface PetClinicPetService {
	Pet findPet(Long id) throws PetNotFoundException;

	List<Pet> findByOwnerId(Long id) throws PetNotFoundException;

	void createPet(Pet pet);

	void updatePet(Pet pet);

	void deletePet(Long id);
}
