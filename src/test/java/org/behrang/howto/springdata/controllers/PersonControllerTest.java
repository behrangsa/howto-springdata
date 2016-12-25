package org.behrang.howto.springdata.controllers;

import org.behrang.howto.springdata.entities.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_XML;

/**
 * @author Behrang Saeedzadeh
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PersonControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private HttpHeaders headers;

    @Before
    public void before() {
        headers = new HttpHeaders();
        headers.setContentType(APPLICATION_XML);
    }

    @Test
    public void testGetPerson() {
        final HttpEntity<Object> request = new HttpEntity<>(headers);

        final ResponseEntity<Person> response = restTemplate.exchange("/persons/1", GET, request, Person.class, "1");

        assertThat(response.getStatusCode(), equalTo(OK));

        final Person body = response.getBody();
        assertThat(body.getFirstName(), equalTo("Tin"));
        assertThat(body.getLastName(), equalTo("Tin"));
        assertThat(body.getDateOfBirth(), equalTo("2000-01-01"));
    }

    @Test
    @DirtiesContext
    public void testSavePerson() {
        final HttpEntity<Object> request = new HttpEntity<>("<person first-name=\"Tin Tin\" last-name=\"Herge\" dob=\"1907-05-22\"></person>", headers);

        final ResponseEntity<Person> response = restTemplate.exchange("/persons/1", PUT, request, Person.class, "1");
        assertThat(response.getStatusCode(), equalTo(OK));

        final Person body = response.getBody();
        assertThat(body.getFirstName(), equalTo("Tin Tin"));
        assertThat(body.getLastName(), equalTo("Herge"));
        assertThat(body.getDateOfBirth(), equalTo("1907-05-22"));
    }

}