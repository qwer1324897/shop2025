package com.ch.shop.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.ProductSize;
import com.ch.shop.exception.ProductSizeException;

@Repository
public class MybatisProductSizeDAO implements ProductSizeDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insert(ProductSize productSize) throws ProductSizeException{
		try {
			sqlSessionTemplate.insert("ProductSize.insert", productSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductSizeException("상품 지원 사이즈 insert 실패", e);
		}
	}
	
	
}
