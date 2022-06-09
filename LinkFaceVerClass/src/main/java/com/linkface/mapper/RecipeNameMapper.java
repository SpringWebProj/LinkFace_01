package com.linkface.mapper;

import java.util.List;

import com.linkface.entity.RecipeName;

public interface RecipeNameMapper {
	public RecipeName getRecipeName(String recipenmko);
	
	public List<RecipeName> getRecipeList();
	
}
