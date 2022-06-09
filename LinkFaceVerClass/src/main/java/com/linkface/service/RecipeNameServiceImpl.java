package com.linkface.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkface.entity.RecipeName;
import com.linkface.mapper.RecipeNameMapper;

import lombok.Setter;

@Service
public class RecipeNameServiceImpl implements RecipeNameService {

	@Setter(onMethod_=@Autowired)
	private RecipeNameMapper mapper;

	@Override
	public List<RecipeName> getList() {
		return mapper.getRecipeList();
	}

	@Override
	public RecipeName getOne(String recipenmko) {
		return mapper.getRecipeName(recipenmko);
	}
	
	
	
	
}
