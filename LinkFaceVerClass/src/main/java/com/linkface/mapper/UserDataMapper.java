package com.linkface.mapper;

import java.util.List;

import com.linkface.entity.UserData;

public interface UserDataMapper {
	public List<UserData> select(Long userKey);
	public void delete(UserData userdata);
	public int insert(UserData userdata);
	public UserData selectOne(UserData userdata);
}
