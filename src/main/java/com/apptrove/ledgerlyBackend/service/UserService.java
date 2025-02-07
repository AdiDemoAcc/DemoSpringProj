package com.apptrove.ledgerlyBackend.service;

import com.apptrove.ledgerlyBackend.payload.UserDTO;

public interface UserService {

	public boolean isUserLoggedIn(String username);
	
	public void logoutUserSession(String username);
	
	public boolean checkUserSession(String username,String domainName,String sessionId,String ipAddress);
	
	public UserDTO loginUser(String username,String domainName,String sessionId,String ipAddress,String token);

	public void clearLastSession(String username);
	
	public boolean logoutUser(String username,String token, String domainName,String ipAddress,String sessionId);
	
}
