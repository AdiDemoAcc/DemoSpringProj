package com.apptrove.ledgerlyBackend.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class ApiResponse<T> {

	private T respObject;
	
	private String errorMsg;
	
	private String errorCd;
}
