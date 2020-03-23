package com.mehmetpekdemir.petclinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	/**
	 * Kendi oluşturduğumuz login sayfasına yönlenmemizi sağlar
	 * 
	 * @return modelAndView
	 */
	@RequestMapping(value = "/login.html", method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

}
