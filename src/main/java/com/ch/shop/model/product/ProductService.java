package com.ch.shop.model.product;

import java.util.List;

import com.ch.shop.dto.Product;

public interface ProductService {
	
	public void regist(Product product);
	public List getList();	// 모든 목록 가져오기
	public List selectBySubCategoryId(int subcategory_id); // 하위 카테고리에 소속된 목록만 가져오기
	
	// 우리의 경우, 이미지들을 상품의 pk 값을 이용해 "p21" 의 디렉토리 형식으로 만들었기 때문에 pk값만 알면 디렉토리를 지워서 찌꺼기 이미지를 모두 제거할 수 있다.
	public void cancelUpload(Product product);
	public Product select(int product_id);
	
}
