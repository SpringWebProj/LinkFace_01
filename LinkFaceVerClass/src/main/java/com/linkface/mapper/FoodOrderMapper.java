package com.linkface.mapper;

import java.util.List;

import com.linkface.entity.FoodOrder;

public interface FoodOrderMapper {
	List<FoodOrder> readAll();
	
	List<FoodOrder> readOne(int RECIPEID);
	
	void delete(int RECIPEID);
}
