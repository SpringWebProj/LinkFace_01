package com.linkface.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.linkface.entity.FoodName;
import com.linkface.entity.Sanctions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

	private Long key;
	private String id;
	private String password;
	private String passwordCheck;
	private String email;
	private String name;
	private String role;
	private boolean enabled;
	private boolean idNonExpired;
	private boolean passwordNonExpired;
	private boolean emailAuth;
	private String authToken;
	private Date tokenCreateDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
	private Date createDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
	private Date updateDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
	private Date sanctionsDate;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy/MM/dd")
	private Date lastLoginDate;
	private List<FoodName> foodnames;
	private List<Sanctions> sanctions;
}
