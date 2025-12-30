package com.ch.shop.model.topcategory;

import java.util.List;

// 아래의 서비스 객체조차, 컨트롤러가 보유할 때 느슨하게 보유해야 하므로(필터 > 컨트롤러 > 서비스 > DAO > 클래스 DAO) 얘도 인터페이스로 정의
public interface TopCategoryService {

	public List getList();
}
