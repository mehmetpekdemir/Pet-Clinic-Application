package com.mehmetpekdemir.petclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Runtime da (Çalışma anında hata oluşması durumda 500 kodunu fırlatacak
 * exceptionumun classı)
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InternalServerException(Throwable cause) {
		super(cause);
	}

}
