package com.apptrove.ledgerlyBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apptrove.ledgerlyBackend.entities.SecurityLog;
import java.util.List;
import java.util.Date;

public interface SecurityLogRepository extends JpaRepository<SecurityLog, Integer> {

	@Query("FROM SecurityLog WHERE username=:username AND userToken=:userToken and domainName=:domainName AND ipAddress=:ipAddress AND sessionId=:sessionId")
	public List<SecurityLog> checkLoggedUser(@Param("username") String username,@Param("userToken") String token,@Param("domainName") String domainName,@Param("ipAddress") String ipAddress,
			@Param("sessionId") String sessionId);

	@Query("SELECT COUNT(*) FROM SecurityLog WHERE username=:username AND DATE(loginDt)=DATE(:loginDt) AND logoutDt IS NULL")
	public Integer isUserLoggedIn(@Param("username") String username, @Param("loginDt") Date loginDt);

	public boolean existsByUsernameAndLoginDtAndLogoutDt(String username, Date loginDt, Date logoutDt);

	@Modifying
	@Query("UPDATE SecurityLog SET logoutDt=:logoutDt,userActive=false WHERE username=:username AND DATE(loginDt)=DATE(:loginDt) AND logoutDt IS NULL")
	public void logoutUserMultipleRows(@Param("username") String username, @Param("loginDt") Date loginDt,
			@Param("logoutDt") Date logoutDt);

	@Modifying
	@Query("UPDATE SecurityLog SET logoutDt=:logoutDt,userActive=false WHERE username=:username AND userToken=:userToken and domainName=:domainName AND ipAddress=:ipAddress AND sessionId=:sessionId AND logoutDt IS NULL")
	public void logoutUser(@Param("username") String username,@Param("userToken") String token,@Param("domainName") String domainName,@Param("ipAddress") String ipAddress, @Param("sessionId") String sessionId,@Param("logoutDt") Date logoutDt);
	
	@Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM SecurityLog s WHERE s.username = :username AND s.userToken = :userToken AND s.ipAddress = :ipAddress AND DATE(loginDt)=DATE(:loginDt) AND s.sessionId = :sessionId AND s.userActive = :userActive AND s.logoutDt IS NULL")
	boolean validateSession(@Param("username") String username, @Param("userToken") String token, @Param("ipAddress") String ipAddress, @Param("loginDt") Date loginDt, @Param("sessionId") String sessionId, @Param("userActive") boolean userActive);

}
