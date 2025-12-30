package com.ch.shop.model.member;

import java.util.List;

import com.ch.shop.dto.Provider;

public interface ProviderDAO {
	public List selectAll();	// 모든 사업자 가져오기
	public Provider selectByName(String provider_name);	// 사업자명으로 가져오기
}
