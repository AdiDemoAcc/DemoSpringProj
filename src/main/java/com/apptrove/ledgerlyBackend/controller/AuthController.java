package com.apptrove.ledgerlyBackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptrove.ledgerlyBackend.payload.ApiResponse;
import com.apptrove.ledgerlyBackend.payload.LoginModel;
import com.apptrove.ledgerlyBackend.security.util.JwtUtil;

@RequestMapping("/ldgr/auth")
@RestController
public class AuthController {

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private Environment env;
	
    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginModel loginModel) {
    	Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword()));
    	String token = jwtUtil.generateToken(authentication);
    	return new ResponseEntity<ApiResponse<String>>(new ApiResponse<String>(token, env.getProperty("login.success.message"), env.getProperty("login.user.authenticated")),HttpStatus.OK);
    }
    
}
