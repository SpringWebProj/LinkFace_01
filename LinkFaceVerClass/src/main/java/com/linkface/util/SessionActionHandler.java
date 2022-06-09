package com.linkface.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionActionHandler {

	
	public void CreateLoginSession(String id,String password) {
		// 인증을 위해 id password로 구성된 인증정보 없는 객체 생성(첫번쨰 생성자)
		UsernamePasswordAuthenticationToken authDataToken =
				new UsernamePasswordAuthenticationToken(id,password);
		// 스프링 시큐리티 컨텍스트를 받아옴
        SecurityContext context = SecurityContextHolder.getContext();
        // Authentication 등록 
        // AuthenticationProvider 로 전달 후 인증로직 수행

        context.setAuthentication(authDataToken);
        System.out.println(context.getAuthentication().getPrincipal());




	}
}
