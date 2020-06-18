package com.example.spring.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.spring.domain.exception.BusinessException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	//to format response "BusinessException"
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleBusiness(BusinessException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		
		var exceptionBody = new ExceptionBody();
		exceptionBody.setStatus(status.value());
		exceptionBody.setTittle(ex.getMessage());
		exceptionBody.setDateTime(LocalDateTime.now());
		
		return super.handleExceptionInternal(ex, exceptionBody, new HttpHeaders(), status, request);
	}
	
	//to format response "MethodArgumentNotValidException"
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var exceptionBody = new ExceptionBody();
		exceptionBody.setStatus(status.value());
		exceptionBody.setTittle("One or more fields are invalids. Try correct values.");
		exceptionBody.setDateTime(LocalDateTime.now());
		
		var fields = new ArrayList<ExceptionBody.Field>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
//			String message = error.getDefaultMessage(); //Get default message 
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale()); //Get message from "messages.properties" 
			fields.add(new ExceptionBody.Field(name, message));
		}
		exceptionBody.setFields(fields);
		
		return super.handleExceptionInternal(ex, exceptionBody, headers, status, request);
	}

}
