package com.example.demo.Exceptions;
/**
 * Custom exception for handling bad requests (HTTP 404).
 */

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}
