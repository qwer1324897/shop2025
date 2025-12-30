package com.ch.shop.dto;

import java.util.List;

import lombok.Data;

// 쇼핑몰의 최상위 카테고리 테이블의 DTO
@Data
public class TopCategory {
	private int topcategory_id;
	private String topname;
	private List<SubCategory> subList;
}
