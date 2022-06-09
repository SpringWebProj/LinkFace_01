package com.linkface.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.linkface.entity.UserInfo;

public interface UserInfoMapper {

	void insert(UserInfo userinfo);
	
	UserInfo select(Long userKey);
	
	UserInfo readId(String userId);
	
	UserInfo readEmail(String userEmail);
	
	List<String> readAllUserId();
	
	int updateEmail(@Param("userEmail") String userEmail,@Param("userKey") Long userKey);
	
	int updatePassword(@Param("userPassword") String userPassword,@Param("userKey") Long userKey);
	
	int updateName(@Param("userName") String userName,@Param("userKey") Long userKey);
	
	void changeUpdateDate(Long userKey);
	
	void delete(Long userKey);
	
	
}
