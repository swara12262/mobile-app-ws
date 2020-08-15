package com.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.app.ws.ui.model.reponse.ErrorMessage;

@ControllerAdvice
public class AppExceptionsHandler {

	@ExceptionHandler(value= {UserServiceException.class})
	public ResponseEntity<Object> handleUserServiceExceptions(UserServiceException ex, WebRequest req){
		ErrorMessage erroMessage=new ErrorMessage(new Date(), ex.getMessage());
			
		return new ResponseEntity<>(erroMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest req){
		ErrorMessage erroMessage=new ErrorMessage(new Date(), ex.getMessage());
			
		return new ResponseEntity<>(erroMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}
