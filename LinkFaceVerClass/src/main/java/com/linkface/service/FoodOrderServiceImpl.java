package com.linkface.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkface.entity.FoodOrder;
import com.linkface.mapper.FoodOrderMapper;

import lombok.Setter;


@Service
public class FoodOrderServiceImpl implements FoodOrderService{

	@Setter(onMethod_ = @Autowired)
	private FoodOrderMapper mapper;

	public List<FoodOrder> showAll() {
		return mapper.readAll();
	}

	public List<FoodOrder> showOne(int RECIPEID) {
		return mapper.readOne(RECIPEID);
	}
	
}
