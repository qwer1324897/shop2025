package com.ch.shop.model.subcategory;

import java.util.List;

// 서비스가 느슨하게 보유할 SubCategoryDAO 의 최상위 객체
public interface SubCategoryDAO {
	
	public List selectByTopCategoryID(int topcategory_id);
	
}
