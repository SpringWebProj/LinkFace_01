package com.linkface.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkface.entity.RecipeGrade;
import com.linkface.mapper.FoodNameMapper;
import com.linkface.mapper.RecipeGradeMapper;

import lombok.Setter;

@Service
public class RecipeGradeServiceImpl implements RecipeGradeService{
	
	@Setter(onMethod_=@Autowired)
	private RecipeGradeMapper gradeMapper;
	
	@Setter(onMethod_=@Autowired)
	private FoodNameMapper foodNameMapper;

	@Override
	public String recipeGradeCUD(RecipeGrade recipeGrade) {
	
		String []messageBox = { 
								"별점을 삭제했씁니다" , "삭제할 별점이 존재하지 않습니다" ,
								"별점을 수정했습니다" , "별점을 수정하지 못했습니다" ,
								"별점을 등록했습니다" , "별점을 처리하는 도중 오류가 발생했습니다."
							   };
		
		int code = 6;
		
		if(recipeGrade.getGrade() == 0)
			code = gradeMapper.delete(recipeGrade) == 1 ?  1 : 2;
		else if(gradeMapper.selectOneFromRecipe(recipeGrade) != null) 
			code = gradeMapper.update(recipeGrade) == 1 ?  3 : 4;
		else
			code = gradeMapper.insert(recipeGrade) == 1 ?  5 : 6;
		
		if(code % 2 == 1){
			List<RecipeGrade> gradeList = 
					gradeMapper.selectAllFromRecipe(recipeGrade.getRECIPEID());
			if(gradeList.size() == 0) {
				return messageBox[5];
			}
			int sum = 0;
			for(RecipeGrade grade : gradeList) {	sum += grade.getGrade();	}
			foodNameMapper.updateGrade((float)sum / gradeList.size(),gradeList.size(),
					recipeGrade.getRECIPEID());
		}
		
		return messageBox[code - 1];
		
	}
	
	

	

}
