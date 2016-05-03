
package com.sample.app.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.sample.app.model.Person;

//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
@Repository
public interface PersonRepository extends MongoRepository<Person, String> {

	List<Person> findByEmail(String email);
	
	List<Person> findByFirstName(String firstName);
	
	Person findPasswordByEmail(String email);
	
	List<Person> findAll();
	
	

}
