package com.mehmetpekdemir.petclinic;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
public class PasswordEncoderTests {

	@Test
	public void generateEncodedPasswords() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println("{bcrypt}" + bCryptPasswordEncoder.encode("secret"));
		System.out.println("{bcrypt}" + bCryptPasswordEncoder.encode("secret"));
		System.out.println("{bcrypt}" + bCryptPasswordEncoder.encode("secret"));

	}
}
