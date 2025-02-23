package com.apptrove.ledgerlyBackend.payload;

import java.util.Date;

import lombok.Data;

@Data
public class ApartmentOccupantModel {
	
	private Integer occupantId;
	
	private Integer aptmntId;
	
	private String occupantType;
	
	private String occupantName;
	
	private Integer isLeased;
	
	private Date leaseStartDate;
	
	private Date leaseEndDate;
	
	private Integer makerCd;
	
	private Date makerDt;
	
	private String makerRmrks;
	
	private Integer authorCd;
	
	private Date authorDt;
	
	private String authorRmrks;
	
	private Integer isActive;

}
