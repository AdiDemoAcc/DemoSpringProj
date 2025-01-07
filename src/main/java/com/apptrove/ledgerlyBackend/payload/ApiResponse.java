package com.apptrove.ledgerlyBackend.payload;

import lombok.Data;

@Data
public class ApiResponse<T> {

	private T respObject;
	
	private String errorMsg;
	
	private String errorCd;
}
