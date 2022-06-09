package com.linkface.util;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linkface.service.UserService;

import lombok.Setter;

@Component
public class PostConstructExampleBean {

	@Setter(onMethod_=@Autowired)
    private UserService userService;
 
    @PostConstruct
    public void init() {
    	
    	SearchTrie.listInsert(userService.getAllUser());
    
    }
}