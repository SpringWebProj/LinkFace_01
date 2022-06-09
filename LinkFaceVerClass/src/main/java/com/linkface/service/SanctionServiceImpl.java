package com.linkface.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkface.entity.Sanctions;
import com.linkface.mapper.SanctionsMapper;

import lombok.Setter;

@Service
public class SanctionServiceImpl implements SanctionService{

	@Setter(onMethod_=@Autowired)
	private SanctionsMapper sanctionsMapper;
	
	@Override
	public void sanctionsRecipe(Long userKey, int RECIPEID, String reasons, Long sanctioner) {
		
		Sanctions sanctions = Sanctions.builder()
										.userKey(userKey)
										.RECIPEID(RECIPEID)
										.reasons(reasons)
										.sanctioner(sanctioner)
										.build();
		sanctionsMapper.insert(sanctions);
		
	}

	@Override
	public void sanctionsUser(Long userKey, String reasons, Long sanctioner) {
		// TODO Auto-generated method stub
		
	}

}
