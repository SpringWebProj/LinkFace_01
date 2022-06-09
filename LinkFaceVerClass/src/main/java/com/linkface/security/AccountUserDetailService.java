package com.linkface.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.linkface.entity.UserInfo;
import com.linkface.entity.UserStatus;
import com.linkface.mapper.UserInfoMapper;
import com.linkface.mapper.UserStatusMapper;

import lombok.Setter;

// 사용자 인증 객체 생성
@Component
public class AccountUserDetailService implements UserDetailsService {

	@Setter(onMethod_=@Autowired)
	private UserInfoMapper userInfoMapper;
	@Setter(onMethod_=@Autowired)
	private UserStatusMapper UserStatusMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		UserInfo userInfo = userInfoMapper.readId(userId);
		
		if(userInfo == null) { throw new UsernameNotFoundException(userId); }
		
		UserStatus userStatus = 
				UserStatusMapper.select(userInfo.getUserKey());				
		return new AccessAccount(userInfo,userStatus);
	}
	
}
