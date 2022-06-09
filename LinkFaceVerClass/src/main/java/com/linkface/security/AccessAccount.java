package com.linkface.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.linkface.entity.UserInfo;
import com.linkface.entity.UserStatus;

import lombok.Getter;

@Getter
public class AccessAccount implements UserDetails {

	private static final long serialVersionUID = 1L;
	private UserInfo userInfo;
	private UserStatus userStatus;
	
	public AccessAccount(UserInfo userInfo,UserStatus userStatus) {
		
		this.userInfo = userInfo;
		this.userStatus = userStatus;
		
	}
	// 계정 아이디
	@Override
	public String getUsername() {
		return this.userInfo.getUserId();
	}

	// 계정 패스워드
	@Override
	public String getPassword() {
		return this.userInfo.getUserPassword();
	}
	// 계정 권한
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(
				this.userStatus.getRole()));
	}
	// 계정 만료 여부
	@Override
	public boolean isAccountNonExpired() {
		return this.userStatus.isIdNonExpired();
	}

	// 계정 잠금 여부
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 계정 패스워드 만료 여부
	@Override
	public boolean isCredentialsNonExpired() {
		return this.userStatus.isPasswordNonExpired();
	}
	// 계정 사용 가능 여부
	@Override
	public boolean isEnabled() {
		return this.userStatus.isEnabled();
	}

	
}
