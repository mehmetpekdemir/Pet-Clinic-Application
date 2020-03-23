package com.mehmetpekdemir.petclinic.api;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.mehmetpekdemir.petclinic.model.Pet;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class PetRestControllerTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Before
	public void setUp() {
		testRestTemplate = testRestTemplate.withBasicAuth("user2", "secret");
	}

	@Test
	public void testGetPetById() {
		ResponseEntity<Pet> response = testRestTemplate.getForEntity("http://localhost:8088/rest/pet/5", Pet.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getName(), Matchers.equalTo("Chucky"));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetsPetsByOwnerId() {

		ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:8088/rest/pets/8", List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = response.getBody();

		List<String> names = body.stream().map(e -> e.get("name")).collect(Collectors.toList());

		MatcherAssert.assertThat(names, Matchers.contains("Chucky", "Minnos"));
	}

	@Test
	public void testCreatePet() {
		Pet pet = new Pet();
		pet.setName("Test");

		URI location = testRestTemplate.postForLocation("http://localhost:8088/rest/pet", pet);

		Pet pet2 = testRestTemplate.getForObject(location, Pet.class);

		MatcherAssert.assertThat(pet2.getName(), Matchers.equalTo(pet.getName()));

	}

	@Test
	public void testUpdatePet() {
		Pet pet = testRestTemplate.getForObject("http://localhost:8088/rest/pet/9", Pet.class);

		MatcherAssert.assertThat(pet.getName(), Matchers.equalTo("Test"));

		pet.setName("TestPet");

		testRestTemplate.put("http://localhost:8088/rest/pet/9", pet);

		pet = testRestTemplate.getForObject("http://localhost:8088/rest/pet/9", Pet.class);

		MatcherAssert.assertThat(pet.getName(), Matchers.equalTo("TestPet"));

	}

	@Test
	public void testDeletePet() {
		try {
			testRestTemplate.delete("http://localhost:8088/rest/pet/7");
		} catch (HttpClientErrorException httpClientErrorException) {
			MatcherAssert.assertThat(httpClientErrorException.getStatusCode().value(), Matchers.equalTo(404));
		}
	}
}
