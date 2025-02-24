package com.apptrove.ledgerlyBackend.payload;

import lombok.Data;

@Data
public class UserSessionCheck {

	private String sessionId;
	
	private String username;
	
}
