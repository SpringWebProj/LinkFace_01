package com.linkface.service;

import java.util.List;

import com.linkface.entity.FoodOrder;

public interface FoodOrderService {

	public List<FoodOrder> showAll();
	
	public List<FoodOrder> showOne(int RECIPEID);
}
