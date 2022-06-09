package com.linkface.mapper;

import java.util.List;

import com.linkface.entity.Sanctions;

public interface SanctionsMapper {

	List<Sanctions> readUserSanctions(Long userKey);
	
	void insert(Sanctions sanctions);
}
