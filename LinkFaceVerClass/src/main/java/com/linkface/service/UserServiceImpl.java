package com.linkface.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkface.dto.UserDTO;
import com.linkface.entity.UserInfo;
import com.linkface.entity.UserStatus;
import com.linkface.mapper.FoodNameMapper;
import com.linkface.mapper.SanctionsMapper;
import com.linkface.mapper.UserInfoMapper;
import com.linkface.mapper.UserStatusMapper;
import com.linkface.security.AccessAccount;
import com.linkface.util.EmailHandler;
import com.linkface.util.SearchTrie;

import lombok.Setter;


@Service
public class UserServiceImpl implements UserService{

	@Setter(onMethod_=@Autowired)
	private UserInfoMapper userInfoMapper;
	@Setter(onMethod_=@Autowired)
	private UserStatusMapper userStatusMapper;
	@Setter(onMethod_=@Autowired)
	private PasswordEncoder pwend;
	@Setter(onMethod_=@Autowired)
	private FoodNameMapper foodNameMapper;
	@Setter(onMethod_=@Autowired)
	private SanctionsMapper sanctionsMapper;
	
	// 중복 검사
	@Override 
	public boolean duplicateCheck(Object dtoData,String type) {
		// key
		if(type.equals("key")) {
			return userInfoMapper.select((long)dtoData) == null;
		}
		// id
		else if(type.equals("id")) {
			return userInfoMapper.readId((String)dtoData) == null;
		}
		// 이메일
		else if(type.equals("email")) {
			return userInfoMapper.readEmail((String)dtoData) == null;
		}
		// 추후 확장 및 사용이 용이하게 코드 수정
		else {
			return false;
		}
		
	}

	// 유저 등록
	@Transactional
	@Override
	public boolean registUser(UserDTO dto) {
		
		// 유저 entity 생성
		UserInfo userinfo = UserInfo.builder()
								.userId(dto.getId())
								.userPassword(pwend.encode(dto.getPassword()))
								.userEmail(dto.getEmail())
								.userName(dto.getName())
								.build();
		// 개인정보 저장
		userInfoMapper.insert(userinfo);

		// 권한 db 생성을 위한 key 값 뽑아옴
		Long key = userInfoMapper.readId(userinfo.getUserId())
						.getUserKey();
		
		// key 가 null 이라면 테이블 생성 실패이므로 false 반환
		if(key == null) { return false; }
		
		// 권한 db 생성
		userStatusMapper.insert(key);
		// 토큰 생성
		userStatusMapper.updateToken(key);
		
		// 인증 메일 발송
		UserInfo info = userInfoMapper.select(key);
		UserStatus status = userStatusMapper.select(key);
		EmailHandler.
			authLinkSendEmail("email",info.getUserKey(),info.getUserEmail(),status.getAuthToken());
		
		// 관리자가 찾기 위해 아이디를 등록
		SearchTrie.insert(info.getUserId());
		
		// 정상 수행
		return true;
	}
	
	@Override
	public boolean userTokenCheck(Long key, String token) {
		
		UserInfo info = userInfoMapper.select(key);
		UserStatus status = userStatusMapper.select(key);
		// 토큰 유효시간 계산
		Date now = new Date();
		// 유저가 없거나 토큰이 다르거나 유효시간이 지나면 false 반환
		if(info == null)
		{ 	return false; }
		else if(!status.getAuthToken().equals(token))
		{	return false; }
		else if(now.getTime() - status.getTokenCreateDate().getTime() > (300 * 1000))
		{	return false; }
	
		return true;
	}

	@Override
	public String emailConformity(Long key,String email,String token) {
		
		UserInfo info = userInfoMapper.select(key);
		UserStatus status = userStatusMapper.select(key);
		
		if(!userTokenCheck(key,token))
		{	return "인증링크가 유효하지 않습니다. 인증 링크를 재요청해주세요";	}
		else if(userInfoMapper.readEmail(email) != null && 
				userInfoMapper.readEmail(email).getUserKey() != info.getUserKey())
		{	return "해당 이메일은 사용중이므로 등록하실 수 없습니다";	}

		// 이메일이 동일할 경우(신규가입 후 이메일 인증)
		if(info.getUserEmail().equals(email)) {
			// 이미 등록된 이메일이며 해당 유저의 인증이 끝난 경우
			if(status.isEmailAuth()) { return "해당 계정은 이미 인증을 마친 계정입니다"; }
			// 해당 유저의 권한 DB 를 업데이트
			userStatusMapper.updateEmailAuth(key);
			userStatusMapper.updateRole("ROLE_MEMBER", key);
			// 세션 업데이트
			checkSessionAndUpdate(key);
			return "계정의 이메일 인증이 완료되었습니다";
		}
		
		// 여기까지 테스트 완료
		// 아래 로직은 기능 추가 후 테스트 필요
		
		// 이메일이 다른 경우( 신규유저 & 기존유저의 이메일 변경) - 이메일인증 부분은 바꿀 필요 없이 이메일만 변경해 주면 됨
		userInfoMapper.updateEmail(email, key);
		// 세션 업데이트
		checkSessionAndUpdate(key);
		
		return "계정의 이메일 변경이 완료되었습니다";
	}

