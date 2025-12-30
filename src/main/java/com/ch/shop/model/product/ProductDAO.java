package com.ch.shop.model.product;

import java.util.List;

import com.ch.shop.dto.Product;

public interface ProductDAO {
	public void insert(Product product);
	public List selectAll();	// 모든 레코드 가져오기.
	public List selectBySubCategoryId(int subcategory_id);
	public Product select(int product_id);
}
