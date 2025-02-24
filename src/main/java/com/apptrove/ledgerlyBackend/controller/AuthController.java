package com.apptrove.ledgerlyBackend.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apptrove.ledgerlyBackend.payload.ApiResponse;
import com.apptrove.ledgerlyBackend.payload.LoginModel;
import com.apptrove.ledgerlyBackend.payload.UserDTO;
import com.apptrove.ledgerlyBackend.payload.UserSessionCheck;
import com.apptrove.ledgerlyBackend.security.util.JwtUtil;
import com.apptrove.ledgerlyBackend.service.MenuService;
import com.apptrove.ledgerlyBackend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/ldgr/T1000")
@RestController
@CrossOrigin
public class AuthController {
	
	private static final Logger logger = LogManager.getLogger(AuthController.class);

	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private Environment env;
    
    @Autowired
    private MenuService menuService;
	
    @PostMapping(path = "/S1001")
    public ResponseEntity<ApiResponse<Map<String, Object>>> login(@RequestBody LoginModel loginModel,HttpServletRequest httpServletRequest,HttpSession session,HttpServletResponse httpServletResponse) {
    	Map<String, Object> respObject = new HashMap<String, Object>();
    	try {
    		Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword()));
    		boolean userLoggedIn = userService.isUserLoggedIn(loginModel.getUsername());
    		
    		if (loginModel.isClearSession() && userLoggedIn) {
				userService.clearLastSession(loginModel.getUsername());
			}
    		
        	if (!userLoggedIn || loginModel.isClearSession()) {
        		logger.info("Old Session Id: {}",session.getId());
        		session.invalidate();
        		session = httpServletRequest.getSession(true);
        		String domainName = httpServletRequest.getServerName();
        		String ipAddress = httpServletRequest.getRemoteAddr();
        		String sessionId = httpServletRequest.getSession().getId();
        		String token = jwtUtil.generateToken(authentication,httpServletRequest,httpServletResponse);
        		UserDTO user = userService.loginUser(loginModel.getUsername(), domainName, sessionId, ipAddress,token);
        		httpServletRequest.getSession().setAttribute("token", token);
        		httpServletRequest.setAttribute("sessionId", sessionId);
        		respObject.put("user", user);
        		respObject.put("token", token);
        		respObject.put("sessionId", sessionId);
        		logger.info("Attempting login for user: {}", loginModel.getUsername());
				logger.info("Token: {}", token);
				logger.info("Domain: {}", domainName);
				logger.info("IP Address: {}", ipAddress);
				logger.info("Session ID: {}", sessionId);
        		
            	return new ResponseEntity<ApiResponse<Map<String, Object>>>(new ApiResponse<Map<String, Object>>(respObject, env.getProperty("login.success.message"), env.getProperty("login.user.authenticated")),HttpStatus.OK);
			} else {
				return new ResponseEntity<ApiResponse<Map<String, Object>>>(new ApiResponse<Map<String, Object>>(null, env.getProperty("login.user.already.logged.message"), env.getProperty("login.user.failed")),HttpStatus.OK );
			}
    		
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			return new ResponseEntity<ApiResponse<Map<String, Object>>>(new ApiResponse<Map<String, Object>>(null, env.getProperty("login.fail.message"), env.getProperty("login.user.notAuthenticated")),HttpStatus.OK);
		}
    }
    
    @PostMapping("/S1002")
    public ResponseEntity<ApiResponse<String>> logoutUser(@RequestBody Map<String,Object> reqObj,HttpServletRequest httpServletRequest) {
    	String sessionId = "";
        try {
			String token = httpServletRequest.getHeader("Authorization").substring(7);
			if (token != null && token != "" && reqObj.containsKey("username") && reqObj.containsKey("sessionId")) {
				
				sessionId = reqObj.get("sessionId").toString();
				
				String domainName = httpServletRequest.getServerName();
				String ipAddress = httpServletRequest.getRemoteAddr();
				String username = reqObj.get("username").toString();
				
				logger.info("Attempting logout for user: {}", username);
				logger.info("Token: {}", token);
				logger.info("Domain: {}", domainName);
				logger.info("IP Address: {}", ipAddress);
				logger.info("Session ID: {}", sessionId);
				
				boolean flag = this.userService.logoutUser(username, token, domainName, ipAddress, sessionId);
				if (flag) {
					return new ResponseEntity<ApiResponse<String>>(new ApiResponse<String>("User logout successfull", env.getProperty("logout.success.message"), env.getProperty("logout.user.success.code")),HttpStatus.OK);
				} else {
					return new ResponseEntity<ApiResponse<String>>(new ApiResponse<String>("User Session Expired", env.getProperty("logout.fail.message"), env.getProperty("logout.user.fail.code")),HttpStatus.CONFLICT);
				}
			}
			return new ResponseEntity<ApiResponse<String>>(new ApiResponse<String>("User Session Expired", env.getProperty("logout.fail.message"), env.getProperty("logout.user.fail.code")),HttpStatus.CONFLICT);
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			return new ResponseEntity<ApiResponse<String>>(new ApiResponse<String>(e.getMessage(), env.getProperty("logout.fail.message"), env.getProperty("logout.user.fail.code")),HttpStatus.INTERNAL_SERVER_ERROR);
		}
        
    }
    
    @PostMapping("/S1003")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getRoleBasedMenu(@RequestBody Integer roleId) {
    	Map<String, Object> respObject = new HashMap<String, Object>();
    	ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<Map<String,Object>>();
    	try {
			respObject = menuService.getMenuMap(roleId);
			apiResponse.setRespObject(respObject);
			apiResponse.setErrorMsg(env.getProperty("common.server.request.success.message"));
			apiResponse.setErrorCd(env.getProperty("common.request.success.code"));
			return new ResponseEntity<ApiResponse<Map<String,Object>>>(apiResponse,HttpStatus.OK);
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			return new ResponseEntity<ApiResponse<Map<String, Object>>>(new ApiResponse<Map<String, Object>>(null, env.getProperty("common.server.request.failure.message"), env.getProperty("common.request.failed.code")),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    @PostMapping("/S1004")
    public ResponseEntity<ApiResponse<Boolean>> validateUserSession(@RequestBody UserSessionCheck reqObj, HttpServletRequest request) {
        try {
        	String token = request.getHeader("Authorization").substring(7);
        	String ipAddress = request.getRemoteAddr();
			Boolean flag = userService.checkUserSession(reqObj.getUsername(), reqObj.getSessionId(), ipAddress, token);
			ApiResponse<Boolean> apiResponse = new ApiResponse<Boolean>(flag, env.getProperty("common.server.request.success.message"), env.getProperty("common.request.success.code"));
			return new ResponseEntity<ApiResponse<Boolean>>(apiResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			return new ResponseEntity<ApiResponse<Boolean>>(new ApiResponse<Boolean>(false, env.getProperty("common.server.request.failure.message"), env.getProperty("common.request.failed.code")),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
    
    
}
