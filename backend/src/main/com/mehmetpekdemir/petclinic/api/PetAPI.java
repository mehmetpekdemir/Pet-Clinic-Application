package com.mehmetpekdemir.petclinic.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mehmetpekdemir.petclinic.exception.InternalServerException;
import com.mehmetpekdemir.petclinic.exception.PetNotFoundException;
import com.mehmetpekdemir.petclinic.model.Pet;
import com.mehmetpekdemir.petclinic.service.PetClinicPetService;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@RestController
@RequestMapping("/rest")
public class PetAPI {

	@Autowired
	private PetClinicPetService petClinicPetService;

	/**
	 * Verilen id değerindeki peti döner.
	 * 
	 * @param id
	 * @return httpstatus kodlarını döner(eğer işlem başarılıysa 200, başarısızsa
	 *         404 döner.)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/pet/{id}")
	public ResponseEntity<?> getPet(@PathVariable("id") Long id) {
		try {
			Pet pet = petClinicPetService.findPet(id);
			return ResponseEntity.ok(pet);
		} catch (PetNotFoundException petNotFoundException) {
			throw petNotFoundException;
		}
	}

	/**
	 * Verilen owner id değerine göre kimin hangi petlere sahip olduğunu gösterir.
	 * 
	 * @param id
	 * @return httpstatus kodlarını döner(eğer işlem başarılıysa 200, başarısızsa
	 *         404 döner.)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/pets/{id}")
	public ResponseEntity<?> getPetsByOwnerId(@PathVariable("id") Long id) {
		try {
			List<Pet> pets = petClinicPetService.findByOwnerId(id);
			return ResponseEntity.ok(pets);
		} catch (PetNotFoundException petNotFoundException) {
			throw petNotFoundException;
		}

	}

	/**
	 * Yeni pet yaratmaya yarar.
	 * 
	 * @param pet
	 * @return httpstatus kodlarını döner(eğer işlem başarılıysa 201, başarısızsa
	 *         500 döner.)
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/pet")
	public ResponseEntity<URI> createPet(@RequestBody Pet pet) {
		try {
			petClinicPetService.createPet(pet);
			Long id = pet.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();// 201
		} catch (Exception exception) {
			throw new InternalServerException(exception);
		}
	}

	/**
	 * Varolan bir petin bilgilerini güncellemeyi sağlar.
	 * 
	 * @param id
	 * @param petRequest
	 * @return httpstatus kodlarını döner(200,404 ve 500)
	 * 
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/pet/{id}")
	public ResponseEntity<?> updatePet(@PathVariable("id") Long id, @RequestBody Pet petRequest) {
		try {
			Pet pet = petClinicPetService.findPet(id);
			pet.setName(petRequest.getName());
			petClinicPetService.updatePet(pet);
			return ResponseEntity.ok().build();
		} catch (PetNotFoundException petNotFoundException) {
			throw petNotFoundException;
		} catch (Exception exception) {
			throw new InternalServerException(exception);
		}
	}

	/**
	 * Varolan bir peti verilen id değerine göre siler.
	 * 
	 * @param id
	 * @return httpstatus kodlarını döner(200,404,500)
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/pet/{id}")
	public ResponseEntity<?> deletePet(@PathVariable("id") Long id) {
		try {
			petClinicPetService.findPet(id);
			petClinicPetService.deletePet(id);
			return ResponseEntity.ok().build();
		} catch (PetNotFoundException petNotFoundException) {
			throw petNotFoundException;
		} catch (Exception exception) {
			throw new InternalServerException(exception);
		}
	}

}