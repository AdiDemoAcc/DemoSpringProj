package com.apptrove.ledgerlyBackend.payload;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {
	
	private Integer userId;
	
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String emailId;
	
	private String contactNum;
	
	private Date createdOn;
	
	private Integer roleId;
	
	private String roleName;

}
