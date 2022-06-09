package com.linkface.entity;

import lombok.Data;

@Data
public class RecipeGrade {

	// 레시피 키
	private int RECIPEID;
	// 유저 키
	private Long userKey;
	// 점수
	private int grade;
	
}
