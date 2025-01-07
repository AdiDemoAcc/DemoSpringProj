package com.apptrove.ledgerlyBackend.entities;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "com_ldgr_user_mst", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"username","email_id","contact_num"})
})
public class User implements UserDetails{
	
	private static final long serialVersionUID = -8832143787814637390L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "contact_num")
	private String contactNum;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "valid_till")
	private Date validTill;
	
	@Column(name = "maker_cd")
	private Integer makerCd;
	
	@Column(name = "maker_dt")
	private Date makerDt;
	
	@Column(name = "author_cd")
	private Integer authorCd;
	
	@Column(name = "author_dt")
	private Date authorDt;
	
	@Column(name = "login_tries")
	private Integer loginTries;
	
	@Column(name = "last_login_date")
	private Date lastLoginDate;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "account_locked")
	private Boolean accountLocked;
	
	@Column(name = "credential_blocked")
	private Boolean credentialBlocked;
	
	@ManyToMany
	@JoinTable(
			name = "com_ldgr_user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName().toString()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return new Date().before(this.validTill);
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return !this.accountLocked;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return !this.credentialBlocked;
	}
	
	@Override
	public boolean isEnabled() {
		return this.isActive;
	}
}
