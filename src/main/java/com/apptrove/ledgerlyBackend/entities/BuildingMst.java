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
@Table(name = "com_ldgr_bldng_mst")
@Data
public class BuildingMst {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bldng_id")
	private Integer bldngId;
	
	@Column(name = "bldng_block")
	private String buildingBlock;
	
	@Column(name = "bldng_name")
	private String buildingName;
	
	@Column(name = "no_of_apartments")
	private Integer noOfApartments;
	
	@Column(name = "maker_id")
	private Integer makerId;
	
	@Column(name = "maker_date")
	private Date makerDt;
	
	@Column(name = "author_id")
	private Integer authorId;
	
	@Column(name = "author_date")
	private Date authorDt;
	
	@Column(name = "is_active")
	private Integer isActive;
}
