package com.apptrove.ledgerlyBackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptrove.ledgerlyBackend.payload.ApartmentOccupantModel;
import com.apptrove.ledgerlyBackend.payload.ApiResponse;
import com.apptrove.ledgerlyBackend.service.ApartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/ldgr/T3000")
@RequiredArgsConstructor
public class ResidentController {

	private static final Logger logger = LogManager.getLogger(ResidentController.class);
	
	private final ApartmentService apartmentService;
	
	private final Environment environment;
	
	@GetMapping("/S3001")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getAllResidents() {
		try {
			Map<String, Object> respObject = new HashMap<String, Object>();
			respObject = apartmentService.getAllResidentDetails();
			ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<Map<String,Object>>(respObject, environment.getProperty("common.server.request.success.message"), environment.getProperty("common.request.success.code"));
			return new ResponseEntity<ApiResponse<Map<String,Object>>>(apiResponse,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			return new ResponseEntity<ApiResponse<Map<String, Object>>>(new ApiResponse<Map<String, Object>>(null, environment.getProperty("common.server.request.failure.message"), environment.getProperty("common.request.failed.code")),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/S3002")
	public ResponseEntity<ApiResponse<Map<String, Object>>> saveNewOccupantData(@RequestBody ApartmentOccupantModel apartmentOccupantModel) {
		try {
			Map<String, Object> respObject = new HashMap<String, Object>();
			respObject = apartmentService.saveNewResident(apartmentOccupantModel);
			ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<Map<String,Object>>(respObject, environment.getProperty("common.server.request.success.message"), environment.getProperty("common.request.success.code"));
			return new ResponseEntity<ApiResponse<Map<String,Object>>>(apiResponse,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("An error occurred: {}",e.getMessage());
			return new ResponseEntity<ApiResponse<Map<String, Object>>>(new ApiResponse<Map<String, Object>>(null, environment.getProperty("common.server.request.failure.message"), environment.getProperty("common.request.failed.code")),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/S3003")
	public ResponseEntity<ApiResponse<Map<String, Object>>> getBldngAndAptmntData() {
		try {
			Map<String, Object> respObject = new HashMap<String, Object>();
			respObject = apartmentService.getBldngAndAptmntData();
			ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<Map<String,Object>>(respObject, environment.getProperty("common.server.request.success.message"), environment.getProperty("common.request.success.code"));
			return new ResponseEntity<ApiResponse<Map<String,Object>>>(apiResponse,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("An error occurred: {}",e.getMessage());
			return new ResponseEntity<ApiResponse<Map<String, Object>>>(new ApiResponse<Map<String, Object>>(null, environment.getProperty("common.server.request.failure.message"), environment.getProperty("common.request.failed.code")),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
