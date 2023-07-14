package com.example.drone.controller.advice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.time.LocalDateTime;

import com.example.drone.model.error.ValidationErrorResponse;
import com.example.drone.model.error.Violation;
import com.example.drone.util.ErrorCode;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {
	
	  @ExceptionHandler(ConstraintViolationException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ResponseBody
	  ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.setStatus(BAD_REQUEST);
		error.setErrorMessage(ErrorCode.VALIDATION_ERRORS_106);
		error.setTimestamp(LocalDateTime.now());
	    for (ConstraintViolation violation : e.getConstraintViolations()) {
	      error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
	    }
	    return error;
	  }

	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ResponseBody
	  ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ValidationErrorResponse error = new ValidationErrorResponse();
		error.setStatus(BAD_REQUEST);
		error.setErrorMessage(ErrorCode.VALIDATION_ERRORS_106);
		//error.setTimestamp(LocalDateTime.now());
	    for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
	    	//System.out.println("fieldError.getDefaultMessage() " + fieldError.getDefaultMessage());
	    	//System.out.println("fieldError.toString() " + fieldError.toString());
	    	error.getViolations().add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
	    }
	    return error;
	  }
}
