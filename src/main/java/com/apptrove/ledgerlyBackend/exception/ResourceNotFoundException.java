package com.apptrove.ledgerlyBackend.exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -8106444158635233475L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(String message,Throwable cause) {
		super(message,cause);
	}
}
