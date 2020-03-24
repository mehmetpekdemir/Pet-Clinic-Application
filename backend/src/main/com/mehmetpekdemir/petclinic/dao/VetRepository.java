package com.mehmetpekdemir.petclinic.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mehmetpekdemir.petclinic.model.Vet;

/**
 * Spring data kullandım.
 * 
 * @author MEHMET PEKDEMIR
 *
 */
public interface VetRepository extends JpaRepository<Vet, Long> {

}
