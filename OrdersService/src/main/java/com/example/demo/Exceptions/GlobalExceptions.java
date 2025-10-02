package com.example.demo.Exceptions;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptions {

 @ExceptionHandler(BadRequestException.class)
 public ResponseEntity<?> handleBadRequestException(BadRequestException ex)
 {
	return ResponseEntity
			.status(HttpStatus.BAD_REQUEST)
			.body(createErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST));
 }
	
 @ExceptionHandler(ResourceNotFoundException.class)
 public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex)
 {
	 return ResponseEntity
			 .status(HttpStatus.NOT_FOUND)
			 .body(createErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND));
	 
 }
 
 private Map<String,Object> createErrorResponse(String message,HttpStatus status)
 {
	 return Map.of(
			 "timeStamp",LocalDateTime.now(),
			 "status",status.value(),
			 "error",status.getReasonPhrase(),
			 "message",message
			 );
 }
}
