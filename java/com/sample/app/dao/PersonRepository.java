
package com.sample.app.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sample.app.model.Person;

//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface PersonRepository extends MongoRepository<Person, String> {

	List<Person> findByEmail(@Param("email") String email);
	
	List<Person> findByFirstName(@Param("firstName") String firstName);

}
