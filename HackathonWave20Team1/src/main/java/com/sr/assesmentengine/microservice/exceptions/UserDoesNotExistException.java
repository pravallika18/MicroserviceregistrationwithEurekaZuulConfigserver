package com.sr.assesmentengine.microservice.exceptions;

public class UserDoesNotExistException extends Exception{
	public UserDoesNotExistException(String message)
	{
		super(message);
	}

}
