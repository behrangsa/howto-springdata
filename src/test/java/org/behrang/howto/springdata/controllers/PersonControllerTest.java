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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
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
    }

    @Test
    public void testGetPersonXml() {
        headers.setContentType(APPLICATION_XML);

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
    public void testSavePersonXml() {
        headers.setContentType(APPLICATION_XML);

        final HttpEntity<Object> request = new HttpEntity<>("<person first-name=\"Tin Tin\" last-name=\"Herge\" dob=\"1907-05-22\"></person>", headers);

        final ResponseEntity<Person> response = restTemplate.exchange("/persons/1", PUT, request, Person.class, "1");
        assertThat(response.getStatusCode(), equalTo(OK));

        final Person body = response.getBody();
        assertThat(body.getFirstName(), equalTo("Tin Tin"));
        assertThat(body.getLastName(), equalTo("Herge"));
        assertThat(body.getDateOfBirth(), equalTo("1907-05-22"));
    }

    @Test
    @DirtiesContext
    public void testSavePersonJson() {
        headers.setContentType(APPLICATION_JSON_UTF8);

        String requestBody = "{\n" +
                "\t\"firstName\": \"Tin Tin\",\n" +
                "\t\"lastName\": \"Harge\",\n" +
                "\t\"dateOfBirth\", \"1907-05-22\"\n" +
                "}";

        final HttpEntity<Object> request = new HttpEntity<>(requestBody, headers);

        final ResponseEntity<Person> response = restTemplate.exchange("/persons/1", PUT, request, Person.class, "1");
        assertThat(response.getStatusCode(), equalTo(OK));

        final Person responseBody = response.getBody();
        assertThat(responseBody.getFirstName(), equalTo("Tin Tin"));
        assertThat(responseBody.getLastName(), equalTo("Herge"));
        assertThat(responseBody.getDateOfBirth(), equalTo("1907-05-22"));
    }

    @Test
    @DirtiesContext
    public void testSavePersonFormUrlEncoded() {
        headers.setContentType(APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("firstName", "Tin Tin");
        requestBody.add("lastName", "Herge");
        requestBody.add("dateOfBirth", "1907-05-22");

        final HttpEntity<Object> request = new HttpEntity<>(requestBody, headers);

        final ResponseEntity<Person> response = restTemplate.exchange("/persons/1", PUT, request, Person.class, "1");
        assertThat(response.getStatusCode(), equalTo(OK));

        final Person responseBody = response.getBody();
        assertThat(responseBody.getFirstName(), equalTo("Tin Tin"));
        assertThat(responseBody.getLastName(), equalTo("Herge"));
        assertThat(responseBody.getDateOfBirth(), equalTo("1907-05-22"));
    }

    @Test
    public void testEchoPersonXmlPut() {
        headers.setContentType(APPLICATION_XML);

        String requestBody = "<person first-name=\"Tin Tin\"></person>";

        final HttpEntity<Object> request = new HttpEntity<>(requestBody, headers);

        final ResponseEntity<Person> response = restTemplate.exchange("/persons/echo", PUT, request, Person.class);
        assertThat(response.getStatusCode(), equalTo(OK));

        final Person responseBody = response.getBody();
        assertThat(responseBody.getFirstName(), equalTo("Tin Tin"));
    }


    @Test
    public void testEchoPersonJsonPut() {
        headers.setContentType(APPLICATION_JSON_UTF8);

        String requestBody = "{\n" +
                "\t\"firstName\": \"Tin Tin\",\n" +
                "\t\"lastName\": \"Harge\",\n" +
                "\t\"dateOfBirth\", \"1907-05-22\"\n" +
                "}";

        final HttpEntity<Object> request = new HttpEntity<>(requestBody, headers);

        final ResponseEntity<Person> response = restTemplate.exchange("/persons/echo", PUT, request, Person.class);
        assertThat(response.getStatusCode(), equalTo(OK));

        final Person responseBody = response.getBody();
        assertThat(responseBody.getFirstName(), equalTo("Tin Tin"));
    }

}