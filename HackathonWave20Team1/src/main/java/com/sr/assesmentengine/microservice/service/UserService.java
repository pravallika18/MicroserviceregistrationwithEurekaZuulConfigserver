package com.sr.assesmentengine.microservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sr.assesmentengine.microservice.domain.User;
import com.sr.assesmentengine.microservice.exceptions.UserAlreadyExistException;
import com.sr.assesmentengine.microservice.exceptions.UserDoesNotExistException;
@Service
public interface UserService  //User Interface to declare the oprating abstract methods
{
	//methods declarations.
	public User createProfile(User user) throws UserAlreadyExistException;
	public String updateProfile(User user)throws UserDoesNotExistException;
	public String deleteProfile(int id) throws UserDoesNotExistException ;
	public List<User> getAll();
	public User getById(int id) throws UserDoesNotExistException;
	


}