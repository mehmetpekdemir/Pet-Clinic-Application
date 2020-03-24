package com.mehmetpekdemir.petclinic.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mehmetpekdemir.petclinic.exception.VetNotFoundException;
import com.mehmetpekdemir.petclinic.model.Vet;
import com.mehmetpekdemir.petclinic.service.PetClinicVetService;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@RestController
@RequestMapping("/rest")
public class VetAPI {

	@Autowired
	private PetClinicVetService petClinicVetService;

	/**
	 * Bütün vetleri getirir.
	 * 
	 * @return HttpStatus kodunu döner(200)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/vets")
	public ResponseEntity<List<Vet>> getVets() {
		List<Vet> vets = petClinicVetService.findVets();
		return ResponseEntity.ok(vets);
	}

	/**
	 * Verilen id değerine göre bir vet getirir.
	 * 
	 * @param id
	 * @return HttpStatus kodlarını döner(200,404)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/vet/{id}")
	public ResponseEntity<?> getVet(@PathVariable("id") Long id) {
		try {
			Vet vet = petClinicVetService.findVet(id);
			return ResponseEntity.ok(vet);
		} catch (VetNotFoundException vetNotFoundException) {
			throw vetNotFoundException;
		}
	}

}
