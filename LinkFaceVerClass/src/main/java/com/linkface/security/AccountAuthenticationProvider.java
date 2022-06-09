package com.linkface.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.linkface.mapper.UserStatusMapper;

import lombok.Setter;

public class AccountAuthenticationProvider implements AuthenticationProvider {

	@Setter(onMethod_=@Autowired)
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Setter(onMethod_=@Autowired)
	private AccountUserDetailService accountUserDetailService;
	
	@Setter(onMethod_=@Autowired)
	private UserStatusMapper userStatusMapper;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		boolean permit = false;
		
		// 입력 아이디
		String id = authentication.getName();
		
		// 입력 패스워드
		String password = (String) authentication.getCredentials();

		// DB 에서 해당 ID 조회
		AccessAccount account = (AccessAccount) accountUserDetailService.loadUserByUsername(id);
		
		if(account == null)	{ 
			throw new BadCredentialsException(id); 
		}
		else if(!account.isAccountNonExpired()) { 
			throw new AccountExpiredException(id);
		} 
		else if(!account.isCredentialsNonExpired()) { 
			throw new CredentialsExpiredException(id); 
		} 
		else if(!account.isEnabled()) {
			throw new DisabledException(id); 
		}

		// 필요하다면 로직 수정
		permit = password.length() == 60 ?
						password.equals(account.getPassword()) :
						bCryptPasswordEncoder.matches(password, account.getPassword());
		
		if(!permit) {
			// 로그인 실패 시 실패카운트 업데이트
			userStatusMapper.updateLoginFailCount(account.getUserStatus().getUserKey(),
					account.getUserStatus().getLoginFailCount() + 1);
			
			// 현재 실패횟수가 4회 이상이라면 db 에는 5회 실패로 저장되어있음
			// 계정을 잠그고 다음 로그인부터는 AccountExpiredException 로 제어
			if(account.getUserStatus().getLoginFailCount() >= 4) {
				userStatusMapper.updateIdNonExpired(account.getUserStatus().getUserKey(), false);
			}
			return null;
		}
		// 실패 카운트를 리셋하고 로그인 처리
		else {
			
			userStatusMapper.updateLastLoginDate(account.getUserStatus().getUserKey());
			userStatusMapper.updateLoginFailCount(account.getUserStatus().getUserKey(),0);
			return new UsernamePasswordAuthenticationToken(
					account,account.getPassword(),account.getAuthorities());
		}
		// 추후 이메일인증으로 계정 푸는 로직 추가 (3)
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// authentication 이 UsernamePasswordAuthenticationToken 일 경우 실행
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	
	
}

