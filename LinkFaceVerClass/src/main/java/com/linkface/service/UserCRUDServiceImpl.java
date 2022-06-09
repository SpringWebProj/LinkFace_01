package com.linkface.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkface.entity.UserInfo;
import com.linkface.mapper.UserInfoMapper;

import lombok.Setter;

@Service
public class UserCRUDServiceImpl implements UserCRUDService {
	
	@Setter(onMethod_ = @Autowired)
	private UserInfoMapper infoMapper;

	@Override
	public void registerInfo(UserInfo userinfo) {
		infoMapper.insert(userinfo);
	}

	@Override
	public UserInfo getByKey(Long userKey) {
		return infoMapper.select(userKey);
	}

	@Override
	public UserInfo getById(String userId) {
		return infoMapper.readId(userId);
	}

	@Override
	public UserInfo getByEmail(String userEmail) {
		return infoMapper.readEmail(userEmail);
	}

	@Override
	public boolean modifyEmail(String userEmail, Long userKey) {
		return infoMapper.updateEmail(userEmail, userKey) == 1;
	}

	@Override
	public boolean modifyPassword(String userPassword, Long userKey) {
		return infoMapper.updatePassword(userPassword, userKey) == 1;
	}

	@Override
	public boolean modifyName(String userName, Long userKey) {
		return infoMapper.updateName(userName, userKey) == 1;
	}

	@Override
	public void modifyUpdateDate(Long userKey) {
		infoMapper.changeUpdateDate(userKey);
	}

}
