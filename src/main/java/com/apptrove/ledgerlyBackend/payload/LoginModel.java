package com.apptrove.ledgerlyBackend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginModel {
	
	private String username;
	
	private String password;
	
	private boolean clearSession = false;

}
