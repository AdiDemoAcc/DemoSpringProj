package com.apptrove.ledgerlyBackend.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "com_ldgr_aptmnt_mst")
@Data
public class ApartmentMst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "aptmnt_id")
	private Integer aptmntId;
	
	@Column(name = "aptmnt_desc")
	private String aptmntDesc;
	
	@Column(name = "aptmnt_no")
	private String aptmntNo;
	
	@Column(name = "bldng_id")
	private Integer bldngId;
	
	@Column(name = "maker_id")
	private Integer makerId;
	
	@Column(name = "maker_date")
	private Date makerDate;
	
	@Column(name = "author_id")
	private Integer authorId;
	
	@Column(name = "author_date")
	private Date authorDate;
	
	@Column(name = "is_active")
	private Integer isActive;
}
