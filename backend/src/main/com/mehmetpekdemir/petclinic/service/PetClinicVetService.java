package com.mehmetpekdemir.petclinic.service;

import java.util.List;

import com.mehmetpekdemir.petclinic.exception.VetNotFoundException;
import com.mehmetpekdemir.petclinic.model.Vet;
/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
public interface PetClinicVetService {

	List<Vet> findVets();
	Vet findVet(Long id) throws VetNotFoundException;
}