	@Override
	public String findUser(UserDTO dto, String type) {
		
		// 유니크값인 이메일로 계정을 찾음
		UserInfo info = userInfoMapper.readEmail(dto.getEmail());
		// 해당 유저가 없을 경우
		if(info == null) { return "해당하는 유저를 찾을 수 없습니다"; }
		// 아이디 찾기이며 입력한 정보가 동일할 경우
		System.out.println(info.getUserId());
		if(type.equals("id") && dto.getName().equals(info.getUserName())) {
			return "찾으시는 아이디는 " + info.getUserId() + "입니다";
		}
		// 패스워드 찾기이며 입력한 정보가 동일할 경우
		else if(dto.getId().equals(info.getUserId()) && dto.getName().equals(info.getUserName())) {
			// 새 토큰 할당
			userStatusMapper.updateToken(info.getUserKey());
			// 메일을 보내기 위해 권한 인증 db 정보 불러옴
			UserStatus stats = userStatusMapper.select(info.getUserKey());
			// 인증메일 발송
			EmailHandler.authLinkSendEmail(
					"password", info.getUserKey(), info.getUserEmail(), stats.getAuthToken());
			return "패스워드를 재설정할 인증 링크를 보내드렸습니다";
		}
		return "해당하는 유저를 찾을 수 없습니다";
	}

	@Override
	public boolean checkSessionAndUpdate(Long key) {
		
		boolean sameSession = false;
		
		// 현재 요청을 보낸 유저가 전달받은 데이터의 유저와 동일한지 확인
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(!principal.equals("anonymousUser")) {
			AccessAccount account = (AccessAccount) principal;
			sameSession = account.getUserInfo().getUserKey() == key;
		}
		// 맞다면 정보 업데이트
		if(sameSession) {
			AccessAccount newAccount = 
					new AccessAccount(userInfoMapper.select(key),userStatusMapper.select(key));
			Authentication newAuth = new UsernamePasswordAuthenticationToken
					(newAccount, newAccount.getPassword(),newAccount.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(newAuth);
		}
		
		return sameSession;
	}

	@Override
	public void createNewPassword(Long key, String password) {
		// 패스워드를 업데이트
		userInfoMapper.updatePassword(pwend.encode(password),key);
		// 업데이트 날짜 업데이트
		userInfoMapper.changeUpdateDate(key);
		// 재접근 못하도록 토큰 업데이트
		userStatusMapper.updateToken(key);
	}

	// 모든 유저 아이디 꺼내옴
	@Override
	public List<String> getAllUser() {
		return userInfoMapper.readAllUserId();
	}

	// 관리자가 유저 조회에 필요한 모든 데이터 꺼내서 반환
	@Override
	public UserDTO adminLookupUserinfo(String id) {
		UserInfo info = userInfoMapper.readId(id);
		if(info == null) {
			return null;
		}
		UserStatus status = userStatusMapper.select(info.getUserKey());
		foodNameMapper.readUserRecipe(info.getUserKey());
		
		// 아이디 이름 유저권한 가입날짜 이메일인증, 계정만료(인증 실패 및 장기 미접속에 사용), 계정 정지, 작성글 
		return UserDTO.builder()
						.id(info.getUserId())
						.name(info.getUserName())
						.role(status.getRole())
						.createDate(info.getCreateDate())
						.emailAuth(status.isEmailAuth())
						.idNonExpired(status.isIdNonExpired())
						.enabled(status.isEnabled())
						.foodnames(foodNameMapper.readUserRecipe(info.getUserKey()))
						.build();
	}

	@Override
	public boolean checkUserRole(Long key1, Long key2) {
		
		int requestRole = getUserRoleGrade(userStatusMapper.select(key1).getRole());
		int targetRole = getUserRoleGrade(userStatusMapper.select(key2).getRole());
		
		return requestRole > targetRole ? true : false; 
		
	}

	@Override
	public int getUserRoleGrade(String role) {
		
		if(role.equals("ROLE_ADMIN")) {
			return 5;
		}
		else if(role.equals("ROLE_MANAGER")) {
			return 4;
		}
		else if(role.equals("ROLE_MAMBER")) {
			return 3;
		}
		else if(role.equals("ROLE_UNKNOUN")) {
			return 2;
		}
		return 1;
	}

	@Override
	public UserDTO getUser(String id) {
		// TODO Auto-generated method stub
		
		UserInfo info = userInfoMapper.readId(id);
		if(info == null) {
			return null;
		}
		UserStatus stauts = userStatusMapper.select(info.getUserKey());
		
		return UserDTO.builder()
				.key(info.getUserKey())
				.id(info.getUserId())
				.role(stauts.getRole())
				.build();
	}

	@Override
	public void sanctionUser(Long key, int day, boolean enabled) {
		
		userStatusMapper.updateSanctionsDate(key, day);
		userStatusMapper.updateEnabled(key, enabled);
		
	}

	@Override
	public void changeUserRole(Long key, String role) {
		
		userStatusMapper.updateRole(role, key);
	}

}
