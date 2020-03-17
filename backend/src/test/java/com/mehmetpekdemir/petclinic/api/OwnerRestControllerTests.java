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

import com.mehmetpekdemir.petclinic.model.Owner;

public class OwnerRestControllerTests {

	private static final String URL_ID = "http://localhost:8088/rest/owner/1";
	private static final String URL_LASTNAME = "http://localhost:8088/rest/owner?lastName=Pekdemir";
	private static final String URL_ALL_OWNER = "http://localhost:8088/rest/owners";
	private static final String URL_CREATE_OWNER = "http://localhost:8088/rest/owner";
	private static final String URL_UPDATE_OWNER = "http://localhost:8088/rest/owner/5";
	private static final String URL_DELETE_OWNER = "http://localhost:8088/rest/owner/4";

	private RestTemplate restTemplate;

	@Before
	public void setUp() {
		restTemplate = new RestTemplate();
	}

	@Test
	public void testGetOwnerById() {
		ResponseEntity<Owner> response = restTemplate.getForEntity(URL_ID, Owner.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));
		MatcherAssert.assertThat(response.getBody().getFirstName(), Matchers.equalTo("Mehmet"));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetsOwnersByLastName() {

		ResponseEntity<List> response = restTemplate.getForEntity(URL_LASTNAME, List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = response.getBody();

		List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

		MatcherAssert.assertThat(firstNames, Matchers.contains("Mehmet", "Deneme"));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testGetOwners() {
		ResponseEntity<List> response = restTemplate.getForEntity(URL_ALL_OWNER, List.class);
		MatcherAssert.assertThat(response.getStatusCodeValue(), Matchers.equalTo(200));

		List<Map<String, String>> body = response.getBody();

		List<String> firstNames = body.stream().map(e -> e.get("firstName")).collect(Collectors.toList());

		MatcherAssert.assertThat(firstNames, Matchers.contains("Mehmet", "Yusuf", "Mehmet2"));

	}

	@Test
	public void testCreateOwner() {
		Owner owner = new Owner();
		owner.setFirstName("Mehmet");
		owner.setLastName("Pekdemir");

		URI location = restTemplate.postForLocation(URL_CREATE_OWNER, owner);

		Owner owner2 = restTemplate.getForObject(location, Owner.class);

		MatcherAssert.assertThat(owner2.getFirstName(), Matchers.equalTo(owner.getFirstName()));
		MatcherAssert.assertThat(owner2.getLastName(), Matchers.equalTo(owner.getLastName()));

	}

	@Test
	public void testUpdateOwner() {
		Owner owner = restTemplate.getForObject(URL_UPDATE_OWNER, Owner.class);

		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Mehmet"));
		MatcherAssert.assertThat(owner.getLastName(), Matchers.equalTo("Pekdemir"));

		owner.setFirstName("Mehmet2");
		owner.setLastName("Pekdemir2");
		restTemplate.put(URL_UPDATE_OWNER, owner);
		owner = restTemplate.getForObject(URL_UPDATE_OWNER, Owner.class);

		MatcherAssert.assertThat(owner.getFirstName(), Matchers.equalTo("Mehmet2"));
		MatcherAssert.assertThat(owner.getLastName(), Matchers.equalTo("Pekdemir2"));
	}

	@Test
	public void testDeleteOwner() {
		restTemplate.delete(URL_DELETE_OWNER);
		try {
			restTemplate.getForEntity(URL_DELETE_OWNER, Owner.class);
			Assert.fail("Should have not returned owner");
		} catch (HttpClientErrorException httpClientErrorException) {
			MatcherAssert.assertThat(httpClientErrorException.getStatusCode().value(), Matchers.equalTo(404));
		}
	}

}
