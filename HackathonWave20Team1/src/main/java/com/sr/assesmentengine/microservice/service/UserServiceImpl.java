package com.sr.assesmentengine.microservice.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sr.assesmentengine.microservice.domain.User;
import com.sr.assesmentengine.microservice.exceptions.UserAlreadyExistException;
import com.sr.assesmentengine.microservice.exceptions.UserDoesNotExistException;
import com.sr.assesmentengine.microservice.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService 
{
	public static Logger logger=LogManager.getLogger("UserServiceImpl.class");

	@Autowired
	UserRepository userRepository;
	// Creating a user Profile.
	
	
	@Override
	public User createProfile(User user) throws UserAlreadyExistException {
		User existeduser=userRepository.findOne(user.getId());
		
		logger.debug("Creating profile..");
		if(existeduser != null )
		{
			throw new UserAlreadyExistException("User Already exist");
		}
		else {
		User createdProfile=userRepository.save(user);
		return createdProfile;
		}
	}
	// Updating user Profile.
		
	@Override
	public String updateProfile(User user) throws UserDoesNotExistException {
		
		User existeduser=userRepository.findOne(user.getId());
		if(existeduser == null )
		{
			throw new UserDoesNotExistException("User does not exist");
		}
		else {
		userRepository.save(user);
		return "Successfully user updated";
	}
	}
	// Deleting User Profile.
			

	@Override
	public String deleteProfile(int id) throws UserDoesNotExistException{
		User existeduser=userRepository.findOne(id);
		if(existeduser == null )
		{
			throw new UserDoesNotExistException("User does not exist");
		}
		else {
		userRepository.delete(id);
		return "User profile deleted";
	}
	}
	// Get all users using List.
			

	@Override
	public List<User> getAll() {
		
		return (List<User>) userRepository.findAll();
		
	}
	// Get user by giving ID.
	@Override
	public User getById(int id) throws UserDoesNotExistException{
		User existeduser=userRepository.findOne(id);
		if(existeduser == null )
		{
			throw new UserDoesNotExistException("User does not exist");
		}
		else {
		 User user=userRepository.findOne(id);
		return user;

	}
	}
}
