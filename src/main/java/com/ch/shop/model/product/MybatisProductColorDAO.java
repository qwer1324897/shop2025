package com.ch.shop.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.ProductColor;
import com.ch.shop.exception.ProductColorException;

@Repository
public class MybatisProductColorDAO implements ProductColorDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(ProductColor productColor) throws ProductColorException{
		try {
			sqlSessionTemplate.insert("ProductColor.insert", productColor);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductColorException("상품 색상 등록 insert 실패", e);
		}
	}

}
