package com.ait.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.ait.dto.ErrorDetails;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	//handle specific Exceptions 
	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<ErrorDetails> handlePostNotFoundException(PostNotFoundException exception,WebRequest webRequest){
		 ErrorDetails details = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);
		
	}
	//handle global Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalExceptionn(Exception exception,WebRequest webRequest){
		 ErrorDetails details = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException ex){
		
		Map<String,String> map=new HashMap<>();
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		for(FieldError error:fieldErrors) {
			
			map.put(error.getField(), error.getDefaultMessage());
			
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
	}
	

}
