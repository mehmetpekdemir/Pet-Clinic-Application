package com.mehmetpekdemir.petclinic.api;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.mehmetpekdemir.petclinic.model.Pet;

public class PetRestControllerTests {

	private static final String URL_ID = "http://localhost:8088/rest/pet/1";
	private static final String URL_PETS = "http://localhost:8088/rest/pets/1";
	private static final String URL_CREATE_PET = "http://localhost:8088/rest/pet";
	private static final String URL_UPDATE_PET = "http://localhost:8088/rest/pet/2";
	private static final String URL_DELETE_PET = "http://localhost:8088/rest/pet/3";

	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void testGetPetById() {
		ResponseEntity<Pet> response = restTemplate.getForEntity(URL_ID, Pet.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getName(), Matchers.equalTo("Chucky"));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetsPetsByOwnerId() {

		ResponseEntity<List> response = restTemplate.getForEntity(URL_PETS, List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = response.getBody();

		List<String> names = body.stream().map(e -> e.get("name")).collect(Collectors.toList());

		MatcherAssert.assertThat(names, Matchers.contains("Chucky", "Karabas"));
	}

	@Test
	public void testCreatePet() {
		Pet pet = new Pet();
		pet.setName("Test");

		URI location = restTemplate.postForLocation(URL_CREATE_PET, pet);

		Pet pet2 = restTemplate.getForObject(location, Pet.class);

		MatcherAssert.assertThat(pet2.getName(), Matchers.equalTo(pet.getName()));

	}

	@Test
	public void testUpdatePet() {
		Pet pet = restTemplate.getForObject(URL_UPDATE_PET, Pet.class);

		MatcherAssert.assertThat(pet.getName(), Matchers.equalTo("Karabas"));

		pet.setName("TestPet");

		restTemplate.put(URL_UPDATE_PET, pet);

		pet = restTemplate.getForObject(URL_UPDATE_PET, Pet.class);

		MatcherAssert.assertThat(pet.getName(), Matchers.equalTo("TestPet"));

	}

	@Test
	public void testDeletePet() {
		restTemplate.delete(URL_DELETE_PET);
		try {
			restTemplate.getForEntity(URL_DELETE_PET, Pet.class);
			Assert.fail("Should have not returned pet");
		} catch (HttpClientErrorException httpClientErrorException) {
			MatcherAssert.assertThat(httpClientErrorException.getStatusCode().value(), Matchers.equalTo(404));
		}
	}
}
