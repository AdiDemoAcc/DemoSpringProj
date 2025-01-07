package com.apptrove.ledgerlyBackend.exception;

public class UsernameNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1971362687670105335L;

	public UsernameNotFoundException(String message) {
		super(message);
	}
	
	public UsernameNotFoundException(String message,Throwable cause) {
		super(message, cause);
	}

}
