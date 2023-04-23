package com.bridgelabz.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserAdeviceController {
	
	@ExceptionHandler(Userexception.class)
	public ResponseEntity<Object> expectionHandler(Userexception userexception){
	      String message =userexception.getMessage();
	      return new ResponseEntity<Object>(message,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
		Map<String,String> map =new HashMap<>();
		 ex.getBindingResult().getAllErrors().forEach((error)->{
			 String fieldName= ((FieldError)error).getField();
			 String errorMessage =error.getDefaultMessage();
			 map.put(fieldName, errorMessage); 
		 });
		 return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
	}

}
