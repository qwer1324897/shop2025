package com.ch.shop.dto;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import lombok.Data;

// 장바구니에 담겨질 하나의 상품 정보를 담을 객체. Product 와의 차이점은 quantity. 개수 차이
@Data
public class Cart {
	private int product_id;
	private String product_name;
	private String filename;
	private int price;
	private int ea;
}
