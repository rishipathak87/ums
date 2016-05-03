package com.sample.app.controller;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.model.Person;
import com.sample.app.dao.PersonRepository;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private PersonRepository PersonRepository;

  @RequestMapping(method = RequestMethod.POST, value="/registerUser")
  public Map<String, Object> createUser(@RequestBody Map<String, Object> userMap){
    Person person = new Person(userMap.get("firstName").toString(),
    		userMap.get("email").toString(),
    		userMap.get("role").toString(),
    		userMap.get("token").toString());

    PersonRepository.save(person);
    Map<String, Object> response = new LinkedHashMap<String, Object>();
    response.put("message", "User Registered successfully");
    response.put("user", person);
    return response;
  }

  @RequestMapping(method = RequestMethod.GET, value="/user/email/{email}")
  public Person getUserDetails(@PathVariable("email") String email){
    return PersonRepository.findOne(email);
  }
  
  @RequestMapping(method = RequestMethod.GET, value="/user/name/{firstName}")
  public Person getUserDetailsByName(@PathVariable("firstName") String firstName){
    return PersonRepository.findOne(firstName);
  }
  
}
