package com.mehmetpekdemir.petclinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mehmetpekdemir.petclinic.dao.OwnerRepository;
import com.mehmetpekdemir.petclinic.dao.PetRepository;
import com.mehmetpekdemir.petclinic.exception.OwnerNotFoundException;
import com.mehmetpekdemir.petclinic.model.Owner;
import com.mehmetpekdemir.petclinic.service.PetClinicOwnerService;

@Service
@Transactional(rollbackFor=Exception.class)
public class PetClinicOwnerServiceImpl implements PetClinicOwnerService {

	private final OwnerRepository ownerRepository;

	private final PetRepository petRepository;

	@Autowired
	public PetClinicOwnerServiceImpl(OwnerRepository ownerRepository, PetRepository petRepository) {
		this.ownerRepository = ownerRepository;
		this.petRepository = petRepository;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Owner> findOwners() {
		return ownerRepository.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public List<Owner> findOwners(String lastName) {
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
	public Owner findOwner(Long id) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findById(id);
		if (owner == null)
			throw new OwnerNotFoundException("Owner not found");
		return owner;
	}

	@Override
	public void createOwner(Owner owner) {
		ownerRepository.createOwner(owner);
	}

	@Override
	public void updateOwner(Owner owner) {
		ownerRepository.updateOwner(owner);
	}

	@Override 
	public void deleteOwner(Long id) { //Transactional eklenecek.
		petRepository.deleteByOwnerId(id);
		ownerRepository.deleteOwner(id);
	}

}
