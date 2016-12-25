# HOWTO Spring Data

A How-To guide for Spring Data

### HOW TO consume XML and update an existing JPA entity using Spring Data JPA

```gherkin
Feature: Spring Data JPA
  Scenario: Update a JPA entity with XML payload 
    Given I have a person in the database
     When I send a PUT request with XML to update the person's attributes
     Then the person's columns should be updated in the database
```

See:

* `org.behrang.howto.springdata.controllers.PersonController`
* `org.behrang.howto.springdata.controllers.PersonControllerTest.testSavePerson`