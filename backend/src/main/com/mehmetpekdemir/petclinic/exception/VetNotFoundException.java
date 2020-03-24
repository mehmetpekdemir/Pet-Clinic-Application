package com.mehmetpekdemir.petclinic.exception;

public class VetNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VetNotFoundException(String message) {
		super(message);
	}

}
