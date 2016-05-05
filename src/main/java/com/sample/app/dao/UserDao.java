
package com.sample.app.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.sample.app.model.User;

//@RepositoryRestResource(collectionResourceRel = "user", path = "user")
@Repository
@EnableMongoRepositories
public interface UserDao extends MongoRepository<User, String> {

	List<User> findByEmail(String email);
	
	List<User> findByFirstName(String firstName);
	
	User findPasswordByEmail(String email);
	
	List<User> findAll();
	
	

}
