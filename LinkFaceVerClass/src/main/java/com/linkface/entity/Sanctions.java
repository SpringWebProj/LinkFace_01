package com.linkface.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sanctions {

	private int sanctionsKey;
    private Long userKey;
    private Integer RECIPEID;
    private String reasons;
    private Long sanctioner;
    private Date createDate;

}
