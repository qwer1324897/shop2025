package com.ch.shop.model.topcategory;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// TopCategoryDAO 를 구현한 객체
// 추후 Service 객체는 아래의 객체를 바로 보유하지 않고 보다 느슨하게 상위 객체인 TopCategoryDAO 인터페이스를 보유한다.
@Repository 	// ComponentScan 의 대상이 되어 스프링이 자동으로 올려줌
public class MybatisTopCategoryDAO implements TopCategoryDAO{

	// 이 객체는 스프링 컨테이너가 관리하고 있으므로, 자동주입을 명시하면 된다
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("TopCategory.selectAll");
	}
	
	
	
}
