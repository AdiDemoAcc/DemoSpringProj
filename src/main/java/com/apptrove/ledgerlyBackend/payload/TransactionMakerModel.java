package com.apptrove.ledgerlyBackend.payload;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class TransactionMakerModel {

	private Integer transactionId;
	
	private Date transactionDate;
	
	private Integer aptmntId;
	
	private Date startDate;
	
	private Date endDate;
	
	private Integer glAccntId;
	
	
	private String transactionType;
	
	private BigDecimal transactionAmnt;
	
	private String transactionCategory;
	
	private Integer makerCd;
	
	private Date makerDt;
	
	private String makerRmrks;
}
