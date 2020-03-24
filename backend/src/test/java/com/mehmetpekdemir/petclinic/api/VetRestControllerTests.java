package com.mehmetpekdemir.petclinic.api;

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

import com.mehmetpekdemir.petclinic.model.Vet;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class VetRestControllerTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Before
	public void setUp() {
		testRestTemplate = testRestTemplate.withBasicAuth("user2", "secret");
	}

	@Test
	public void testGetVetById() {
		ResponseEntity<Vet> response = testRestTemplate.getForEntity("http://localhost:8088/rest/vet/14", Vet.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Test"));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetVets() {
		ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:8088/rest/vets", List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = response.getBody();

		List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

		MatcherAssert.assertThat(firstNames, Matchers.contains("Test", "Test2"));

	}

}
