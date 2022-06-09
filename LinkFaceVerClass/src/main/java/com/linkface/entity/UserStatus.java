package com.linkface.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserStatus {

	private Long userKey;
	
	private String role;
	
	private boolean enabled;
	
	private boolean idNonExpired;
	
	private boolean passwordNonExpired;
	
	private boolean emailAuth;
	
	private String authToken;
	
	private Date tokenCreateDate; 
	
	private Date sanctionsDate;
	
	private Date lastLoginDate;
	
	private int loginFailCount;
}
