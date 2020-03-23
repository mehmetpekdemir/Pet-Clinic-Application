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

import com.mehmetpekdemir.petclinic.model.Owner;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("dev")
public class OwnerRestControllerTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Before
	public void setUp() {
		testRestTemplate = testRestTemplate.withBasicAuth("user2", "secret");

	}

	@Test
	public void testGetOwnerById() {
		ResponseEntity<Owner> response = testRestTemplate.getForEntity("http://localhost:8088/rest/owner/8",
				Owner.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Mehmet"));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetsOwnersByLastName() {

		ResponseEntity<List> response = testRestTemplate
				.getForEntity("http://localhost:8088/rest/owner?lastName=Pekdemir", List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = response.getBody();

		List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

		MatcherAssert.assertThat(firstNames, Matchers.contains("Mehmet", "Deneme"));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetOwners() {
		ResponseEntity<List> response = testRestTemplate.getForEntity("http://localhost:8088/rest/owners", List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = response.getBody();

		List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

		MatcherAssert.assertThat(firstNames, Matchers.contains("Mehmet", "Yusuf", "test"));

	}

	@Test
	public void testCreateOwner() {
		Owner owner = new Owner();
		owner.setFirstName("Mehmet");
		owner.setLastName("Pekdemir");

		URI location = testRestTemplate.postForLocation("http://localhost:8088/rest/owner", owner);

		Owner owner2 = testRestTemplate.getForObject(location, Owner.class);

		MatcherAssert.assertThat(owner2.getFirstName(), Matchers.equalTo(owner.getFirstName()));
		MatcherAssert.assertThat(owner2.getLastName(), Matchers.equalTo(owner.getLastName()));

	}

	@Test
	public void testUpdateOwner() {
		Owner owner = testRestTemplate.getForObject("http://localhost:8088/rest/owner/12", Owner.class);

		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Mehmet"));
		MatcherAssert.assertThat(owner.getLastName(), Matchers.equalTo("Pekdemir"));

		owner.setFirstName("Mehmet2");
		owner.setLastName("Pekdemir2");
		testRestTemplate.put("http://localhost:8088/rest/owner/12", owner);
		owner = testRestTemplate.getForObject("http://localhost:8088/rest/owner/12", Owner.class);

		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Mehmet2"));
		MatcherAssert.assertThat(owner.getLastName(), Matchers.equalTo("Pekdemir2"));
	}

	@Test
	public void testDeleteOwner() {
		try {
			testRestTemplate.delete("http://localhost:8088/rest/owner/10");
		} catch (HttpClientErrorException httpClientErrorException) {
			MatcherAssert.assertThat(httpClientErrorException.getStatusCode().value(), Matchers.equalTo(404));
		}
	}

}
