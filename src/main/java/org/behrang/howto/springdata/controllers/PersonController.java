package org.behrang.howto.springdata.controllers;

import org.behrang.howto.springdata.entities.Person;
import org.behrang.howto.springdata.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author Behrang Saeedzadeh
 */
@RestController
@RequestMapping(
        path = "/persons"
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

    @RequestMapping(value = "/echo", method = {PUT, POST, PATCH})
    public Person echoPerson(@RequestBody Person person) {
        return person;
    }

}
