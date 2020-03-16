package com.mehmetpekdemir.petclinic.dao;

import java.util.List;

import com.mehmetpekdemir.petclinic.model.Owner;

public interface OwnerRepository {
	List<Owner> findAll();

	Owner findById(Long id);

	List<Owner> findByLastName(String lastName);

	void createOwner(Owner owner);

	Owner updateOwner(Owner owner);

	void deleteOwner(Long id);
}
