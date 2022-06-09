package com.linkface.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.linkface.entity.UserStatus;
import com.linkface.mapper.UserInfoMapper;
import com.linkface.mapper.UserStatusMapper;

import lombok.Setter;

@Component
public class UserScheduler {

	@Setter(onMethod_=@Autowired)
	private UserInfoMapper userInfoMapper;
	@Setter(onMethod_=@Autowired)
	private UserStatusMapper userStatusMapper;
	
	// 추후 공통 부분 통합하기 (2)
	
	// 일주일 이상 지난 비인증 유저 삭제
	@Scheduled(cron = "0 0 0 * * *")
	public void deleteUser(){
		
		Date now = new Date();
		long day = now.getTime() / (1000 * 60 * 60 * 24);
		
		List<UserStatus> userList = userStatusMapper.readAllUser();
		List<Long> deleteUserList = new ArrayList<>();

		for(UserStatus user : userList) {
			if(user.getRole().equals("ROLE_UNKNOWN")) {
				long userCreateDay = userInfoMapper.select(user.getUserKey())
						.getCreateDate().getTime() / (1000 * 60 * 60 * 24);
				if(day - userCreateDay > 7) {
					deleteUserList.add(user.getUserKey());
				}
			}
		}
		
		for(Long userKey : deleteUserList) {
			userStatusMapper.delete(userKey);
			userInfoMapper.delete(userKey);
		}
	}
	
	// 유저를 제재기간이 끝나면 권한 복구
	@Scheduled(cron = "0 0 0 * * *")
	public void unlockUser(){
		
		Date now = new Date();
		long day = now.getTime() / (1000 * 60 * 60 * 24);
		
		List<UserStatus> userList = userStatusMapper.readAllUser();
		for(UserStatus user : userList) {
			
			if(!user.isEnabled() && user.getSanctionsDate() != null) {
				if(day > user.getSanctionsDate().getTime() / (1000 * 60 * 60 * 24)) {
					userStatusMapper.updateEnabled(user.getUserKey(), true);
					userStatusMapper.updateSanctionsDate(user.getUserKey(), 0);	
				}
			}
		}
		
	}
	
	// 6개월동안 로그인 하지 않으면 패스워드 만료
	@Scheduled(cron = "0 0 0 * * *")
	public void passwordExpiredUser(){
		
		Date now = new Date();
		long day = now.getTime() / (1000 * 60 * 60 * 24);
		
		List<UserStatus> userList = userStatusMapper.readAllUser();
		for(UserStatus user : userList) {
			
			// 추후 이메일 발송로직 추가 (3)
			if(day - user.getLastLoginDate().getTime() / (1000 * 60 * 60 * 24) > 180) {
				userStatusMapper.updatePasswordNonExpired(user.getUserKey(), false);
			}
			
		}
		
	}
	
}
