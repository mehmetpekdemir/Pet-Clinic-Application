package com.mehmetpekdemir.petclinic.service;

import java.util.List;

import com.mehmetpekdemir.petclinic.exception.OwnerNotFoundException;
import com.mehmetpekdemir.petclinic.model.Owner;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
public interface PetClinicOwnerService {
	List<Owner> findOwners();

	List<Owner> findOwners(String lastName);

	Owner findOwner(Long id) throws OwnerNotFoundException;

	void createOwner(Owner owner);

	void updateOwner(Owner owner);

	void deleteOwner(Long id);

}
