package com.apptrove.ledgerlyBackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptrove.ledgerlyBackend.payload.ApiResponse;
import com.apptrove.ledgerlyBackend.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/ldgr/T2000")
@RequiredArgsConstructor
public class DashboardController {
	
	private static final Logger logger = LogManager.getLogger(DashboardController.class);
	
	private final DashboardService dashboardService;

	private final Environment environment;
	
	@GetMapping("/S2001")
	public ResponseEntity<ApiResponse<Map<String,Object>>> getCurrMonthTransactions() {
		Map<String,Object> currMonthTxnList = new HashMap<String, Object>();
		ApiResponse<Map<String,Object>> apiResponse = new ApiResponse<Map<String,Object>>();
		try {
			currMonthTxnList = dashboardService.getCurrMonthTxnRecords();
			apiResponse = new ApiResponse<Map<String,Object>>(currMonthTxnList, environment.getProperty("common.server.request.success.message"), environment.getProperty("common.request.success.code"));
			return new ResponseEntity<ApiResponse<Map<String,Object>>>(apiResponse,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<ApiResponse<Map<String,Object>>>(new ApiResponse<Map<String,Object>>(null,environment.getProperty("common.server.request.failure.message"), environment.getProperty("common.request.failed.code")),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
