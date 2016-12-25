package org.behrang.howto.springdata.controllers;

import org.behrang.howto.springdata.entities.Person;
import org.behrang.howto.springdata.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

/**
 * @author Behrang Saeedzadeh
 */
@RestController
@RequestMapping(
        path = "/persons",
        consumes = APPLICATION_XML_VALUE,
        produces = APPLICATION_XML_VALUE
)
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(final PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/{id}")
    public Person getPerson(@PathVariable Long id) {
        return personRepository.findOne(id);
    }

    @PutMapping(value = "/{person}")
    public Person savePerson(@ModelAttribute Person person) {
        return personRepository.save(person);
    }

}
