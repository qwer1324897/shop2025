package com.ch.shop.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.Provider;

@Repository
public class MybatisProviderDAO implements ProviderDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Provider.selectAll");
	}

	@Override
	public Provider selectByName(String provider_name) {
		return sqlSessionTemplate.selectOne("Provider.selectByName", provider_name);
	}

}
