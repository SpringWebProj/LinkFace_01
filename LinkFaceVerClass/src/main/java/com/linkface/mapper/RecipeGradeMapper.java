package com.linkface.mapper;

import java.util.List;

import com.linkface.entity.RecipeGrade;

public interface RecipeGradeMapper {

	int insert(RecipeGrade recipegrade);
	
	int update(RecipeGrade recipegrade);
	
	int delete(RecipeGrade recipegrade);
	// 하나의 게시글 내 한 유저의 평점 찾기
	RecipeGrade selectOneFromRecipe(RecipeGrade recipegrade);
	
	List<RecipeGrade> selectAllFromRecipe(int RECIPEID);
	
}
