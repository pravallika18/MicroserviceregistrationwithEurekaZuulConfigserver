package com.sr.assesmentengine.microservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.sr.assesmentengine.microservice.domain.User;



public interface UserRepository  extends CrudRepository<User, Integer>{
 


}
