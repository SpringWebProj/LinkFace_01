package com.linkface.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodItem {
	private Long recipeid;
	private String irdnttynm;
	private String irdntnm;
}
