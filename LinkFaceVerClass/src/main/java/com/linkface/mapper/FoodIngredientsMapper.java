package com.linkface.mapper;

import java.util.List;

import com.linkface.entity.FoodIngredients;

public interface FoodIngredientsMapper {
	List<FoodIngredients> readAll();
	
	List<FoodIngredients> readOne(int RECIPEID);
	
	void delete(int RECIPEID);
}
