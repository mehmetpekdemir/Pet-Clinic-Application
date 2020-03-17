package com.mehmetpekdemir.petclinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mehmetpekdemir.petclinic.dao.PetRepository;
import com.mehmetpekdemir.petclinic.exception.PetNotFoundException;
import com.mehmetpekdemir.petclinic.model.Pet;
import com.mehmetpekdemir.petclinic.service.PetClinicPetService;

/**
 * Pet için gerekli olan service katmanım(business(iş) katmanım) Buradan
 * Repository(Dao) katmanıma yönlendiriyoruz veritabanı işlemlerinini veritabanı
 * katmanında yapıyoruz. Burada sadece iş kodları bulunmaktadır.
 * 
 * @author MEHMET
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PetClinicPetServiceImpl implements PetClinicPetService {

	private final PetRepository petRepository;

	@Autowired
	public PetClinicPetServiceImpl(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	/**
	 * Transactional(readOnly = true) yapılmasının sebebi burada bir trancation
	 * islemi oluşmayacaktır. Veri üzerinde bir değişiklik yapmadığımız için sadece
	 * okunur yaptık.
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Pet findPet(Long id) throws PetNotFoundException {
		Pet pet = petRepository.findById(id);
		if (pet == null) {
			throw new PetNotFoundException("Pet not found");
		}
		return pet;
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Pet> findByOwnerId(Long id) throws PetNotFoundException {
		List<Pet> pets = petRepository.findByOwnerId(id);
		if (pets == null) {
			throw new PetNotFoundException("Pet not found");
		}
		return pets;
	}

	@Override
	public void createPet(Pet pet) {
		petRepository.createPet(pet);
	}

	@Override
	public void updatePet(Pet pet) {
		petRepository.updatePet(pet);
	}

	@Override
	public void deletePet(Long id) {
		petRepository.deletePet(id);
	}
}
