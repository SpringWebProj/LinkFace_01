package com.linkface.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkface.entity.FoodIngredients;
import com.linkface.mapper.FoodIngredientsMapper;

import lombok.Setter;


@Service
public class FoodIngredientsServiceImpl implements FoodIngredientsService{

	@Setter(onMethod_ = @Autowired)
	private FoodIngredientsMapper mapper;

	public List<FoodIngredients> showAll() {
		return mapper.readAll();
	}

	public List<FoodIngredients> showOne(int RECIPEID) {
		return mapper.readOne(RECIPEID);
	}
}
