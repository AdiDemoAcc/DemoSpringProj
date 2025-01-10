package com.apptrove.ledgerlyBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptrove.ledgerlyBackend.payload.ApiResponse;


@RestController
@RequestMapping("/ldgr/test")
public class TestController {

	@Autowired
	private Environment env;
	
	@GetMapping(path = "/status")
	public ResponseEntity<ApiResponse<String>> status() {
		return new ResponseEntity<ApiResponse<String>>(new ApiResponse<String>("Success",env.getProperty("test.status.success.message"),env.getProperty("test.status.success")),HttpStatus.OK);
	}
	
	
}
