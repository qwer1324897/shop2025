package com.ch.shop.model.color;

import java.util.List;

//서비스가 느슨하게 보유할 ColorDAO 의 최상위 객체
public interface ColorDAO {

	// 모든 색상 조회
	public List selectAll();
}
