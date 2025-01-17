package com.apptrove.ledgerlyBackend.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.apptrove.ledgerlyBackend.payload.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity< ApiResponse<String>> usernameNotFoundExceptionHandler(UsernameNotFoundException ex) {
		ApiResponse<String> apiResponse = new ApiResponse<String>();
		apiResponse.setRespObject(ex.getMessage());
		apiResponse.setErrorMsg(ex.getMessage());
		apiResponse.setErrorCd("USER_NOT_FOUND");
		
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ApiResponse<String>> exceptionHandler(Exception ex) {
		ApiResponse<String> apiResponse = new ApiResponse<String>();
		apiResponse.setRespObject("An error occurred!!");
		apiResponse.setErrorMsg(ex.getMessage());
		apiResponse.setErrorCd("INTERNAL_SERVER_ERROR");
		
		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
