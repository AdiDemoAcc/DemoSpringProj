package com.apptrove.ledgerlyBackend.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "com_ldgr_aptmnt_occpnt")
@Data
public class ApartmentOccupant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "occupant_id")
	private Integer occupantId;
	
	@ManyToOne
	@JoinColumn(name = "aptmnt_id", nullable = false)
	private ApartmentMst aptmnt;
	
	@Column(name = "occupant_type")
	private String occupantType;
	
	@Column(name = "occupant_name")
	private String occupantName;
	
	@Column(name = "is_leased")
	private Integer isLeased;
	
	@Column(name = "lease_start_date")
	private Date leaseStartDate;
	
	@Column(name = "lease_end_date")
	private Date leaseEndDate;
	
	@Column(name = "maker_id")
	private Integer makerCd;
	
	@Column(name = "maker_date")
	private Date makerDt;
	
	@Column(name = "maker_rmrks")
	private String makerRmrks;
	
	@Column(name = "author_id")
	private Integer authorCd;
	
	@Column(name = "author_date")
	private Date authorDt;
	
	@Column(name = "author_rmrks")
	private String authorRmrks;
	
	@Column(name = "is_active")
	private Integer isActive;
	
}
