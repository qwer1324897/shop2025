package com.ch.shop.model.topcategory;

import java.util.List;

// 서비스가 이 객체를 보유할 때, 느슨하게 보유하려면
public interface TopCategoryDAO {
	public List selectAll();
}
