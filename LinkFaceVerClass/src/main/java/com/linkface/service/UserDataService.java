package com.linkface.service;

import java.util.List;

import com.linkface.entity.UserData;

public interface UserDataService {
	public List<UserData> getJJim(Long userKey);
	public void deleteJJim(UserData userdata);
	public boolean insertJJim(UserData userdata);
	public UserData getOne(UserData userdata);
}
