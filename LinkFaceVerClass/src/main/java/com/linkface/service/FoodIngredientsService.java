package com.linkface.service;

import java.util.List;

import com.linkface.entity.FoodIngredients;

public interface FoodIngredientsService {

	public List<FoodIngredients> showAll();
	
	public List<FoodIngredients> showOne(int RECIPEID);
}
