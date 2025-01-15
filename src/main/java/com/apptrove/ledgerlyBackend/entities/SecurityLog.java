package com.apptrove.ledgerlyBackend.entities;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "com_ldgr_user_sec_log")
@Data
public class SecurityLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "login_dt")
	private Date loginDt;
	
	@Column(name = "logout_dt")
	private Date logoutDt;
	
	@Column(name = "user_active")
	private boolean userActive;
	
	@Column(name = "domain_name")
	private String domainName;
	
	@Column(name = "session_id")
	private String sessionId;
	
	@Column(name = "ip_address")
	private String ipAddress;
	
}
