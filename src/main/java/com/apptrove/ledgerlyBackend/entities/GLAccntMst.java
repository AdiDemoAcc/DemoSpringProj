package com.apptrove.ledgerlyBackend.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "com_ldgr_gl_accnt_mst")
@Data
public class GLAccntMst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gl_accnt_id")
	private Integer glAccntId;
	
	@Column(name = "accnt_id")
	private Long accntId;
	
	@Column(name = "accnt_no")
	private Long accntNo;
	
	@Column(name = "accnt_bal")
	private BigDecimal accntBal;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "bank_branch")
	private String bankBranch;
	
	@Column(name = "bank_city")
	private String bankCity;
	
	@Column(name = "bank_state")
	private String bankState;
	
	@Column(name = "bank_pincode")
	private String bankPincode;
	
	@Column(name = "maker_cd")
	private Integer makerCd;
	
	@DateTimeFormat
	@Column(name = "maker_dt")
	private Date makerDt;
	
	@Column(name = "author_cd")
	private Integer authorCd;
	
	@Column(name = "author_dt")
	private Date authorDt;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
}
