package com.apptrove.ledgerlyBackend.payload;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class GLAccntTxn {
	
	private Date transactionDate;
	
	private Integer glAccntId;
	
	private Integer aptmntId;
	
	private String transactionType;
	
	private String transactionCategory;
	
//	private BigDecimal originalBalance;
	
	private BigDecimal transactionAmnt;

	private BigDecimal glAccntBal;
	
	
}
