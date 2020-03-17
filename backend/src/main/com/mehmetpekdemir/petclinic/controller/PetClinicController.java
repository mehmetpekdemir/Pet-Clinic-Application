package com.mehmetpekdemir.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@Controller
public class PetClinicController {

	/**
	 * Anasayfaya yönlenmemizi sağlar.
	 * 
	 * @return modelAndView
	 */
	@RequestMapping(value = { "/", "/index.html" })
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
}
