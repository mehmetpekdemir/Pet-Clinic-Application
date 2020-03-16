package com.mehmetpekdemir.petclinic.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mehmetpekdemir.petclinic.exception.InternalServerException;
import com.mehmetpekdemir.petclinic.exception.OwnerNotFoundException;
import com.mehmetpekdemir.petclinic.model.Owner;
import com.mehmetpekdemir.petclinic.service.PetClinicOwnerService;

@RestController
@RequestMapping("/rest")
public class OwnerAPI {

	@Autowired
	private PetClinicOwnerService petClinicService;

	@RequestMapping(method = RequestMethod.GET, value = "/owners")
	public ResponseEntity<List<Owner>> getOwners() {
		List<Owner> owners = petClinicService.findOwners();
		return ResponseEntity.ok(owners);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/owner/{id}")
	public ResponseEntity<?> getOwner(@PathVariable("id") Long id) {
		try {
			Owner owner = petClinicService.findOwner(id);
			return ResponseEntity.ok(owner);
		} catch (OwnerNotFoundException ownerNotFoundException) {
			return ResponseEntity.notFound().build(); // 404 kodumu döndürüyorum.
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/owner")
	public ResponseEntity<List<Owner>> getOwners(@RequestParam("lastName") String lastName) {
		List<Owner> owners = petClinicService.findOwners(lastName);
		return ResponseEntity.ok(owners);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/owner")
	public ResponseEntity<URI> createOwner(@RequestBody Owner owner) {
		try {
			petClinicService.createOwner(owner);
			Long id = owner.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();// 201

		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();// 500
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/owner/{id}")
	public ResponseEntity<?> updateOwner(@PathVariable("id") Long id, @RequestBody Owner ownerRequest) {
		try {
			Owner owner = petClinicService.findOwner(id);
			owner.setFirstName(ownerRequest.getFirstName());
			owner.setLastName(ownerRequest.getLastName());
			petClinicService.updateOwner(owner);
			return ResponseEntity.ok().build();
		} catch (OwnerNotFoundException ownerNotFoundException) {
			return ResponseEntity.notFound().build();
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			// Buraları düzelt deletedeki gibi yap.
		}

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/owner/{id}")
	public ResponseEntity<?> deleteOwner(@PathVariable("id") Long id) {
		try {
			petClinicService.deleteOwner(id);
			return ResponseEntity.ok().build();
		} catch (OwnerNotFoundException ownerNotFoundException) {
			throw ownerNotFoundException;
		} catch (Exception exception) {
			throw new InternalServerException(exception);
		}
	}
}
