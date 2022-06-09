package com.linkface.service;


public interface SanctionService {

	void sanctionsRecipe(Long userKey,int RECIPEID,String reasons,Long sanctioner);
	
	void sanctionsUser(Long userKey,String reasons,Long sanctioner);
	
}
