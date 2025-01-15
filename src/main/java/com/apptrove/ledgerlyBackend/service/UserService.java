package com.apptrove.ledgerlyBackend.service;

public interface UserService {

	public boolean isUserLoggedIn(String username);
	
	public void logoutUserSession(String username);
	
	public void checkUserSession(String username, String sessionId);
	
	public void loginUser(String username,String domainName,String ipAddress);
	
}
