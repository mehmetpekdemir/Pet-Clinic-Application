package com.mehmetpekdemir.petclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Pet bulunmaması durumda (404) kodunu döneceğim classım
 * 
 * @author MEHMET
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PetNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PetNotFoundException(String message) {
		super(message);
	}

}
