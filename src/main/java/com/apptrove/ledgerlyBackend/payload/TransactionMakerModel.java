package com.apptrove.ledgerlyBackend.payload;

import java.util.Date;

import lombok.Data;

@Data
public class TransactionMakerModel {

	private Integer transactionId;
	
	private Date transactionDt;
	
	private Integer aptmntId;
	
	private Date startDt;
	
	private Date endDt;
	
	private String transactionType;
	
	private Long transactionAmnt;
	
	private Integer makerCd;
	
	private Date makerDt;
	
	private String makerRmrks;
}
