package com.ch.shop.model.product;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ch.shop.dto.ProductImg;
import com.ch.shop.exception.ProductImgException;

@Repository
public class MybatisProductImgDAO implements ProductImgDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(ProductImg productImg) throws ProductImgException{
		try {
			sqlSessionTemplate.insert("ProductImg.insert", productImg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ProductImgException("상품 이미지 저장 실패", e);
		}
	}

	
}
