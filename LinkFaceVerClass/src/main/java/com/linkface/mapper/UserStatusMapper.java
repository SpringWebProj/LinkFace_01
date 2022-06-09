package com.linkface.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkface.entity.UserStatus;

public interface UserStatusMapper {

	void insert(Long userKey);
	
	UserStatus select(Long userKey);
	
	void delete(Long userKey);
	
	void updateToken(Long userKey);
	
	void updateEmailAuth(Long userKey);
	
	void updateRole(@Param("role") String role, @Param("userKey") Long userKey);
	
	void updateLoginFailCount(@Param("userKey") Long userKey ,
							@Param("loginFailCount") int loginFailCount);
	
	void updateIdNonExpired(@Param("userKey") Long userKey , 
						@Param("idNonExpired") boolean idNonExpired);
	
	void updatePasswordNonExpired(@Param("userKey") Long userKey , 
			@Param("passwordNonExpired") boolean passwordNonExpired);
	
	void updateEnabled(@Param("userKey") Long userKey , @Param("enabled") boolean enabled);
	
	void updateSanctionsDate(@Param("userKey") Long userKey , @Param("day") int day);
	
	void updateLastLoginDate(Long userKey);
	
	List<UserStatus> readAllUser();
	
}
