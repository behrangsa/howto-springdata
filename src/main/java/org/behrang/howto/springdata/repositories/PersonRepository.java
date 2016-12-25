package org.behrang.howto.springdata.repositories;

import org.behrang.howto.springdata.entities.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Behrang Saeedzadeh
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
