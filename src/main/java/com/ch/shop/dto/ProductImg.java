package com.ch.shop.dto;

import lombok.Data;

@Data
public class ProductImg {

	private int product_img_id;
	private String filename;	// 파일명은 뭔지
	private Product product;	// 어떤 상품에 소속된 이미지인지
}
