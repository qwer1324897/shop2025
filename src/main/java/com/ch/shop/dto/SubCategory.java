package com.ch.shop.dto;

import lombok.Data;

@Data
public class SubCategory {
	private int subcategory_id;
	private String subname;
	
	// 부모 객체를 보유해야 하는데(포린키), DB에서는 Join 문이 존재하므로 부모 자식 관계를 숫자로 연결시키지만, 자바에서는 has a 관계니까 객체로 연결한다
	private TopCategory topCategory;
}
