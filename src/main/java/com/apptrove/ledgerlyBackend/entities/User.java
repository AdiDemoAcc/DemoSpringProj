package com.apptrove.ledgerlyBackend.entities;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails{
	
	private Integer userId;
	
	private String username;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	private String contactNum;
	
	private Date createdOn;
	
	private Date validTill;
	
	private Integer makerCd;
	
	private Date makerDt;
	
	private Integer authorCd;
	
	private Date authorDt;
	
	private Integer loginTries;
	
	private Date lastLoginDate;
	
	private Boolean isActive;
	
	private Boolean accountLocked;
	
	private Boolean credentialBlocked;
	
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

}
