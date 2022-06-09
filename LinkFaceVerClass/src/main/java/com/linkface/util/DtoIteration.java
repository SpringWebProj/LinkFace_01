package com.linkface.util;

import com.linkface.dto.UserDTO;

public class DtoIteration {

	// userDTO 객체의 데이터가 비어있는지 롹인하는 메서드
	public static boolean userDTOCheckNonNull(UserDTO dto,String ...typeList) {
		
		boolean isNonNull = true;
		
		for(String type :typeList) {
			
			if(!isNonNull)
			{ return isNonNull; }
			
			if(type.equals("key"))
			{	isNonNull = dto.getKey() != null; }
			else if(type.equals("id"))
			{   isNonNull = dto.getId() != null;  }
			else if(type.equals("password"))
			{   isNonNull = dto.getPassword() != null;  }
			else if(type.equals("passwordCheck"))
			{   isNonNull = dto.getPasswordCheck() != null;  }
			else if(type.equals("id"))
			{   isNonNull = dto.getId() != null;  }
			else if(type.equals("email"))
			{   isNonNull = dto.getEmail() != null;  }
			else if(type.equals("name"))
			{   isNonNull = dto.getName() != null;  }
			else if(type.equals("createDate"))
			{   isNonNull = dto.getCreateDate() != null;  }
			else if(type.equals("updateDate"))
			{   isNonNull = dto.getUpdateDate() != null;  }
			else if(type.equals("role"))
			{   isNonNull = dto.getRole() != null;  }
			else {
				continue;
			}
		}
		return isNonNull;
	}
	
	
}
