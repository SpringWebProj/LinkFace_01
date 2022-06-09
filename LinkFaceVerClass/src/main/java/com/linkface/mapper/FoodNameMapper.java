package com.linkface.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.linkface.entity.FoodName;

public interface FoodNameMapper {
	List<FoodName> readAll();
	
	List<FoodName> readList(String RECIPENMKO);
	
	// 카테고리로 그룹 후 전체 날짜 조회 높은 순 정렬
	List<Map<String,Object>>  readGroupByCategoryOrderByDay(String day);
	
	// 오늘 1달 전체기간 게시글 작성 상위 5명 데이터 가져오기
	List<Map<String,Integer>> readListTopFiveUser(String day);
	
	FoodName readOne(int RECIPEID);
	
	// 조회수 증가
	void updateViewCount(int RECIPEID);
	
	void updateGrade(@Param("GRADEAVG") float GRADEAVG,
						@Param("GRADECOUNT") int GRADECOUNT,
						@Param("RECIPEID") int RECIPEID);
	
	// 해당 유저 모든 레시피 가져오기
	List<FoodName> readUserRecipe(Long userKey);
	
	void delete(int RECIPEID);


}
