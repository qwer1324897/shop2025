package com.ch.shop.model.product;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.Product;
import com.ch.shop.exception.ProductException;

@Repository
public class MybatisProductDAO implements ProductDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	// 개발자 뿐만 아니라, 일반 사용자가 에러의 원인을 알기 위해선 이 메서드를 호출한 외부 호출자에게 에러를 전달해야 한다.
	public void insert(Product product) throws ProductException{
		try {
			sqlSessionTemplate.insert("Product.insert", product);
		} catch (Exception e) {
			e.printStackTrace();	  // << 이건 개발자용 에러 확인코드. 서버의 콘솔에만 출력된다.
			throw new ProductException("상품 insert 실패", e);		// Spring 에게 오류를 알려 트랜잭션 롤백을 하기 위한 코드.
		}
	}

	@Override
	public List selectAll() {
		return sqlSessionTemplate.selectList("Product.selectAll");
	}

	@Override
	public List selectBySubCategoryId(int subcategory_id) {
		return sqlSessionTemplate.selectList("Product.selectBySubCategoryId", subcategory_id);
	}

	@Override
	public Product select(int product_id) {
		return sqlSessionTemplate.selectOne("Product.select", product_id);
	}

}
