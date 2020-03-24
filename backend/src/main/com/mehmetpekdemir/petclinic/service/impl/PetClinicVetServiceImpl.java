package com.mehmetpekdemir.petclinic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mehmetpekdemir.petclinic.dao.VetRepository;
import com.mehmetpekdemir.petclinic.exception.VetNotFoundException;
import com.mehmetpekdemir.petclinic.model.Vet;
import com.mehmetpekdemir.petclinic.service.PetClinicVetService;

/**
 * Vet için gerekli olan service katmanım(business(iş) katmanım) Buradan
 * Repository(Dao) katmanıma yönlendiriyoruz veritabanı işlemlerinini veritabanı
 * katmanında yapıyoruz. Burada sadece iş kodları bulunmaktadır.
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PetClinicVetServiceImpl implements PetClinicVetService {

	private final VetRepository vetRepository;

	@Autowired
	public PetClinicVetServiceImpl(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

	@Override
	public List<Vet> findVets() {
		return vetRepository.findAll();
	}

	@Override
	public Vet findVet(Long id) throws VetNotFoundException {
		return vetRepository.findById(id).orElseThrow(() -> {
			return new VetNotFoundException("Vet not found by id :" + id);
		});
	}

}
