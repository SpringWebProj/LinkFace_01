package com.linkface.service;

import java.util.List;

import com.linkface.entity.RecipeName;

public interface RecipeNameService {
	public List<RecipeName> getList();
	
	public RecipeName getOne(String recipenmko);
	

	
}
