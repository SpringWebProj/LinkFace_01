package com.linkface.service;

import java.util.List;
import java.util.Map;

import com.linkface.entity.FoodName;

public interface FoodNameService {
	
	public List<FoodName> showAll();
	
	public List<FoodName> showList(String RECIPENMKO);
	
	public FoodName showOne(int RECIPEID);
	
	
	// 전달받은 날짜에 따라 해당 날짜의 카테고리별 조회수 반환
	Map<String, List<Object>> getViewsbyCategory(String day);
	
	// 전달받은 날짜의 가장 열심히 활동한 유저 반환
	Map<String, List<Object>> getMostActiveUsers(String day);
	
	void deleteRecipe(int RECIPEID);
	
}
