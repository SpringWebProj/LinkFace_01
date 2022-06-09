package com.linkface.service;

import org.apache.ibatis.annotations.Param;

import com.linkface.entity.UserInfo;

public interface UserCRUDService {
	// UserInfo
	public void registerInfo(UserInfo userinfo);
	
	public UserInfo getByKey(Long userKey);
	
	public UserInfo getById(String userId);
	
	public UserInfo getByEmail(String userEmail);
	
	public boolean modifyEmail(@Param("userEmail") String userEmail,@Param("userKey") Long userKey);
	
	public boolean modifyPassword(@Param("userPassword") String userPassword,@Param("userKey") Long userKey);
	
	public boolean modifyName(@Param("userName") String userName,@Param("userKey") Long userKey);
	
	public void modifyUpdateDate(Long userKey);
	
	// UserStatus
//	public void registerStatus(Long userKey);
//	
//	public UserStatus getStatus(Long userKey);
//	
//	public void modifyToken(Long userKey);
//	
//	public void modifyEmailAuth(Long userKey);
//	
//	public void modifyRole(@Param("role") String role, @Param("userKey") Long userKey);
}
