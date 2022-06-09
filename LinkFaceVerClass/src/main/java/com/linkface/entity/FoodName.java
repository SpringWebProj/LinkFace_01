package com.linkface.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class FoodName {
	
	private int RECIPEID;
	// 작성자 키
	private Long userKey;
	private String RECIPENMKO;
	private String SUMRY;
	private String NATIONNM;
	private String TYNM;
	private String COOKINGTIME; // int
	private String CALORIE; //
	private String QNT; //
	private int viewsCount;
	private int todayViewsCount;
	private String LEVELNM;
	private String IRDNTCODE;
	private String IMGURL;
	private float GRADEAVG;
	private int GRADECOUNT;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
	private Date createDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
	private Date updateDate;
	
}
