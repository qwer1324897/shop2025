package com.ch.shop.dto;

import lombok.Data;

@Data
public class ProductColor {

	private int Product_color_id;
	private Product product;		// 자바(객체지향언어)에서는 부모는 숫자가 아닌 객체로 표현.
	private Color color;		// 자바(객체지향언어)에서는 부모는 숫자가 아닌 객체로 표현.
	
}
