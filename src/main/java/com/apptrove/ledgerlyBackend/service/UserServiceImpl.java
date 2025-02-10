package com.apptrove.ledgerlyBackend.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apptrove.ledgerlyBackend.entities.SecurityLog;
import com.apptrove.ledgerlyBackend.entities.User;
import com.apptrove.ledgerlyBackend.exception.UsernameNotFoundException;
import com.apptrove.ledgerlyBackend.payload.UserDTO;
import com.apptrove.ledgerlyBackend.repository.SecurityLogRepository;
import com.apptrove.ledgerlyBackend.repository.UserRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	private final SecurityLogRepository securityLogRepository;
	
	private final UserRepository userRepository;
	
	private final ModelMapper modelMapper;

	@Override
	public boolean isUserLoggedIn(String username) {
		Date now = new Date();
		try {
			logger.info("Checking if user with username: " + username + " is already logged in");
			int res = securityLogRepository.isUserLoggedIn(username, now);

			return res > 0 ? true : false;
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void logoutUserSession(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkUserSession(String username, String domainName, String sessionId, String ipAddress) {
		return false;

	}

	
	@Override
	public UserDTO loginUser(String username, String domainName, String sessionId, String ipAddress, String token) {
		SecurityLog securityLog = new SecurityLog();
		User user = new User();
		Date now = new Date();
		UserDTO userdto = new UserDTO();
		try {
			user = userRepository.findByUsername(username)
					.orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));
			userdto = user != null ? modelMapper.map(user, UserDTO.class) : null;
			securityLog.setUserId(user.getUserId());
			securityLog.setUsername(username);
			securityLog.setDomainName(domainName);
			securityLog.setLoginDt(now);
			securityLog.setSessionId(sessionId);
			securityLog.setUserActive(true);
			securityLog.setIpAddress(ipAddress);
			securityLog.setUserToken(token);
			securityLogRepository.save(securityLog);
		} catch (Exception e) {
			logger.info("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}

		return userdto;
	}

	@Transactional
	@Override
	public void clearLastSession(String username) {
		Date now = new Date();
		try {
			logger.info("Inside clearLastSession method for username: " + username);
			securityLogRepository.logoutUserMultipleRows(username, now, now);
		} catch (Exception e) {
			logger.error("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Transactional
	@Override
	public boolean logoutUser(String username, String token, String domainName, String ipAddress, String sessionId) {
		Date date = new Date();
		try {
			List<SecurityLog> securityLogList = securityLogRepository.checkLoggedUser(username, token, domainName, ipAddress, sessionId);
			if (securityLogList.size() > 0) {
				this.securityLogRepository.logoutUser(username, token, domainName, ipAddress, sessionId, date);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error("An error occurred: "+e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	
}
