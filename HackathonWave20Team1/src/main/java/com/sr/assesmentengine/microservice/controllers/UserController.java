package com.sr.assesmentengine.microservice.controllers;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sr.assesmentengine.microservice.domain.User;
import com.sr.assesmentengine.microservice.exceptions.UserAlreadyExistException;
import com.sr.assesmentengine.microservice.exceptions.UserDoesNotExistException;
import com.sr.assesmentengine.microservice.exceptions.UsernameAndEmailIdIsRequiredException;
import com.sr.assesmentengine.microservice.message.KafkaProducer;

import com.sr.assesmentengine.microservice.service.UserService;
@RefreshScope
@RestController
@RequestMapping("/v2.0/userprofile/user")
public class UserController {
	public static Logger logger=LogManager.getLogger("UserController.class");
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	//when we pass GET Requestwith specified path in Request mapping,this method will get executed
	 @RequestMapping(method=RequestMethod.GET)
	   public ResponseEntity<List<?>> getAllUsers()
		{
		 List<User> users;
		 try {
				 users=userService.getAll();
			  	logger.debug("Getting All users..");
				return ResponseEntity.ok(users);
		 }
				catch (Exception e) {
					List l=new ArrayList();
					l.add(e.getMessage());
				return ResponseEntity.ok(l);
				}
		 
		
		  
		}
	  
	//when we pass POST Request with specified path in Request mapping,this method will get executed
	  @RequestMapping(method=RequestMethod.POST)
	  public ResponseEntity<String> createUser(@RequestBody User user)  
	  {
		  String status="";
	  
		  try {
		  if(user.getName().isEmpty()||user.getEmailId().isEmpty())
		  {
			  throw new UsernameAndEmailIdIsRequiredException("User name and EmailId is mandatory to create user profile");
		  }
		  else {
			  try {
			  User savedUser=userService.createProfile(user);
			  status="User saved successfully";
			  }
			   catch (UserAlreadyExistException e) {
				  return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
			}    
		  }
		  }
		  catch(UsernameAndEmailIdIsRequiredException ex)
		  {
			  return new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
		  }
		  
		  return ResponseEntity.ok(status);
	  	}
	  
	//when we pass PUT Request  specified path in Request mapping,this method will get executed
	  @RequestMapping(method=RequestMethod.PUT)
	  public ResponseEntity<String> updateUser(@RequestBody User user) throws Exception  {
	  
		  try {
		  String status=userService.updateProfile(user);
		  return ResponseEntity.ok("Successfully user updated");
	 }
		  catch(UserDoesNotExistException e)
		  {
			  return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT); 
		  }
	  }
	  
	//when we pass DELETE Request with specified path in Request mapping,this method will get executed
	  @RequestMapping(method=RequestMethod.DELETE)
	  public ResponseEntity<String> deleteUser(@PathVariable int id)  {
		  try {
		  String status=userService.deleteProfile(id);
		  return ResponseEntity.ok(status);
		  }
		  
		  catch(UserDoesNotExistException e)
		  {
			  return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT); 
		  } 
	   
	  }
	//when we pass GET Request with specified path in Request mapping by id,this method will get executed
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
		public ResponseEntity<User> getUserById(@PathVariable int id)  {
		try {
			User user=userService.getById(id);
			
			return ResponseEntity.ok(user);
		
		}
		 catch(UserDoesNotExistException e)
		  {
			 logger.error("User does not exist");
			  return null;
		  } 
		}
	
	}

	


