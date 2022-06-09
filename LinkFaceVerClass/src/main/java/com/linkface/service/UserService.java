package com.linkface.service;

import java.util.List;

import com.linkface.dto.UserDTO;

public interface UserService {

	// 유저 꺼내기
	UserDTO getUser(String id);
	
	// 해당 타입의 데이터가 db 에 존재하는지 확인
	boolean duplicateCheck(Object dtoData,String type);
	
	// DB 유저 등록
	boolean registUser(UserDTO dto);
	
	// 이메일 적합성 확인 & 적용
	String emailConformity(Long key,String email,String token);	

	// 아이디 찾기 & 패스워드 변경 이메일 발송
	String findUser(UserDTO dto, String type);
	
	// 토큰 및 유효기간 체크
	boolean userTokenCheck(Long key,String token);
	
	// 현재 유저가 접속한 상태라면 정보 변경 후 세션을 업데이트
	boolean checkSessionAndUpdate(Long key);
	
	//새 패스워드 발급 추후 기능 추가 시 수정
	void createNewPassword(Long key,String password);
	
	// 유저 아이디 전부 꺼내기
	List<String> getAllUser();
	
	// 관리자 조회용 유저 정보 반환
	UserDTO adminLookupUserinfo(String id);
	
	// 요청키와 변경키를 조회해서 권한을 비교하고 가능여부 반환
	boolean checkUserRole(Long key1,Long key2);
	
	// 권한을 비교 가능한 정수형으로 반환
	int getUserRoleGrade(String role);
	
	void sanctionUser(Long key, int day, boolean enabled);
	
	void changeUserRole(Long key,String role);
}
