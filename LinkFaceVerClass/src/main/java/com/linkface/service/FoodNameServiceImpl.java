package com.linkface.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkface.entity.FoodName;
import com.linkface.mapper.FoodIngredientsMapper;
import com.linkface.mapper.FoodNameMapper;
import com.linkface.mapper.FoodOrderMapper;
import com.linkface.mapper.UserInfoMapper;

import lombok.Setter;


@Service
public class FoodNameServiceImpl implements FoodNameService{

	@Setter(onMethod_ = @Autowired)
	private FoodNameMapper mapper;
	@Setter(onMethod_ = @Autowired)
	private FoodOrderMapper orderMapper;
	@Setter(onMethod_ = @Autowired)
	private FoodIngredientsMapper ingredientsMapper;
	
	@Setter(onMethod_=@Autowired)
	private UserInfoMapper infomapper;
	
	public List<FoodName> showAll() {
		return mapper.readAll();
	}

	public List<FoodName> showList(String RECIPENMKO) {
		return mapper.readList(RECIPENMKO);
	}

	public FoodName showOne(int RECIPEID) {
		
		mapper.updateViewCount(RECIPEID);
		
		return mapper.readOne(RECIPEID);
	}

	// 카테고리로 그룹화된 레시피에서 카테고리와 조회수만 맵으로 반환
	@Override
	public Map<String, List<Object>> getViewsbyCategory(String day) {
		
		List<Object> category = new ArrayList<>();
		List<Object> count  = new ArrayList<>();
		for(Map<String,Object> food : mapper.readGroupByCategoryOrderByDay(day)){
			category.add(food.get("category"));
			count.add(food.get("count"));
		}
		
		Map<String, List<Object>> data = new HashMap<>();
		data.put("category", category);
		data.put("viewsCount", count);
		
		return data;
	}

	@Override
	public Map<String, List<Object>> getMostActiveUsers(String day) {
		
		List<Object> userId = new ArrayList<>();
		List<Object> count  = new ArrayList<>();
		for(Map<String,Integer> user : mapper.readListTopFiveUser(day)){
			userId.add(infomapper.select((long)user.get("userKey")).getUserId());
			count.add(user.get("count"));
		}

		Map<String, List<Object>> data = new HashMap<>();
		data.put("userId", userId);
		data.put("recipeCount", count);
		
		
		return data;
	}

	@Override
	public void deleteRecipe(int RECIPEID) {
		mapper.delete(RECIPEID);
		orderMapper.delete(RECIPEID);
		ingredientsMapper.delete(RECIPEID);
	}
}
