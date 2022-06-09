package com.linkface.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkface.entity.FoodItem;
import com.linkface.mapper.FoodItemsMapper;

import lombok.Setter;

@Service
public class FoodItemsServiceImpl implements FoodItemsService {
	
	@Setter(onMethod_ = @Autowired)
	private FoodItemsMapper mapper;
	
	@Override
	public List<FoodItem> getFoodItems() {
		
		return mapper.getFooditem();
	}

}
