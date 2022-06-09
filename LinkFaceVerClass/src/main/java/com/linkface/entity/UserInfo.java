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
public class UserInfo {
	
	private Long userKey;
	
	private String userId;
	
	private String userPassword;
	
	private String userEmail;
	
	private String userName;
	
	private Date createDate;
	
	private Date updateDate;

}
